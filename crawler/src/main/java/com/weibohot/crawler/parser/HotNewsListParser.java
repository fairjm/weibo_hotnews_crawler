package com.weibohot.crawler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.weibohot.crawler.HotNewsListCrawler;
import com.weibohot.crawler.Parser;
import com.weibohot.crawler.entity.HotNewsTitle;
import com.weibohot.crawler.util.WeiboFormatUtil;

public final class HotNewsListParser implements Parser<HotNewsTitle> {

    public static final HotNewsListParser instance = new HotNewsListParser();

    private static final Pattern pattern = Pattern.compile("\\\"html\\\"\\:\\\"(.+)\\\"");

    private HotNewsListParser() {
    }

    @Override
    public List<HotNewsTitle> parse(final Document content) {
        final Optional<String> cleanedOption = this.cleanFormat(content);
        if (cleanedOption.isPresent()) {
            return this.extraData(cleanedOption.get());
        } else {
            return Collections.emptyList();
        }
    }

    private Optional<String> cleanFormat(final Document content) {
        final Element lst = content.select("script").last();
        final String html = lst.html();
        final Matcher m = HotNewsListParser.pattern.matcher(html);
        if (m.find()) {
            final String data = WeiboFormatUtil.cleanFormat(m.group(1));
            return Optional.of(data);
        } else {
            return Optional.empty();
        }
    }

    /**
     * 抽取数据
     *
     * @param content
     * @return
     */
    private List<HotNewsTitle> extraData(final String content) {
        final Document doc = Jsoup.parse(content);
        final Elements es = doc.select("div.W_autocut");
        if (es.isEmpty()) {
            return Collections.emptyList();
        } else {
            final List<HotNewsTitle> list = new ArrayList<>(es.size());
            for (final Element e : es) {
                final Elements nameElement = e.select("a.S_txt1");
                final String url = nameElement.attr("href").trim();
                final String tagName = nameElement.text().trim();
                final String type = e.select("a.W_btn_tag").text().trim();
                final HotNewsTitle newsTitle = new HotNewsTitle(tagName, url, type);
                list.add(newsTitle);
            }
            return list;
        }
    }

    public static void main(final String[] args) throws IOException {
        final HotNewsListCrawler crawler = new HotNewsListCrawler();
        System.out.println(HotNewsListParser.instance.parse(crawler.makeConnection().get()));
    }

}
