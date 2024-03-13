package com.example.reptile.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:49
 */
public class MyDownloader implements Downloader {
    @Override
    public Page download(Request request, Task task) {
        return null;
    }

    @Override
    public void setThread(int i) {

    }
}
