package com.weibohot.crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {

    public static final Connection makeConnection(){
        return Jsoup.connect(Conf.url).cookie("SUB", Conf.sub);
    }

    //    public static final List<HotNews> fetchNews() throws IOException {
    //        final Connection newConn = Crawler.makeConnection();
    //        final Document doc = newConn.get();
    //        doc.select("script").last();
    //    }

    public static void main(final String[] args) throws IOException {
        final Connection newConn = Crawler.makeConnection();
        final Document doc = newConn.get();
        final Element lst = doc.select("script").last();
        System.out.println(lst.html());
    }
}
