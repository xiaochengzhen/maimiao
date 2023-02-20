package com.example.mybatisplusdemo.selfinterceptor;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/11 14:26
 */
public class MyInterceptor extends JsqlParserSupport implements InnerInterceptor {
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (!InterceptorIgnoreHelper.willIgnoreTenantLine(ms.getId())) {
            PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
            mpBs.sql(this.parserSingle(mpBs.sql(), (Object)null));
            System.out.println();
        }
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        InnerInterceptor.super.beforeUpdate(executor, ms, parameter);
    }

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        InnerInterceptor.super.beforePrepare(sh, connection, transactionTimeout);
    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        this.processSelectBody(select.getSelectBody());
        List<WithItem> withItemsList = select.getWithItemsList();
        if (!CollectionUtils.isEmpty(withItemsList)) {
            withItemsList.forEach(this::processSelectBody);
        }
    }

    protected void processSelectBody(SelectBody selectBody) {
        if (selectBody != null) {
            if (selectBody instanceof PlainSelect) {
                this.processPlainSelect((PlainSelect)selectBody);
            } else if (selectBody instanceof WithItem) {
                WithItem withItem = (WithItem)selectBody;
                this.processSelectBody(withItem.getSubSelect().getSelectBody());
            } else {
                SetOperationList operationList = (SetOperationList)selectBody;
                List<SelectBody> selectBodyList = operationList.getSelects();
                if (CollectionUtils.isNotEmpty(selectBodyList)) {
                    selectBodyList.forEach(this::processSelectBody);
                }
            }

        }
    }

    @Override
    protected void processUpdate(Update update, int index, String sql, Object obj) {
        Table table = update.getTable();
        update.setWhere(this.andExpression(table, update.getWhere()));
    }
    @Override
    protected void processDelete(Delete delete, int index, String sql, Object obj) {
        delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere()));
    }

    protected BinaryExpression andExpression(Table table, Expression where) {
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(this.getAliasColumn(table));
        equalsTo.setRightExpression(new LongValue(1));
        if (null != where) {
            return where instanceof OrExpression ? new AndExpression(equalsTo, new Parenthesis(where)) : new AndExpression(equalsTo, where);
        } else {
            return equalsTo;
        }
    }

    protected void processInsertSelect(SelectBody selectBody) {
        PlainSelect plainSelect = (PlainSelect)selectBody;
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            this.processPlainSelect(plainSelect);
            this.appendSelectItem(plainSelect.getSelectItems());
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect)fromItem;
            this.appendSelectItem(plainSelect.getSelectItems());
            this.processInsertSelect(subSelect.getSelectBody());
        }

    }

    protected void appendSelectItem(List<SelectItem> selectItems) {
        if (!CollectionUtils.isEmpty(selectItems)) {
            if (selectItems.size() == 1) {
                SelectItem item = (SelectItem)selectItems.get(0);
                if (item instanceof AllColumns || item instanceof AllTableColumns) {
                    return;
                }
            }
            selectItems.add(new SelectExpressionItem(new Column("company_id")));
        }
    }

    protected void processPlainSelect(PlainSelect plainSelect) {
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        if (CollectionUtils.isNotEmpty(selectItems)) {
            selectItems.forEach(this::processSelectItem);
        }

        Expression where = plainSelect.getWhere();
        this.processWhereSubSelect(where);
        FromItem fromItem = plainSelect.getFromItem();
        List<Table> list = this.processFromItem(fromItem);
        List<Table> mainTables = new ArrayList(list);
        List<Join> joins = plainSelect.getJoins();
        if (CollectionUtils.isNotEmpty(joins)) {
            mainTables = this.processJoins((List)mainTables, joins);
        }
        if (CollectionUtils.isNotEmpty((Collection)mainTables)) {
            plainSelect.setWhere(this.builderExpression(where, (List)mainTables));
        }
    }

    private List<Table> processFromItem(FromItem fromItem) {
        while(fromItem instanceof ParenthesisFromItem) {
            fromItem = ((ParenthesisFromItem)fromItem).getFromItem();
        }
        List<Table> mainTables = new ArrayList();
        if (fromItem instanceof Table) {
            Table fromTable = (Table)fromItem;
            mainTables.add(fromTable);
        } else if (fromItem instanceof SubJoin) {
            List<Table> tables = this.processSubJoin((SubJoin)fromItem);
            mainTables.addAll(tables);
        } else {
            this.processOtherFromItem(fromItem);
        }

        return mainTables;
    }

    protected void processWhereSubSelect(Expression where) {
        if (where != null) {
            if (where instanceof FromItem) {
                this.processOtherFromItem((FromItem)where);
            } else {
                if (where.toString().indexOf("SELECT") > 0) {
                    if (where instanceof BinaryExpression) {
                        BinaryExpression expression = (BinaryExpression)where;
                        this.processWhereSubSelect(expression.getLeftExpression());
                        this.processWhereSubSelect(expression.getRightExpression());
                    } else if (where instanceof InExpression) {
                        InExpression expression = (InExpression)where;
                        Expression inExpression = expression.getRightExpression();
                        if (inExpression instanceof SubSelect) {
                            this.processSelectBody(((SubSelect)inExpression).getSelectBody());
                        }
                    } else if (where instanceof ExistsExpression) {
                        ExistsExpression expression = (ExistsExpression)where;
                        this.processWhereSubSelect(expression.getRightExpression());
                    } else if (where instanceof NotExpression) {
                        NotExpression expression = (NotExpression)where;
                        this.processWhereSubSelect(expression.getExpression());
                    } else if (where instanceof Parenthesis) {
                        Parenthesis expression = (Parenthesis)where;
                        this.processWhereSubSelect(expression.getExpression());
                    }
                }

            }
        }
    }

    protected void processSelectItem(SelectItem selectItem) {
        if (selectItem instanceof SelectExpressionItem) {
            SelectExpressionItem selectExpressionItem = (SelectExpressionItem)selectItem;
            if (selectExpressionItem.getExpression() instanceof SubSelect) {
                this.processSelectBody(((SubSelect)selectExpressionItem.getExpression()).getSelectBody());
            } else if (selectExpressionItem.getExpression() instanceof Function) {
                this.processFunction((Function)selectExpressionItem.getExpression());
            }
        }

    }

    protected void processFunction(Function function) {
        ExpressionList parameters = function.getParameters();
        if (parameters != null) {
            parameters.getExpressions().forEach((expression) -> {
                if (expression instanceof SubSelect) {
                    this.processSelectBody(((SubSelect)expression).getSelectBody());
                } else if (expression instanceof Function) {
                    this.processFunction((Function)expression);
                }

            });
        }

    }

    protected void processOtherFromItem(FromItem fromItem) {
        while(fromItem instanceof ParenthesisFromItem) {
            fromItem = ((ParenthesisFromItem)fromItem).getFromItem();
        }

        if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect)fromItem;
            if (subSelect.getSelectBody() != null) {
                this.processSelectBody(subSelect.getSelectBody());
            }
        } else if (fromItem instanceof ValuesList) {
            this.logger.debug("Perform a subQuery, if you do not give us feedback");
        } else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect lateralSubSelect = (LateralSubSelect)fromItem;
            if (lateralSubSelect.getSubSelect() != null) {
                SubSelect subSelect = lateralSubSelect.getSubSelect();
                if (subSelect.getSelectBody() != null) {
                    this.processSelectBody(subSelect.getSelectBody());
                }
            }
        }

    }

    private List<Table> processSubJoin(SubJoin subJoin) {
        List<Table> mainTables = new ArrayList();
        if (subJoin.getJoinList() != null) {
            List<Table> list = this.processFromItem(subJoin.getLeft());
            ((List)mainTables).addAll(list);
            mainTables = this.processJoins((List)mainTables, subJoin.getJoinList());
        }

        return (List)mainTables;
    }

    private List<Table> processJoins(List<Table> mainTables, List<Join> joins) {
        Table mainTable = null;
        Table leftTable = null;
        if (mainTables == null) {
            mainTables = new ArrayList();
        } else if (((List)mainTables).size() == 1) {
            mainTable = (Table)((List)mainTables).get(0);
            leftTable = mainTable;
        }

        Deque<List<Table>> onTableDeque = new LinkedList();
        Iterator var6 = joins.iterator();

        while(true) {
            while(true) {
                while(var6.hasNext()) {
                    Join join = (Join)var6.next();
                    FromItem joinItem = join.getRightItem();
                    List<Table> joinTables = null;
                    if (joinItem instanceof Table) {
                        joinTables = new ArrayList();
                        ((List)joinTables).add((Table)joinItem);
                    } else if (joinItem instanceof SubJoin) {
                        joinTables = this.processSubJoin((SubJoin)joinItem);
                    }

                    if (joinTables != null) {
                        if (join.isSimple()) {
                            ((List)mainTables).addAll((Collection)joinTables);
                        } else {
                            Table joinTable = (Table)((List)joinTables).get(0);
                            List<Table> onTables = null;
                            if (join.isRight()) {
                                mainTable = joinTable;
                                if (leftTable != null) {
                                    onTables = Collections.singletonList(leftTable);
                                }
                            } else if (join.isLeft()) {
                                onTables = Collections.singletonList(joinTable);
                            } else if (join.isInner()) {
                                if (mainTable == null) {
                                    onTables = Collections.singletonList(joinTable);
                                } else {
                                    onTables = Arrays.asList(mainTable, joinTable);
                                }

                                mainTable = null;
                            }

                            mainTables = new ArrayList();
                            if (mainTable != null) {
                                ((List)mainTables).add(mainTable);
                            }

                            Collection<Expression> originOnExpressions = join.getOnExpressions();
                            LinkedList onExpressions;
                            if (originOnExpressions.size() == 1 && onTables != null) {
                                onExpressions = new LinkedList();
                                onExpressions.add(this.builderExpression((Expression)originOnExpressions.iterator().next(), onTables));
                                join.setOnExpressions(onExpressions);
                                leftTable = joinTable;
                            } else {
                                onTableDeque.push(onTables);
                                if (originOnExpressions.size() > 1) {
                                    onExpressions = new LinkedList();
                                    Iterator var14 = originOnExpressions.iterator();

                                    while(var14.hasNext()) {
                                        Expression originOnExpression = (Expression)var14.next();
                                        List<Table> currentTableList = (List)onTableDeque.poll();
                                        if (CollectionUtils.isEmpty(currentTableList)) {
                                            onExpressions.add(originOnExpression);
                                        } else {
                                            onExpressions.add(this.builderExpression(originOnExpression, currentTableList));
                                        }
                                    }

                                    join.setOnExpressions(onExpressions);
                                }

                                leftTable = joinTable;
                            }
                        }
                    } else {
                        this.processOtherFromItem(joinItem);
                        leftTable = null;
                    }
                }

                return (List)mainTables;
            }
        }
    }

    protected Expression builderExpression(Expression currentExpression, List<Table> tables) {
        if (CollectionUtils.isEmpty(tables)) {
            return currentExpression;
        } else {
            List<Table> tempTables = (List)tables.stream().collect(Collectors.toList());
            if (CollectionUtils.isEmpty(tempTables)) {
                return currentExpression;
            } else {
                Expression tenantId = new LongValue(1);
               /* List<EqualsTo> equalsTos = (List)tempTables.stream().map((item) -> {
                    return new EqualsTo(this.getAliasColumn(item), tenantId);
                }).collect(Collectors.toList());*/
                //Expression injectExpression = (Expression)equalsTos.get(0);
                Expression injectExpression = new Column("company_id=1");

               /* if (equalsTos.size() > 1) {
                    for(int i = 1; i < equalsTos.size(); ++i) {
                        injectExpression = new AndExpression((Expression)injectExpression, (Expression)equalsTos.get(i));
                    }
                }*/

                if (currentExpression == null) {
                    return (Expression)injectExpression;
                } else {
                    return currentExpression instanceof OrExpression ? new AndExpression(new Parenthesis(currentExpression), (Expression)injectExpression) : new AndExpression(currentExpression, (Expression)injectExpression);
                }
            }
        }
    }

    protected Column getAliasColumn(Table table) {
        StringBuilder column = new StringBuilder();
        if (table.getAlias() != null) {
            column.append(table.getAlias().getName());
        } else {
            column.append(table.getName());
        }

        column.append(".").append("company_id");
        return new Column(column.toString());
    }
}
