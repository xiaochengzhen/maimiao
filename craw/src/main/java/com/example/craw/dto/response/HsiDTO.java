package com.example.craw.dto.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/25 8:47
 */
@NoArgsConstructor
@Data
public class HsiDTO {

    @JSONField(name = "requestName")
    private String requestName;
    @JSONField(name = "requestDate")
    private String requestDate;
    @JSONField(name = "dateFormat")
    private String dateFormat;
    @JSONField(name = "duration")
    private String duration;
    @JSONField(name = "indexSeriesList")
    private List<IndexSeriesListDTO> indexSeriesList;
    @JSONField(name = "refreshFrequency")
    private Integer refreshFrequency;

    @NoArgsConstructor
    @Data
    public static class IndexSeriesListDTO {
        @JSONField(name = "seriesName")
        private String seriesName;
        @JSONField(name = "seriesCode")
        private String seriesCode;
        @JSONField(name = "indexList")
        private List<IndexListDTO> indexList;

        @NoArgsConstructor
        @Data
        public static class IndexListDTO {
            @JSONField(name = "indexName")
            private String indexName;
            @JSONField(name = "subIndexList")
            private List<SubIndexListDTO> subIndexList;

            @NoArgsConstructor
            @Data
            public static class SubIndexListDTO {
                @JSONField(name = "indexName")
                private String indexName;
                @JSONField(name = "isTableOpen")
                private Boolean isTableOpen;
                @JSONField(name = "constituentsCount")
                private Integer constituentsCount;
                @JSONField(name = "constituentTemplate")
                private String constituentTemplate;
                @JSONField(name = "constituentContent")
                private List<ConstituentContentDTO> constituentContent;

                @NoArgsConstructor
                @Data
                public static class ConstituentContentDTO {
                    @JSONField(name = "code")
                    private String code;
                }
            }
        }
    }

    public static void main(String[] args) {
        String ss = "<table class=\"wikitable sortable\" id=\"constituents\">\n" +
                "\n" +
                "                                <tbody>\n" +
                "                                <tr class=\"is-sticky\">\n" +
                "                                    <th><a href=\"/wiki/Ticker_symbol\" title=\"Ticker symbol\">Symbol</a>\n" +
                "                                    </th>\n" +
                "                                    <th>Security</th>\n" +
                "                                    <th><a href=\"/wiki/Global_Industry_Classification_Standard\"\n" +
                "                                           title=\"Global Industry Classification Standard\">GICS</a> Sector</th>\n" +
                "                                    <th>GICS Sub-Industry</th>\n" +
                "                                    <th>Headquarters Location</th>\n" +
                "                                    <th>Date added</th>\n" +
                "                                    <th><a href=\"/wiki/Central_Index_Key\" title=\"Central Index Key\">CIK</a></th>\n" +
                "                                    <th>Founded\n" +
                "                                    </th>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td><a rel=\"nofollow\" class=\"external text\"\n" +
                "                                           href=\"https://www.nyse.com/quote/XNYS:MMM\">MMM</a>\n" +
                "                                    </td>\n" +
                "                                    <td><a href=\"/wiki/3M\" title=\"3M\">3M</a></td>\n" +
                "                                    <td>Industrials</td>\n" +
                "                                    <td>Industrial Conglomerates</td>\n" +
                "                                    <td><a href=\"/wiki/Saint_Paul,_Minnesota\"\n" +
                "                                           title=\"Saint Paul, Minnesota\">Saint Paul, Minnesota</a></td>\n" +
                "                                    <td>1957-03-04</td>\n" +
                "                                    <td>0000066740</td>\n" +
                "                                    <td>1902\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td><a rel=\"nofollow\" class=\"external text\"\n" +
                "                                           href=\"https://www.nyse.com/quote/XNYS:AOS\">AOS</a>\n" +
                "                                    </td>\n" +
                "                                    <td><a href=\"/wiki/A._O._Smith\" title=\"A. O. Smith\">A. O. Smith</a></td>\n" +
                "                                    <td>Industrials</td>\n" +
                "                                    <td>Building Products</td>\n" +
                "                                    <td><a href=\"/wiki/Milwaukee,_Wisconsin\" class=\"mw-redirect\"\n" +
                "                                           title=\"Milwaukee, Wisconsin\">Milwaukee, Wisconsin</a></td>\n" +
                "                                    <td>2017-07-26</td>\n" +
                "                                    <td>0000091142</td>\n" +
                "                                    <td>1916\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td><a rel=\"nofollow\" class=\"external text\"\n" +
                "                                           href=\"https://www.nyse.com/quote/XNYS:ABT\">ABT</a>\n" +
                "                                    </td>\n" +
                "                                    <td><a href=\"/wiki/Abbott_Laboratories\"\n" +
                "                                           title=\"Abbott Laboratories\">Abbott</a></td>\n" +
                "                                    <td>Health Care</td>\n" +
                "                                    <td>Health Care Equipment</td>\n" +
                "                                    <td><a href=\"/wiki/North_Chicago,_Illinois\"\n" +
                "                                           title=\"North Chicago, Illinois\">North Chicago, Illinois</a></td>\n" +
                "                                    <td>1957-03-04</td>\n" +
                "                                    <td>0000001800</td>\n" +
                "                                    <td>1888\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td><a rel=\"nofollow\" class=\"external text\"\n" +
                "                                           href=\"https://www.nyse.com/quote/XNYS:ABBV\">ABBV</a>\n" +
                "                                    </td>\n" +
                "                                    <td><a href=\"/wiki/AbbVie\" title=\"AbbVie\">AbbVie</a></td>\n" +
                "                                    <td>Health Care</td>\n" +
                "                                    <td>Biotechnology</td>\n" +
                "                                    <td><a href=\"/wiki/North_Chicago,_Illinois\"\n" +
                "                                           title=\"North Chicago, Illinois\">North Chicago, Illinois</a></td>\n" +
                "                                    <td>2012-12-31</td>\n" +
                "                                    <td>0001551152</td>\n" +
                "                                    <td>2013 (1888)\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td><a rel=\"nofollow\" class=\"external text\"\n" +
                "                                           href=\"https://www.nyse.com/quote/XNYS:ACN\">ACN</a>\n" +
                "                                    </td>\n" +
                "                                    <td><a href=\"/wiki/Accenture\" title=\"Accenture\">Accenture</a></td>\n" +
                "                                    <td>Information Technology</td>\n" +
                "                                    <td>IT Consulting &amp; Other Services</td>\n" +
                "                                    <td><a href=\"/wiki/Dublin,_Ireland\" class=\"mw-redirect\"\n" +
                "                                           title=\"Dublin, Ireland\">Dublin, Ireland</a></td>\n" +
                "                                    <td>2011-07-06</td>\n" +
                "                                    <td>0001467373</td>\n" +
                "                                    <td>1989\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td><a rel=\"nofollow\" class=\"external text\"\n" +
                "                                           href=\"https://www.nyse.com/quote/XNYS:ZTS\">ZTS</a>\n" +
                "                                    </td>\n" +
                "                                    <td><a href=\"/wiki/Zoetis\" title=\"Zoetis\">Zoetis</a></td>\n" +
                "                                    <td>Health Care</td>\n" +
                "                                    <td>Pharmaceuticals</td>\n" +
                "                                    <td><a href=\"/wiki/Parsippany,_New_Jersey\" class=\"mw-redirect\"\n" +
                "                                           title=\"Parsippany, New Jersey\">Parsippany, New Jersey</a></td>\n" +
                "                                    <td>2013-06-21</td>\n" +
                "                                    <td>0001555280</td>\n" +
                "                                    <td>1952\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>";
        Document doc= Jsoup.parse(ss);
        Element constituents = doc.getElementById("constituents");
        Elements external_text = constituents.getElementsByClass("external text");
        if (!CollectionUtils.isEmpty(external_text)) {
            for (Element element : external_text) {
                System.out.println(element.childNode(0));
            }
        }
    }


}
