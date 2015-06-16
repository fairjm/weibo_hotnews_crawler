package com.weibohot.crawler;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.weibohot.crawler.entity.HotNewsDetail;
import com.weibohot.crawler.parser.HotNewsDetailParser;

public final class HotNewsDetailCrawler implements Crawler<HotNewsDetail, HotNewsDetail> {

    public final String url;

    public HotNewsDetailCrawler(final String url) {
        this.url = url;
    }

    @Override
    public Connection makeConnection() {
        return Jsoup.connect(this.url).cookie("SUB", Conf.sub);
    }

    @Override
    public Parser<HotNewsDetail> getParser() {
        return HotNewsDetailParser.instance;
    }

    @Override
    public List<HotNewsDetail> fetchContent() throws Exception {
        return this.getParser().parse(this.makeConnection().get());
    }

}
