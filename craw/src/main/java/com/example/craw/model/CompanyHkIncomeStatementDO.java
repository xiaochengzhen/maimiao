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
     * 季度
     */
    private String quarter;

    /**
     * 营业额
     */
    @IncomeKeyAnnotation("Turnover")
    private String turnover;

    /**
     * 营业收入特殊项目
     */
    @IncomeKeyAnnotation("SpeItemsOR")
    private String specialItemsOfOperatingRevenue;

    /**
     * 营业收入调整项目
     */
    @IncomeKeyAnnotation("AdjItemsOR")
    private String adjustmentItemsOfOperatingRevenue;

    /**
     * 营业总收入
     */
    @IncomeKeyAnnotation("OperatingIncome")
    private String operatingIncome;

    /**
     * 员工支销-营业支出
     */
    @IncomeKeyAnnotation("EmpTurnoverOP")
    private String employeeExpenditureOperatingExpenses;

    /**
     * 折旧与摊销-营业支出
     */
    @IncomeKeyAnnotation("DepDividerSaleOP")
    private String depreciationAndAmortizationOperatingExpenses;

    /**
     * 销售成本
     */
    @IncomeKeyAnnotation("SalesCost")
    private String costOfSales;

    /**
     * 其他成本
     */
    @IncomeKeyAnnotation("OtherCost")
    private String otherCosts;

    /**
     * 营运支出
     */
    @IncomeKeyAnnotation("OperExpenses")
    private String operatingExpenses;

    /**
     * 毛利特殊项目
     */
    @IncomeKeyAnnotation("SpeItemsGrossProfit")
    private String specialItemsOfGrossProfit;

    /**
     * 毛利调整项目
     */
    @IncomeKeyAnnotation("AdjItemsGrossProfit")
    private String adjustmentItemsOfGrossProfit;

    /**
     * 毛利
     */
    @IncomeKeyAnnotation("GrossProfit")
    private String grossProfit;

    /**
     * 销售费用
     */
    @IncomeKeyAnnotation("OpeExpenseAE")
    private String sellingExpenses;

    /**
     * 行政费用
     */
    @IncomeKeyAnnotation("AdmExpensesAE")
    private String administrativeExpenses;

    /**
     * 研发费用
     */
    @IncomeKeyAnnotation("RAndDExpensesAE")
    private String researchAndDevelopmentExpenses;

    /**
     * 出售资产溢利
     */
    @IncomeKeyAnnotation("ProfitDispOfAssets")
    private String profitFromAssetSales;

    /**
     * 员工支销
     */
    @IncomeKeyAnnotation("EmpTurnover")
    private String employeeExpense;

    /**
     * 折旧与摊销
     */
    @IncomeKeyAnnotation("DepDividerSale")
    private String depreciationAndAmortization;

    /**
     * 重估盈余
     */
    @IncomeKeyAnnotation("RevaluationSurplus")
    private String revaluationSurplus;

    /**
     * 投资物业公平值变动
     */
    @IncomeKeyAnnotation("CInFVofInvPropert")
    private String changesInTheFairValueOfInvestmentProperty;

    /**
     * 衍生金融工具公平值变动
     */
    @IncomeKeyAnnotation("CInFVofDerFinInst")
    private String changesInTheFairValueOfDerivativeFinancialInstruments;

    /**
     * 财务资产公平值变动
     */
    @IncomeKeyAnnotation("CInFVofFinAssets")
    private String changesInTheFairValueOfFinancialAssets;

    /**
     * 其他资产公平值变动
     */
    @IncomeKeyAnnotation("CInFVofOtherAssets")
    private String changesInTheFairValueOfOtherAssets;

    /**
     * 减值及拨备
     */
    @IncomeKeyAnnotation("DevalAndAccBadDebt")
    private String impairmentAndProvision;

    /**
     * 无形资产减值
     */
    @IncomeKeyAnnotation("DevalofIntangAssets")
    private String impairmentOfIntangibleAssets;

    /**
     * 物业、机器及设备减值
     */
    @IncomeKeyAnnotation("DevalofProPlEquip")
    private String impairmentOfPropertyMachineryAndEquipment;

    /**
     * 可供出售投资减值
     */
    @IncomeKeyAnnotation("DevalofAvaForSaleInv")
    private String impairmentOfAvailableForSaleInvestments;

    /**
     * 商誉减值
     */
    @IncomeKeyAnnotation("DevalofGoodwill")
    private String goodwillImpairment;

    /**
     * 其他减值即拨备
     */
    @IncomeKeyAnnotation("DevalofOthers")
    private String otherImpairmentIsProvision;

    /**
     * 营运利息支出
     */
    @IncomeKeyAnnotation("OpeInterestExpense")
    private String operatingInterestExpense;

    /**
     * 经营溢利特殊项目
     */
    @IncomeKeyAnnotation("SpeItemsOperateProfit")
    private String specialItemsOfOperatingProfit;

    /**
     * 经营溢利调整项目
     */
    @IncomeKeyAnnotation("AdjItemsOperateProfit")
    private String adjustmentItemsOfOperatingProfit;

    /**
     * 经营溢利
     */
    @IncomeKeyAnnotation("OperatingProfit")
    private String operatingProfit;

    /**
     * 融资收入
     */
    @IncomeKeyAnnotation("FinancingIncome")
    private String financingIncome;

    /**
     * 融资成本
     */
    @IncomeKeyAnnotation("FundingCost")
    private String financingCost;

    /**
     * 应占联营公司溢利
     */
    @IncomeKeyAnnotation("AffiliatedComFrofit")
    private String shareOfProfitsOfAssociates;

    /**
     * 应占合营公司溢利
     */
    @IncomeKeyAnnotation("JointVenturesProfit")
    private String shareOfProfitFromJointVentureCompany;

    /**
     * 应占共同控制实体溢利
     */
    @IncomeKeyAnnotation("CooperateBusProfit")
    private String shareOfJointlyControlledEntityProfit;

    /**
     * 溢利特殊项目
     */
    @IncomeKeyAnnotation("SpeItemsProfits")
    private String specialItemsOfEarningBeforeTax;

    /**
     * 溢利调整项目
     */
    @IncomeKeyAnnotation("AdjItemsProfits")
    private String adjustmentItemsOfEarningBeforeTax;

    /**
     * 除税前溢利
     */
    @IncomeKeyAnnotation("EarningBeforeTax")
    private String earningBeforeTax;

    /**
     * 税项
     */
    @IncomeKeyAnnotation("Tax")
    private String tax;

    /**
     * 递延税项
     */
    @IncomeKeyAnnotation("DeferredTax")
    private String deferredTax;

    /**
     * 持续经营业务税后利润
     */
    @IncomeKeyAnnotation("AftaxProfitFCBusi")
    private String afterTaxProfitFromContinuingOperations;

    /**
     * 非持续经营业务税后溢利
     */
    @IncomeKeyAnnotation("AftaxProfitFNCBusi")
    private String afterTaxProfitFromNonContinuingBusiness;

    /**
     * 除税后溢利特殊项目
     */
    @IncomeKeyAnnotation("SpeItemsAftaxProfit")
    private String specialItemsOfEarningAfterTax;

    /**
     * 除税后溢利调整项目
     */
    @IncomeKeyAnnotation("AdjItemsAftaxProfit")
    private String adjustmentItemsOfEarningAfterTax;

    /**
     * 除税后溢利
     */
    @IncomeKeyAnnotation("EarningAfterTax")
    private String earningAfterTax;

    /**
     * 少数股东应占来自持续业务溢利
     */
    @IncomeKeyAnnotation("AttriToMSholdFCBprof")
    private String minorityShareholdersProfitsFromContinuingBusiness;

    /**
     * 少数股东应占来自非持续业务溢利
     */
    @IncomeKeyAnnotation("AttriToMSholdFNCBprof")
    private String minorityShareholdersNonContinuingBusinessProfits;

    /**
     * 少数股东损益
     */
    @IncomeKeyAnnotation("MinorityProfit")
    private String minorityProfit;

    /**
     * 股东应占来自持续业务溢利
     */
    @IncomeKeyAnnotation("AttriToSholdFCBprof")
    private String shareholdersProfitsFromContinuingBusiness;

    /**
     * 股东应占来自非持续业务溢利
     */
    @IncomeKeyAnnotation("AttriToSholdFNCBprof")
    private String shareholdersFromNonContinuingBusiness;

    /**
     * 股东应占溢利特殊项目
     */
    @IncomeKeyAnnotation("SpeItemsProfTSholders")
    private String specialItemsOfProfitAttributableToShareholders;

    /**
     * 股东应占溢利调整项目
     */
    @IncomeKeyAnnotation("AdjItemsProfTSholders")
    private String adjustmentItemsOfProfitAttributableToShareholders;

    /**
     * 股东应占溢利
     */
    @IncomeKeyAnnotation("ProfitToShareholders")
    private String profitAttributableToShareholders;

    /**
     * 股息
     */
    @IncomeKeyAnnotation("Dividend")
    private String dividend;

    /**
     * 每股股息
     */
    @IncomeKeyAnnotation("DividendPerShare")
    private String dividendPerShare;

    /**
     * 每股基本盈利
     */
    @IncomeKeyAnnotation("EPSBasic")
    private String basicEarningsPerShare;

    /**
     * 每股摊薄盈利
     */
    @IncomeKeyAnnotation("EPS")
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