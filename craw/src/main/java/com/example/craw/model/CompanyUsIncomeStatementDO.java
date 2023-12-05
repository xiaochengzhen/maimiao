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
    @IncomeKeyAnnotation(enName = "Total revenue", zhName = "营业总收入", value = "TotalRevenue")
    private String totalRevenue;

    /**
     * 营业收入
     */
    @IncomeKeyAnnotation(enName = "Operating revenue", zhName = "营业收入", value = "OperatingRevenue")
    private String operatingRevenue;

    /**
     * 消费税
     */
    @IncomeKeyAnnotation(enName = "Excise taxes", zhName = "消费税", value = "ExciseTaxes")
    private String exciseTaxes;

    /**
     * 主营业务成本
     */
    @IncomeKeyAnnotation(enName = "Cost of revenue", zhName = "主营业务成本", value = "CostOfRevenue")
    private String costOfRevenue;

    /**
     * 毛利
     */
    @IncomeKeyAnnotation(enName = "Gross profit", zhName = "毛利", value = "GrossProfit")
    private String grossProfit;

    /**
     * 营业费用
     */
    @IncomeKeyAnnotation(enName = "Operating expense", zhName = "营业费用", value = "OperatingExpense")
    private String operatingExpense;

    /**
     * 销售和管理费用
     */
    @IncomeKeyAnnotation(enName = "Selling general and administration", zhName = "销售和管理费用", value = "SellingGeneralAndAdministration")
    private String sellingAndAdministrativeExpenses;

    /**
     * 销售费用
     */
    @IncomeKeyAnnotation(enName = "Selling and marketing expense", zhName = "销售费用", value = "SellingAndMarketingExpense")
    private String sellingAndMarketingExpense;

    /**
     * 管理费用
     */
    @IncomeKeyAnnotation(enName = "General and administrative expense", zhName = "管理费用", value = "GeneralAndAdministrativeExpense")
    private String generalAndAdministrativeExpense;

    /**
     * 研发费用
     */
    @IncomeKeyAnnotation(enName = "Research and development costs", zhName = "研发费用", value = "ResearchAndDevelopment")
    private String researchAndDevelopmentCosts;

    /**
     * 折旧摊销及损耗
     */
    @IncomeKeyAnnotation(enName = "Depreciation amortization depletion", zhName = "折旧摊销及损耗", value = "DepreciationAmortizationDepletion")
    private String depreciationAmortizationDepletion;

    /**
     * 折旧及摊销
     */
    @IncomeKeyAnnotation(enName = "Depreciation and amortization", zhName = "折旧及摊销", value = "DepreciationAndAmortization")
    private String depreciationAndAmortization;

    /**
     * 损耗
     */
    @IncomeKeyAnnotation(enName = "Depletion", zhName = "损耗", value = "Depletion")
    private String depletion;

    /**
     * 可疑账款准备金
     */
    @IncomeKeyAnnotation(enName = "Provision for doubtful accounts", zhName = "可疑账款准备金", value = "ProvisionForDoubtfulAccounts")
    private String provisionForDoubtfulAccounts;

    /**
     * 其他税费
     */
    @IncomeKeyAnnotation(enName = "Other taxes", zhName = "其他税费", value = "OtherTaxes")
    private String otherTaxes;

    /**
     * 其他营业费用
     */
    @IncomeKeyAnnotation(enName = "Other operating expenses", zhName = "其他营业费用", value = "OtherOperatingExpenses")
    private String otherOperatingExpenses;

    /**
     * 营业利润
     */
    @IncomeKeyAnnotation(enName = "Operating profit", zhName = "营业利润", value = "OperatingIncome")
    private String operatingProfit;

    /**
     * 净非营业利息收入（费用）
     */
    @IncomeKeyAnnotation(enName = "Net non-operating interest income expense", zhName = "净非营业利息收入（费用）", value = "NetNonOperatingInterestIncomeExpense")
    private String netNonOperatingInterestIncomeExpense;

    /**
     * 利息收入
     */
    @IncomeKeyAnnotation(enName = "Non-operating interest income", zhName = "利息收入", value = "InterestIncomeNonOperating")
    private String nonOperatingInterestIncome;

    /**
     * 利息费用
     */
    @IncomeKeyAnnotation(enName = "Interest expense-non operating", zhName = "利息费用", value = "InterestExpenseNonOperating")
    private String nonOperatingInterestExpense;

    /**
     * 其他财务费用
     */
    @IncomeKeyAnnotation(enName = "Total other finance cost", zhName = "其他财务费用", value = "TotalOtherFinanceCost")
    private String totalOtherFinanceCost;

    /**
     * 其他净收入（费用）
     */
    @IncomeKeyAnnotation(enName = "Other income expense", zhName = "其他净收入（费用）", value = "OtherIncomeExpense")
    private String otherNetIncome;

    /**
     * 出售证券收益
     */
    @IncomeKeyAnnotation(enName = "Gain on sale of security", zhName = "出售证券收益", value = "GainOnSaleOfSecurity")
    private String gainOnSaleOfSecurity;

    /**
     * 股权收益
     */
    @IncomeKeyAnnotation(enName = "Earnings from equity interest", zhName = "股权收益", value = "EarningsFromEquityInterest")
    private String earningsFromEquityInterest;

    /**
     * 证券摊销
     */
    @IncomeKeyAnnotation(enName = "Amortization of securities", zhName = "证券摊销", value = "AmortizationOfSecurities")
    private String amortizationOfSecurities;

    /**
     * 特殊收入（费用）
     */
    @IncomeKeyAnnotation(enName = "Special income (charges)", zhName = "特殊收入（费用）", value = "SpecialIncomeCharges")
    private String specialIncome;

    /**
     * 减：重组与并购
     */
    @IncomeKeyAnnotation(enName = "Less:Restructuring and mergern&acquisition", zhName = "减：重组与并购", value = "RestructuringAndMergernAcquisition")
    private String restructuringAndMergerAcquisition;

    /**
     * 减：资本性资产减值
     */
    @IncomeKeyAnnotation(enName = "Less:Impairment of capital assets", zhName = "减：资本性资产减值", value = "ImpairmentOfCapitalAssets")
    private String impairmentOfCapitalAssets;

    /**
     * 减：其他特殊费用
     */
    @IncomeKeyAnnotation(enName = "Less：Other special charges", zhName = "减：其他特殊费用", value = "OtherSpecialCharges")
    private String otherSpecialCharges;

    /**
     * 减：勾销
     */
    @IncomeKeyAnnotation(enName = "Write off", zhName = "减：勾销", value = "WriteOff")
    private String writeOff;

    /**
     * 业务出售收益
     */
    @IncomeKeyAnnotation(enName = "Gain on sale of business", zhName = "业务出售收益", value = "GainOnSaleOfBusiness")
    private String gainOnSaleOfBusiness;

    /**
     * 固定资产出售收益
     */
    @IncomeKeyAnnotation(enName = "Gain on sale of property,plant,equipment", zhName = "固定资产出售收益", value = "GainOnSaleOfPPE")
    private String gainOnSaleOfPropertyPlantEquipment;

    /**
     * 其他非经营收入（费用）
     */
    @IncomeKeyAnnotation(enName = "Other non-operating income (expenses)", zhName = "其他非经营收入（费用）", value = "OtherNonOperatingIncomeExpenses")
    private String otherNonOperatingIncome;

    /**
     * 税前利润
     */
    @IncomeKeyAnnotation(enName = "Income before tax", zhName = "税前利润", value = "PretaxIncome")
    private String incomeBeforeTax;

    /**
     * 所得税
     */
    @IncomeKeyAnnotation(enName = "Income tax", zhName = "所得税", value = "TaxProvision")
    private String incomeTax;

    /**
     * 除税后的权益收益
     */
    @IncomeKeyAnnotation(enName = "Earnings from equity interest net of tax", zhName = "除税后的权益收益", value = "EarningsFromEquityInterestNetOfTax")
    private String earningsFromEquityInterestNetOfTax;

    /**
     * 除税后利润
     */
    @IncomeKeyAnnotation(enName = "Net income", zhName = "除税后利润", value = "NetIncomeIncludingNoncontrollingInterests")
    private String netIncome;

    /**
     * 持续经营利润
     */
    @IncomeKeyAnnotation(enName = "Net Income continuous operations", zhName = "持续经营利润", value = "NetIncomeContinuousOperations")
    private String netIncomeContinuousOperations;

    /**
     * 停止经营利润
     */
    @IncomeKeyAnnotation(enName = "Net income discontinuous operations", zhName = "停止经营利润", value = "NetIncomeDiscontinuousOperations")
    private String netIncomeDiscontinuousOperations;

    /**
     * 反常净利润
     */
    @IncomeKeyAnnotation(enName = "Net income extraordinary", zhName = "反常净利润", value = "NetIncomeExtraordinary")
    private String netIncomeExtraordinary;

    /**
     * 会计变更的累计影响
     */
    @IncomeKeyAnnotation(enName = "Cumulative effect of accounting change", zhName = "会计变更的累计影响", value = "CumulativeEffectOfAccountingChange")
    private String cumulativeEffectOfAccountingChange;

    /**
     * 税项亏损所得净额结转
     */
    @IncomeKeyAnnotation(enName = "Net income from tax loss carry forward", zhName = "税项亏损所得净额结转", value = "NetIncomeFromTaxLossCarryforward")
    private String netIncomeFromTaxLossCarryForward;

    /**
     * 其他净损益
     */
    @IncomeKeyAnnotation(enName = "Net Income from other gains losses", zhName = "其他净损益", value = "NetIncomeFromOtherGainsLosses")
    private String netIncomeFromOtherGainsLosses;

    /**
     * 归属于少数股东的净利润
     */
    @IncomeKeyAnnotation(enName = "Minority interest income", zhName = "归属于少数股东的净利润", value = "MinorityInterests")
    private String minorityInterestIncome;

    /**
     * 归属于母公司的净利润
     */
    @IncomeKeyAnnotation(enName = "Net income attributable to the parent company", zhName = "归属于母公司的净利润", value = "NetIncome")
    private String netIncomeAttributableToTheParentCompany;

    /**
     * 优先股派息
     */
    @IncomeKeyAnnotation(enName = "Preferred stock dividends", zhName = "优先股派息", value = "PreferredStockDividends")
    private String preferredStockDividends;

    /**
     * 其他优先股派息
     */
    @IncomeKeyAnnotation(enName = "Other  preferred stock dividend", zhName = "其他优先股派息", value = "OtherunderPreferredStockDividend")
    private String otherPreferredStockDividends;

    /**
     * 归属于普通股股东的净利润
     */
    @IncomeKeyAnnotation(enName = "Net income attributable to  common stockholders", zhName = "归属于普通股股东的净利润", value = "NetIncomeCommonStockholders")
    private String netIncomeAttributableToCommonStockholders;

    /**
     * 基本每股收益
     */
    @IncomeKeyAnnotation(enName = "Basic earnings per share", zhName = "基本每股收益", value = "BasicEPS")
    private String basicEarningsPerShare;

    /**
     * 稀释每股收益
     */
    @IncomeKeyAnnotation(enName = "Diluted earnings per share", zhName = "稀释每股收益", value = "DilutedEPS")
    private String dilutedEarningsPerShare;

    /**
     * 每股派息
     */
    @IncomeKeyAnnotation(enName = "Dividend per share", zhName = "每股派息", value = "DividendPerShare")
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