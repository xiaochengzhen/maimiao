package com.example.craw.model;

import java.io.Serializable;
import java.util.Date;

import com.example.craw.http.IncomeKeyAnnotation;
import lombok.Data;

/**
 * 利润表（us）
 * tb_company_us_income_statement
 * @author 
 */
@Data
public class CompanyUsIncomeStatementDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标的代码
     */
    private String symbol;

    private String quarter;

    /**
     * 营业总收入
     */
    @IncomeKeyAnnotation("TotalRevenue")
    private String totalRevenue;

    /**
     * 营业收入
     */
    @IncomeKeyAnnotation("OperatingRevenue")
    private String operatingRevenue;

    /**
     * 消费税
     */
    @IncomeKeyAnnotation("ExciseTaxes")
    private String exciseTaxes;

    /**
     * 主营业务成本
     */
    @IncomeKeyAnnotation("CostOfRevenue")
    private String costOfRevenue;

    /**
     * 毛利
     */
    @IncomeKeyAnnotation("GrossProfit")
    private String grossProfit;

    /**
     * 营业费用
     */
    @IncomeKeyAnnotation("OperatingExpense")
    private String operatingExpense;

    /**
     * 销售和管理费用
     */
    @IncomeKeyAnnotation("SellingGeneralAndAdministration")
    private String sellingAndAdministrativeExpenses;

    /**
     * 销售费用
     */
    @IncomeKeyAnnotation("SellingAndMarketingExpense")
    private String sellingAndMarketingExpense;

    /**
     * 管理费用
     */
    @IncomeKeyAnnotation("GeneralAndAdministrativeExpense")
    private String generalAndAdministrativeExpense;

    /**
     * 研发费用
     */
    @IncomeKeyAnnotation("ResearchAndDevelopment")
    private String researchAndDevelopmentCosts;

    /**
     * 折旧摊销及损耗
     */
    @IncomeKeyAnnotation("DepreciationAmortizationDepletion")
    private String depreciationAmortizationDepletion;

    /**
     * 折旧及摊销
     */
    @IncomeKeyAnnotation("DepreciationAndAmortization")
    private String depreciationAndAmortization;

    /**
     * 损耗
     */
    @IncomeKeyAnnotation("Depletion")
    private String depletion;

    /**
     * 可疑账款准备金
     */
    @IncomeKeyAnnotation("ProvisionForDoubtfulAccounts")
    private String provisionForDoubtfulAccounts;

    /**
     * 其他税费
     */
    @IncomeKeyAnnotation("OtherTaxes")
    private String otherTaxes;

    /**
     * 其他营业费用
     */
    @IncomeKeyAnnotation("OtherOperatingExpenses")
    private String otherOperatingExpenses;

    /**
     * 营业利润
     */
    @IncomeKeyAnnotation("OperatingIncome")
    private String operatingProfit;

    /**
     * 净非营业利息收入（费用）
     */
    @IncomeKeyAnnotation("NetNonOperatingInterestIncomeExpense")
    private String netNonOperatingInterestIncomeExpense;

    /**
     * 利息收入
     */
    @IncomeKeyAnnotation("InterestIncomeNonOperating")
    private String nonOperatingInterestIncome;

    /**
     * 利息费用
     */
    @IncomeKeyAnnotation("InterestExpenseNonOperating")
    private String nonOperatingInterestExpense;

    /**
     * 其他财务费用
     */
    @IncomeKeyAnnotation("TotalOtherFinanceCost")
    private String totalOtherFinanceCost;

    /**
     * 其他净收入（费用）
     */
    @IncomeKeyAnnotation("OtherIncomeExpense")
    private String otherNetIncome;

    /**
     * 出售证券收益
     */
    @IncomeKeyAnnotation("GainOnSaleOfSecurity")
    private String gainOnSaleOfSecurity;

    /**
     * 股权收益
     */
    @IncomeKeyAnnotation("EarningsFromEquityInterest")
    private String earningsFromEquityInterest;

    /**
     * 证券摊销
     */
    @IncomeKeyAnnotation("AmortizationOfSecurities")
    private String amortizationOfSecurities;

    /**
     * 特殊收入（费用）
     */
    @IncomeKeyAnnotation("SpecialIncomeCharges")
    private String specialIncome;

    /**
     * 减：重组与并购
     */
    @IncomeKeyAnnotation("RestructuringAndMergernAcquisition")
    private String restructuringAndMergerAcquisition;

    /**
     * 减：资本性资产减值
     */
    @IncomeKeyAnnotation("ImpairmentOfCapitalAssets")
    private String impairmentOfCapitalAssets;

    /**
     * 减：其他特殊费用
     */
    @IncomeKeyAnnotation("OtherSpecialCharges")
    private String otherSpecialCharges;

    /**
     * 减：勾销
     */
    @IncomeKeyAnnotation("WriteOff")
    private String writeOff;

    /**
     * 业务出售收益
     */
    @IncomeKeyAnnotation("GainOnSaleOfBusiness")
    private String gainOnSaleOfBusiness;

    /**
     * 固定资产出售收益
     */
    @IncomeKeyAnnotation("GainOnSaleOfPPE")
    private String gainOnSaleOfPropertyPlantEquipment;

    /**
     * 其他非经营收入（费用）
     */
    @IncomeKeyAnnotation("OtherNonOperatingIncomeExpenses")
    private String otherNonOperatingIncome;

    /**
     * 税前利润
     */
    @IncomeKeyAnnotation("PretaxIncome")
    private String incomeBeforeTax;

    /**
     * 所得税
     */
    @IncomeKeyAnnotation("TaxProvision")
    private String incomeTax;

    /**
     * 除税后的权益收益
     */
    @IncomeKeyAnnotation("EarningsFromEquityInterestNetOfTax")
    private String earningsFromEquityInterestNetOfTax;

    /**
     * 除税后利润
     */
    @IncomeKeyAnnotation("NetIncomeIncludingNoncontrollingInterests")
    private String netIncome;

    /**
     * 持续经营利润
     */
    @IncomeKeyAnnotation("NetIncomeContinuousOperations")
    private String netIncomeContinuousOperations;

    /**
     * 停止经营利润
     */
    @IncomeKeyAnnotation("NetIncomeDiscontinuousOperations")
    private String netIncomeDiscontinuousOperations;

    /**
     * 反常净利润
     */
    @IncomeKeyAnnotation("NetIncomeExtraordinary")
    private String netIncomeExtraordinary;

    /**
     * 会计变更的累计影响
     */
    @IncomeKeyAnnotation("CumulativeEffectOfAccountingChange")
    private String cumulativeEffectOfAccountingChange;

    /**
     * 税项亏损所得净额结转
     */
    @IncomeKeyAnnotation("NetIncomeFromTaxLossCarryforward")
    private String netIncomeFromTaxLossCarryForward;

    /**
     * 其他净损益
     */
    @IncomeKeyAnnotation("NetIncomeFromOtherGainsLosses")
    private String netIncomeFromOtherGainsLosses;

    /**
     * 归属于少数股东的净利润
     */
    @IncomeKeyAnnotation("MinorityInterests")
    private String minorityInterestIncome;

    /**
     * 归属于母公司的净利润
     */
    @IncomeKeyAnnotation("NetIncome")
    private String netIncomeAttributableToTheParentCompany;

    /**
     * 优先股派息
     */
    @IncomeKeyAnnotation("PreferredStockDividends")
    private String preferredStockDividends;

    /**
     * 其他优先股派息
     */
    @IncomeKeyAnnotation("OtherunderPreferredStockDividend")
    private String otherPreferredStockDividends;

    /**
     * 归属于普通股股东的净利润
     */
    @IncomeKeyAnnotation("NetIncomeCommonStockholders")
    private String netIncomeAttributableToCommonStockholders;

    /**
     * 基本每股收益
     */
    @IncomeKeyAnnotation("BasicEPS")
    private String basicEarningsPerShare;

    /**
     * 稀释每股收益
     */
    @IncomeKeyAnnotation("DilutedEPS")
    private String dilutedEarningsPerShare;

    /**
     * 每股派息
     */
    @IncomeKeyAnnotation("DividendPerShare")
    private String dividendPerShare;

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