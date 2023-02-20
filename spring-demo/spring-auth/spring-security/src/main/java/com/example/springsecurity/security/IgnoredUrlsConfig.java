package com.example.springsecurity.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/***
 */
@ConfigurationProperties(prefix = "ebang")
public class IgnoredUrlsConfig {
    private List<String> ignoreUri;

    public List<String> getIgnoreUri() {
        return ignoreUri;
    }

    public void setIgnoreUri(List<String> ignoreUri) {
        this.ignoreUri = ignoreUri;
    }
}
