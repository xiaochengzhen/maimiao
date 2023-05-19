package com.example.javabase.stock.model;/**
 * @description
 * @author xiaobo
 * @date 2023/5/19 13:38
 */

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description
 * @author xiaobo
 * @date 2023/5/19 13:38
 */
@NoArgsConstructor
@Data
public class StockMsg {

    @JSONField(name = "ResultCode")
    private String resultCode;
    @JSONField(name = "ResultNum")
    private String resultNum;
    @JSONField(name = "Result")
    private List<ResultDTO> result;
    @JSONField(name = "QueryID")
    private String queryID;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        @JSONField(name = "ResultURL")
        private String resultURL;
        @JSONField(name = "Weight")
        private String weight;
        @JSONField(name = "SrcID")
        private String srcID;
        @JSONField(name = "ClickNeed")
        private String clickNeed;
        @JSONField(name = "SubResult")
        private List<?> subResult;
        @JSONField(name = "SubResNum")
        private String subResNum;
        @JSONField(name = "Sort")
        private String sort;
        @JSONField(name = "RecoverCacheTime")
        private String recoverCacheTime;
        @JSONField(name = "DisplayData")
        private DisplayDataDTO displayData;
        @JSONField(name = "OriginSrcID")
        private String originSrcID;

        @NoArgsConstructor
        @Data
        public static class DisplayDataDTO {
            @JSONField(name = "StdStg")
            private String stdStg;
            @JSONField(name = "StdStl")
            private String stdStl;
            @JSONField(name = "strategy")
            private StrategyDTO strategy;
            @JSONField(name = "resultData")
            private ResultDataDTO resultData;

            @NoArgsConstructor
            @Data
            public static class StrategyDTO {
                @JSONField(name = "tempName")
                private String tempName;
                @JSONField(name = "precharge")
                private String precharge;
                @JSONField(name = "ctplOrPhp")
                private String ctplOrPhp;
                @JSONField(name = "hilightWord")
                private String hilightWord;
            }

            @NoArgsConstructor
            @Data
            public static class ResultDataDTO {
                @JSONField(name = "tplData")
                private TplDataDTO tplData;
                @JSONField(name = "extData")
                private ExtDataDTO extData;

                @NoArgsConstructor
                @Data
                public static class TplDataDTO {
                    @JSONField(name = "cardName")
                    private String cardName;
                    @JSONField(name = "templateName")
                    private String templateName;
                    @JSONField(name = "StdStg")
                    private String stdStg;
                    @JSONField(name = "StdStl")
                    private String stdStl;
                    @JSONField(name = "title")
                    private String title;
                    @JSONField(name = "result")
                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1 result;
                    @JSONField(name = "ResultURL")
                    private String resultURL;
                    @JSONField(name = "sigma_use")
                    private String sigmaUse;
                    @JSONField(name = "normal_use")
                    private String normalUse;
                    @JSONField(name = "weak_use")
                    private String weakUse;
                    @JSONField(name = "strong_use")
                    private String strongUse;
                    @JSONField(name = "pk")
                    private List<?> pk;
                    @JSONField(name = "encoding")
                    private String encoding;
                    @JSONField(name = "card_order")
                    private String cardOrder;
                    @JSONField(name = "disp_data_url_ex")
                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.DispDataUrlExDTO dispDataUrlEx;
                    @JSONField(name = "data_source")
                    private String dataSource;

                    @NoArgsConstructor
                    @Data
                    public static class ResultDTO1 {
                        @JSONField(name = "code")
                        private String code;
                        @JSONField(name = "name")
                        private String name;
                        @JSONField(name = "market")
                        private String market;
                        @JSONField(name = "tabs")
                        private List<ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO> tabs;
                        @JSONField(name = "selectTab")
                        private String selectTab;

                        @NoArgsConstructor
                        @Data
                        public static class TabsDTO {
                            @JSONField(name = "code")
                            private String code;
                            @JSONField(name = "stockName")
                            private String stockName;
                            @JSONField(name = "market")
                            private String market;
                            @JSONField(name = "content")
                            private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO content;
                            @JSONField(name = "name")
                            private String name;
                            @JSONField(name = "type")
                            private String type;
                            @JSONField(name = "ajaxUrl")
                            private String ajaxUrl;

                            @NoArgsConstructor
                            @Data
                            public static class ContentDTO {
                                @JSONField(name = "newCompany")
                                private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO newCompany;
                                @JSONField(name = "companyInfo")
                                private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO companyInfo;

                                @NoArgsConstructor
                                @Data
                                public static class NewCompanyDTO {
                                    @JSONField(name = "basicInfo")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.BasicInfoDTO basicInfo;
                                    @JSONField(name = "holderEquity")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.HolderEquityDTO holderEquity;
                                    @JSONField(name = "executiveInfo")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.ExecutiveInfoDTO executiveInfo;
                                    @JSONField(name = "bonusTransfer")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.BonusTransferDTO bonusTransfer;

                                    @NoArgsConstructor
                                    @Data
                                    public static class BasicInfoDTO {
                                        @JSONField(name = "title")
                                        private String title;
                                        @JSONField(name = "companyCode")
                                        private String companyCode;
                                        @JSONField(name = "innerCode")
                                        private String innerCode;
                                        @JSONField(name = "industry")
                                        private List<ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.BasicInfoDTO.IndustryDTO> industry;
                                        @JSONField(name = "companyName")
                                        private String companyName;
                                        @JSONField(name = "releaseDate")
                                        private String releaseDate;
                                        @JSONField(name = "chairman")
                                        private String chairman;
                                        @JSONField(name = "website")
                                        private String website;
                                        @JSONField(name = "mainBusiness")
                                        private String mainBusiness;
                                        @JSONField(name = "totalShares")
                                        private String totalShares;
                                        @JSONField(name = "hkShares")
                                        private String hkShares;

                                        @NoArgsConstructor
                                        @Data
                                        public static class IndustryDTO {
                                            @JSONField(name = "text")
                                            private String text;
                                            @JSONField(name = "url")
                                            private String url;
                                            @JSONField(name = "webStatusUrlUrl_xcx_params")
                                            private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.BasicInfoDTO.IndustryDTO.WebStatusUrlUrlXcxParamsDTO webstatusurlurlXcxParams;
                                            @JSONField(name = "webStatusUrl")
                                            private String webStatusUrl;

                                            @NoArgsConstructor
                                            @Data
                                            public static class WebStatusUrlUrlXcxParamsDTO {
                                                @JSONField(name = "xcx_appkey")
                                                private String xcxAppkey;
                                                @JSONField(name = "xcx_from")
                                                private String xcxFrom;
                                                @JSONField(name = "xcx_path")
                                                private String xcxPath;
                                                @JSONField(name = "xcx_query")
                                                private String xcxQuery;
                                            }
                                        }
                                    }

                                    @NoArgsConstructor
                                    @Data
                                    public static class HolderEquityDTO {
                                        @JSONField(name = "title")
                                        private String title;
                                        @JSONField(name = "url")
                                        private String url;
                                        @JSONField(name = "ajaxUrl")
                                        private String ajaxUrl;
                                        @JSONField(name = "header")
                                        private List<String> header;
                                        @JSONField(name = "body")
                                        private List<ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.HolderEquityDTO.BodyDTO> body;

                                        @NoArgsConstructor
                                        @Data
                                        public static class BodyDTO {
                                            @JSONField(name = "holder")
                                            private String holder;
                                            @JSONField(name = "EquityVolume")
                                            private String equityVolume;
                                            @JSONField(name = "holdNum")
                                            private String holdNum;
                                            @JSONField(name = "holdPer")
                                            private String holdPer;
                                            @JSONField(name = "holdChange")
                                            private String holdChange;
                                            @JSONField(name = "status")
                                            private String status;
                                            @JSONField(name = "date")
                                            private String date;
                                        }
                                    }

                                    @NoArgsConstructor
                                    @Data
                                    public static class ExecutiveInfoDTO {
                                        @JSONField(name = "title")
                                        private String title;
                                        @JSONField(name = "header")
                                        private List<String> header;
                                        @JSONField(name = "body")
                                        private List<ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.NewCompanyDTO.ExecutiveInfoDTO.BodyDTO> body;

                                        @NoArgsConstructor
                                        @Data
                                        public static class BodyDTO {
                                            @JSONField(name = "executive")
                                            private String executive;
                                            @JSONField(name = "post")
                                            private String post;
                                        }
                                    }

                                    @NoArgsConstructor
                                    @Data
                                    public static class BonusTransferDTO {
                                        @JSONField(name = "title")
                                        private String title;
                                        @JSONField(name = "header")
                                        private List<String> header;
                                        @JSONField(name = "body")
                                        private List<List<String>> body;
                                    }
                                }

                                @NoArgsConstructor
                                @Data
                                public static class CompanyInfoDTO {
                                    @JSONField(name = "StdStg")
                                    private String stdStg;
                                    @JSONField(name = "StdStl")
                                    private String stdStl;
                                    @JSONField(name = "_update_time")
                                    private String updateTime;
                                    @JSONField(name = "loc")
                                    private String loc;
                                    @JSONField(name = "lastmod")
                                    private String lastmod;
                                    @JSONField(name = "updateTime")
                                    private String updateTime1;
                                    @JSONField(name = "code")
                                    private String code;
                                    @JSONField(name = "marketCode")
                                    private String marketCode;
                                    @JSONField(name = "financeValue")
                                    private String financeValue;
                                    @JSONField(name = "secuCategory")
                                    private String secuCategory;
                                    @JSONField(name = "id")
                                    private String id;
                                    @JSONField(name = "category")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.CategoryDTO category;
                                    @JSONField(name = "ipoInfo")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.IpoInfoDTO ipoInfo;
                                    @JSONField(name = "targetFlag")
                                    private String targetFlag;
                                    @JSONField(name = "ignoreCheck")
                                    private String ignoreCheck;
                                    @JSONField(name = "tradingDay")
                                    private String tradingDay;
                                    @JSONField(name = "provider")
                                    private String provider;
                                    @JSONField(name = "plates")
                                    private List<ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.PlatesDTO> plates;
                                    @JSONField(name = "type")
                                    private String type;
                                    @JSONField(name = "status")
                                    private String status;
                                    @JSONField(name = "securityValue")
                                    private String securityValue;
                                    @JSONField(name = "sname")
                                    private String sname;
                                    @JSONField(name = "clusterName")
                                    private String clusterName;
                                    @JSONField(name = "siteinfo")
                                    private String siteinfo;
                                    @JSONField(name = "marketMIC")
                                    private String marketMIC;
                                    @JSONField(name = "categoryLvOne")
                                    private String categoryLvOne;
                                    @JSONField(name = "rloc")
                                    private String rloc;
                                    @JSONField(name = "name")
                                    private String name;
                                    @JSONField(name = "issuedBy")
                                    private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.IssuedByDTO issuedBy;
                                    @JSONField(name = "categoryLvTwo")
                                    private String categoryLvTwo;
                                    @JSONField(name = "SiteId")
                                    private String siteId;
                                    @JSONField(name = "_version")
                                    private String version;
                                    @JSONField(name = "_select_time")
                                    private String selectTime;
                                    @JSONField(name = "title")
                                    private String title;
                                    @JSONField(name = "industry")
                                    private String industry;
                                    @JSONField(name = "intro")
                                    private String intro;
                                    @JSONField(name = "area")
                                    private String area;
                                    @JSONField(name = "majorbiz")
                                    private String majorbiz;
                                    @JSONField(name = "listingdate")
                                    private String listingdate;
                                    @JSONField(name = "stockType")
                                    private String stockType;
                                    @JSONField(name = "url")
                                    private String url;
                                    @JSONField(name = "sfUrl")
                                    private String sfUrl;
                                    @JSONField(name = "style")
                                    private String style;

                                    @NoArgsConstructor
                                    @Data
                                    public static class CategoryDTO {
                                        @JSONField(name = "categoryLv1")
                                        private List<String> categoryLv1;
                                        @JSONField(name = "categoryLv1#num#baidu")
                                        private String _$CategoryLv1NumBaidu252;// FIXME check this code
                                        @JSONField(name = "categoryLv2")
                                        private List<String> categoryLv2;
                                        @JSONField(name = "categoryLv2#num#baidu")
                                        private String _$CategoryLv2NumBaidu17;// FIXME check this code
                                    }

                                    @NoArgsConstructor
                                    @Data
                                    public static class IpoInfoDTO {
                                        @JSONField(name = "publishProspectus")
                                        private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.IpoInfoDTO.PublishProspectusDTO publishProspectus;
                                        @JSONField(name = "endSubscription")
                                        private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.IpoInfoDTO.EndSubscriptionDTO endSubscription;
                                        @JSONField(name = "startSubscription")
                                        private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.IpoInfoDTO.StartSubscriptionDTO startSubscription;
                                        @JSONField(name = "listing")
                                        private ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO.CompanyInfoDTO.IpoInfoDTO.ListingDTO listing;

                                        @NoArgsConstructor
                                        @Data
                                        public static class PublishProspectusDTO {
                                            @JSONField(name = "status")
                                            private String status;
                                            @JSONField(name = "releaseDate")
                                            private String releaseDate;
                                            @JSONField(name = "subscriptionPrice")
                                            private String subscriptionPrice;
                                            @JSONField(name = "name")
                                            private String name;
                                            @JSONField(name = "currency")
                                            private String currency;
                                        }

                                        @NoArgsConstructor
                                        @Data
                                        public static class EndSubscriptionDTO {
                                            @JSONField(name = "status")
                                            private String status;
                                            @JSONField(name = "releaseDate")
                                            private String releaseDate;
                                            @JSONField(name = "subscriptionPrice")
                                            private String subscriptionPrice;
                                            @JSONField(name = "name")
                                            private String name;
                                            @JSONField(name = "currency")
                                            private String currency;
                                        }

                                        @NoArgsConstructor
                                        @Data
                                        public static class StartSubscriptionDTO {
                                            @JSONField(name = "status")
                                            private String status;
                                            @JSONField(name = "releaseDate")
                                            private String releaseDate;
                                            @JSONField(name = "equityIssuance")
                                            private String equityIssuance;
                                            @JSONField(name = "name")
                                            private String name;
                                            @JSONField(name = "currency")
                                            private String currency;
                                        }

                                        @NoArgsConstructor
                                        @Data
                                        public static class ListingDTO {
                                            @JSONField(name = "status")
                                            private String status;
                                            @JSONField(name = "releaseDate")
                                            private String releaseDate;
                                            @JSONField(name = "name")
                                            private String name;
                                        }
                                    }

                                    @NoArgsConstructor
                                    @Data
                                    public static class IssuedByDTO {
                                        @JSONField(name = "industry")
                                        private String industry;
                                        @JSONField(name = "mainBusiness")
                                        private String mainBusiness;
                                        @JSONField(name = "description")
                                        private String description;
                                        @JSONField(name = "area")
                                        private String area;
                                    }

                                    @NoArgsConstructor
                                    @Data
                                    public static class PlatesDTO {
                                        @JSONField(name = "code")
                                        private String code;
                                        @JSONField(name = "name")
                                        private String name;
                                    }
                                }
                            }
                        }
                    }

                    @NoArgsConstructor
                    @Data
                    public static class DispDataUrlExDTO {
                        @JSONField(name = "aesplitid")
                        private String aesplitid;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class ExtDataDTO {
                    @JSONField(name = "tplt")
                    private String tplt;
                    @JSONField(name = "OriginQuery")
                    private String originQuery;
                    @JSONField(name = "resourceid")
                    private String resourceid;
                }
            }
        }
    }
}
