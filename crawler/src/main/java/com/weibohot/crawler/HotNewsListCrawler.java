package com.weibohot.crawler;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.weibohot.crawler.entity.HotNewsTitle;
import com.weibohot.crawler.parser.HotNewsListParser;

public final class HotNewsListCrawler implements Crawler<HotNewsTitle, HotNewsTitle> {

    @Override
    public Connection makeConnection() {
        return Jsoup.connect(Conf.url).cookie("SUB", Conf.sub);
    }

    @Override
    public Parser<HotNewsTitle> getParser() {
        return HotNewsListParser.instance;
    }

    @Override
    public List<HotNewsTitle> fetchContent() throws IOException {
        return this.getParser().parse(this.makeConnection().get());
    }
}
