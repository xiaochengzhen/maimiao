package com.example.reptile.webmagic.news.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/27 9:28
 */
@Data
public class NewsInfoDTO {

    private String headerPicture;
    private String headerContent;
    private String linkAddress;
    private String publisherTime;
    private String publisherName;

    public boolean isNotEmpty() {
        if (StringUtils.isEmpty(headerContent) || StringUtils.isEmpty(linkAddress) || StringUtils.isEmpty(publisherTime) || StringUtils.isEmpty(publisherName)) {
            return false;
        }
        return true;
    }

}
