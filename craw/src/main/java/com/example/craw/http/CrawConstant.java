package com.example.craw.http;
/**
 * @description 
 * @author xiaobo
 * @date 2023/12/4 8:25
 */
public class CrawConstant {

    //周期：1=季度； 2=累计季度； 3=年度
    public static final int QUARTER_PERIOD = 1;
    public static final int ACCMULATED_QUARTER_PERIOD = 2;
    public static final int YEAR_PERIOD = 3;

    //港股=1； 美股=11
    public static final String MARKET_CODE_HK = "1";
    public static final String MARKET_CODE_US = "11";

    //港股=1； 美股=11
    public static final String MARKET_TYPE_HK = "1";
    public static final String MARKET_TYPE_US = "2";

    //4=公司主营地区； 8=公司主营业务 ； 11=财务指标年； 12=财务指标单季； 13=财务指标累季
    public static final String REGION_TYPE = "4";
    public static final String BUSINESS_TYPE = "8";

    //接口请求参数周期：12=季度； 13=累计季度； 11=年度
    public static final String YEAR_PERIOD_REQ = "11";
    public static final String QUARTER_PERIOD_REQ = "12";
    public static final String ACCMULATED_QUARTER_PERIOD_REQ = "13";

}
