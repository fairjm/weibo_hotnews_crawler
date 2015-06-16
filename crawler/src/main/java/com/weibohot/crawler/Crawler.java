package com.weibohot.crawler;

import java.util.List;

import org.jsoup.Connection;

public interface Crawler<T, U> {

    Connection makeConnection();

    Parser<U> getParser();

    /**
     * 返回抓取数据
     *
     * @return
     */
    List<T> fetchContent() throws Exception;

}
