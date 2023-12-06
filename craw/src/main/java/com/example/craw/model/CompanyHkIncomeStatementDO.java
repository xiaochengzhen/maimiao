package com.example.craw.model;

import java.io.Serializable;
import java.util.Date;

import com.example.craw.http.IncomeKeyAnnotation;
import lombok.Data;

/**
 * 利润表（hk）
 * tb_company_hk_income_statement
 * @author 
 */
@Data
public class CompanyHkIncomeStatementDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标的代码
     */
    private String symbol;

    /**
     * 周期：1=1季度； 2=中报； 3=3季度； 4=年报
     */
    private Integer period;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 营业额
     */
    @IncomeKeyAnnotation(value = "Turnover", enName = "Turnover", zhName = "营业额")
    private String turnover;

    /**
     * 营业收入特殊项目
     */
    @IncomeKeyAnnotation(value = "SpeItemsOR", enName = "Special items of operating revenue", zhName = "营业收入特殊项目")
    private String specialItemsOfOperatingRevenue;

    /**
     * 营业收入调整项目
     */
    @IncomeKeyAnnotation(value = "AdjItemsOR", enName = "Adjustment items of operating revenue", zhName = "营业收入调整项目")
    private String adjustmentItemsOfOperatingRevenue;

    /**
     * 营业总收入
     */
    @IncomeKeyAnnotation(value = "OperatingIncome", enName = "Operating income", zhName = "营业总收入")
    private String operatingIncome;

    /**
     * 员工支销-营业支出
     */
    @IncomeKeyAnnotation(value = "EmpTurnoverOP", enName = "Employee Expenditure-operating expenses", zhName = "员工支销-营业支出")
    private String employeeExpenditureOperatingExpenses;

    /**
     * 折旧与摊销-营业支出
     */
    @IncomeKeyAnnotation(value = "DepDividerSaleOP", enName = "Depreciation and amortization-operating expenses", zhName = "折旧与摊销-营业支出")
    private String depreciationAndAmortizationOperatingExpenses;

    /**
     * 销售成本
     */
    @IncomeKeyAnnotation(value = "SalesCost", enName = "Cost of sales", zhName = "销售成本")
    private String costOfSales;

    /**
     * 其他成本
     */
    @IncomeKeyAnnotation(value = "OtherCost", enName = "Other costs", zhName = "其他成本")
    private String otherCosts;

    /**
     * 营运支出
     */
    @IncomeKeyAnnotation(value = "OperExpenses", enName = "Operating expenses", zhName = "营运支出")
    private String operatingExpenses;

    /**
     * 毛利特殊项目
     */
    @IncomeKeyAnnotation(value = "SpeItemsGrossProfit", enName = "Special items of gross profit", zhName = "毛利特殊项目")
    private String specialItemsOfGrossProfit;

    /**
     * 毛利调整项目
     */
    @IncomeKeyAnnotation(value = "AdjItemsGrossProfit", enName = "Adjustment items of gross profit", zhName = "毛利调整项目")
    private String adjustmentItemsOfGrossProfit;

    /**
     * 毛利
     */
    @IncomeKeyAnnotation(value = "GrossProfit", enName = "Gross profit", zhName = "毛利")
    private String grossProfit;

    /**
     * 销售费用
     */
    @IncomeKeyAnnotation(value = "OpeExpenseAE", enName = "Selling expenses", zhName = "销售费用")
    private String sellingExpenses;

    /**
     * 行政费用
     */
    @IncomeKeyAnnotation(value = "AdmExpensesAE", enName = "Administrative expenses", zhName = "行政费用")
    private String administrativeExpenses;

    /**
     * 研发费用
     */
    @IncomeKeyAnnotation(value = "RAndDExpensesAE", enName = "Research and development expenses", zhName = "研发费用")
    private String researchAndDevelopmentExpenses;

    /**
     * 出售资产溢利
     */
    @IncomeKeyAnnotation(value = "ProfitDispOfAssets", enName = "Profit from asset sales", zhName = "出售资产溢利")
    private String profitFromAssetSales;

    /**
     * 员工支销
     */
    @IncomeKeyAnnotation(value = "EmpTurnover", enName = "Employee compensation and benefits", zhName = "员工支销")
    private String employeeExpense;

    /**
     * 折旧与摊销
     */
    @IncomeKeyAnnotation(value = "DepDividerSale", enName = "Depreciation and amortization", zhName = "折旧与摊销")
    private String depreciationAndAmortization;

    /**
     * 重估盈余
     */
    @IncomeKeyAnnotation(value = "RevaluationSurplus", enName = "Revaluation surplus", zhName = "重估盈余")
    private String revaluationSurplus;

    /**
     * 投资物业公平值变动
     */
    @IncomeKeyAnnotation(value = "CInFVofInvPropert", enName = "Changes in the fair value of investment property", zhName = "投资物业公平值变动")
    private String changesInTheFairValueOfInvestmentProperty;

    /**
     * 衍生金融工具公平值变动
     */
    @IncomeKeyAnnotation(value = "CInFVofDerFinInst", enName = "Changes in the fair value of derivative financial instruments", zhName = "衍生金融工具公平值变动")
    private String changesInTheFairValueOfDerivativeFinancialInstruments;

    /**
     * 财务资产公平值变动
     */
    @IncomeKeyAnnotation(value = "CInFVofFinAssets", enName = "Changes in the fair value of financial assets", zhName = "财务资产公平值变动")
    private String changesInTheFairValueOfFinancialAssets;

    /**
     * 其他资产公平值变动
     */
    @IncomeKeyAnnotation(value = "CInFVofOtherAssets", enName = "Changes in the fair value of other assets", zhName = "其他资产公平值变动")
    private String changesInTheFairValueOfOtherAssets;

    /**
     * 减值及拨备
     */
    @IncomeKeyAnnotation(value = "DevalAndAccBadDebt", enName = "Impairment and provisions", zhName = "减值及拨备")
    private String impairmentAndProvision;

    /**
     * 无形资产减值
     */
    @IncomeKeyAnnotation(value = "DevalofIntangAssets", enName = "Impairment of intangible assets", zhName = "无形资产减值")
    private String impairmentOfIntangibleAssets;

    /**
     * 物业、机器及设备减值
     */
    @IncomeKeyAnnotation(value = "DevalofProPlEquip", enName = "Impairment of property, machinery and equipment", zhName = "物业、机器及设备减值")
    private String impairmentOfPropertyMachineryAndEquipment;

    /**
     * 可供出售投资减值
     */
    @IncomeKeyAnnotation(value = "DevalofAvaForSaleInv", enName = "Impairment of available-for-sale investments", zhName = "可供出售投资减值")
    private String impairmentOfAvailableForSaleInvestments;

    /**
     * 商誉减值
     */
    @IncomeKeyAnnotation(value = "DevalofGoodwill", enName = "Goodwill impairment", zhName = "商誉减值")
    private String goodwillImpairment;

    /**
     * 其他减值即拨备
     */
    @IncomeKeyAnnotation(value = "DevalofOthers", enName = "Other impairment is provision", zhName = "其他减值即拨备")
    private String otherImpairmentIsProvision;

    /**
     * 营运利息支出
     */
    @IncomeKeyAnnotation(value = "OpeInterestExpense", enName = "Operating interest expense", zhName = "营运利息支出")
    private String operatingInterestExpense;

    /**
     * 经营溢利特殊项目
     */
    @IncomeKeyAnnotation(value = "SpeItemsOperateProfit", enName = "Special items of operating profit", zhName = "经营溢利特殊项目")
    private String specialItemsOfOperatingProfit;

    /**
     * 经营溢利调整项目
     */
    @IncomeKeyAnnotation(value = "AdjItemsOperateProfit", enName = "Adjustment items of operating profit", zhName = "经营溢利调整项目")
    private String adjustmentItemsOfOperatingProfit;

    /**
     * 经营溢利
     */
    @IncomeKeyAnnotation(value = "OperatingProfit", enName = "Operating profit", zhName = "经营溢利")
    private String operatingProfit;

    /**
     * 融资收入
     */
    @IncomeKeyAnnotation(value = "FinancingIncome", enName = "Financing income", zhName = "融资收入")
    private String financingIncome;

    /**
     * 融资成本
     */
    @IncomeKeyAnnotation(value = "FundingCost", enName = "Financing cost", zhName = "融资成本")
    private String financingCost;

    /**
     * 应占联营公司溢利
     */
    @IncomeKeyAnnotation(value = "AffiliatedComFrofit", enName = "Share of profits of associates", zhName = "应占联营公司溢利")
    private String shareOfProfitsOfAssociates;

    /**
     * 应占合营公司溢利
     */
    @IncomeKeyAnnotation(value = "JointVenturesProfit", enName = "Share of profit from joint venture company", zhName = "应占合营公司溢利")
    private String shareOfProfitFromJointVentureCompany;

    /**
     * 应占共同控制实体溢利
     */
    @IncomeKeyAnnotation(value = "CooperateBusProfit", enName = "Share of jointly controlled entity profit", zhName = "应占共同控制实体溢利")
    private String shareOfJointlyControlledEntityProfit;

    /**
     * 溢利特殊项目
     */
    @IncomeKeyAnnotation(value = "SpeItemsProfits", enName = "Special items of profit", zhName = "溢利特殊项目")
    private String specialItemsOfEarningBeforeTax;

    /**
     * 溢利调整项目
     */
    @IncomeKeyAnnotation(value = "AdjItemsProfits", enName = "Adjust items of profit", zhName = "溢利调整项目")
    private String adjustmentItemsOfEarningBeforeTax;

    /**
     * 除税前溢利
     */
    @IncomeKeyAnnotation(value = "EarningBeforeTax", enName = "Earning before tax", zhName = "除税前溢利")
    private String earningBeforeTax;

    /**
     * 税项
     */
    @IncomeKeyAnnotation(value = "Tax", enName = "Tax", zhName = "税项")
    private String tax;

    /**
     * 递延税项
     */
    @IncomeKeyAnnotation(value = "DeferredTax", enName = "Deferred tax", zhName = "递延税项")
    private String deferredTax;

    /**
     * 持续经营业务税后利润
     */
    @IncomeKeyAnnotation(value = "AftaxProfitFCBusi", enName = "After-tax profit from continuing operations", zhName = "持续经营业务税后利润")
    private String afterTaxProfitFromContinuingOperations;

    /**
     * 非持续经营业务税后溢利
     */
    @IncomeKeyAnnotation(value = "AftaxProfitFNCBusi", enName = "After-tax profit from non-continuing business", zhName = "非持续经营业务税后溢利")
    private String afterTaxProfitFromNonContinuingBusiness;

    /**
     * 除税后溢利特殊项目
     */
    @IncomeKeyAnnotation(value = "SpeItemsAftaxProfit", enName = "Special items of earning before tax", zhName = "除税后溢利特殊项目")
    private String specialItemsOfEarningAfterTax;

    /**
     * 除税后溢利调整项目
     */
    @IncomeKeyAnnotation(value = "AdjItemsAftaxProfit", enName = "Adjustment items of earning before tax", zhName = "除税后溢利调整项目")
    private String adjustmentItemsOfEarningAfterTax;

    /**
     * 除税后溢利
     */
    @IncomeKeyAnnotation(value = "EarningAfterTax", enName = "Earning after tax", zhName = "除税后溢利")
    private String earningAfterTax;

    /**
     * 少数股东应占来自持续业务溢利
     */
    @IncomeKeyAnnotation(value = "AttriToMSholdFCBprof", enName = "Minority shareholders should account for profits from continuing business", zhName = "少数股东应占来自持续业务溢利")
    private String minorityShareholdersProfitsFromContinuingBusiness;

    /**
     * 少数股东应占来自非持续业务溢利
     */
    @IncomeKeyAnnotation(value = "AttriToMSholdFNCBprof", enName = "Minority shareholders should account for non-continuing business profits", zhName = "少数股东应占来自非持续业务溢利")
    private String minorityShareholdersNonContinuingBusinessProfits;

    /**
     * 少数股东损益
     */
    @IncomeKeyAnnotation(value = "MinorityProfit", enName = "Minority profit", zhName = "少数股东损益")
    private String minorityProfit;

    /**
     * 股东应占来自持续业务溢利
     */
    @IncomeKeyAnnotation(value = "AttriToSholdFCBprof", enName = "Shareholders should account for profits from continuing business", zhName = "股东应占来自持续业务溢利")
    private String shareholdersProfitsFromContinuingBusiness;

    /**
     * 股东应占来自非持续业务溢利
     */
    @IncomeKeyAnnotation(value = "AttriToSholdFNCBprof", enName = "Shareholders should account for profits from non-continuing business", zhName = "股东应占来自非持续业务溢利")
    private String shareholdersFromNonContinuingBusiness;

    /**
     * 股东应占溢利特殊项目
     */
    @IncomeKeyAnnotation(value = "SpeItemsProfTSholders", enName = "Special items of profit attributable to shareholders", zhName = "股东应占溢利特殊项目")
    private String specialItemsOfProfitAttributableToShareholders;

    /**
     * 股东应占溢利调整项目
     */
    @IncomeKeyAnnotation(value = "AdjItemsProfTSholders", enName = "Adjustment items of profit attributable to shareholders", zhName = "股东应占溢利调整项目")
    private String adjustmentItemsOfProfitAttributableToShareholders;

    /**
     * 股东应占溢利
     */
    @IncomeKeyAnnotation(value = "ProfitToShareholders", enName = "Profits attributable to shareholders", zhName = "股东应占溢利")
    private String profitAttributableToShareholders;

    /**
     * 股息
     */
    @IncomeKeyAnnotation(value = "Dividend", enName = "Dividend", zhName = "股息")
    private String dividend;

    /**
     * 每股股息
     */
    @IncomeKeyAnnotation(value = "DividendPerShare", enName = "Dividend per share", zhName = "每股股息")
    private String dividendPerShare;

    /**
     * 每股基本盈利
     */
    @IncomeKeyAnnotation(value = "EPSBasic", enName = "Basic earnings per share", zhName = "每股基本盈利")
    private String basicEarningsPerShare;

    /**
     * 每股摊薄盈利
     */
    @IncomeKeyAnnotation(value = "EPS", enName = "Diluted earnings per share", zhName = "每股摊薄盈利")
    private String dilutedEarningsPerShare;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}