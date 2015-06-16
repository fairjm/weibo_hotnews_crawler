package com.weibohot.crawler.parser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.weibohot.crawler.HotNewsDetailCrawler;
import com.weibohot.crawler.Parser;
import com.weibohot.crawler.entity.HotNewsDetail;
import com.weibohot.crawler.util.NumberExtractor;
import com.weibohot.crawler.util.WeiboFormatUtil;

public class HotNewsDetailParser implements Parser<HotNewsDetail> {

    public static final HotNewsDetailParser instance = new HotNewsDetailParser();

    private static final Pattern pattern = Pattern.compile("\\\"html\\\"\\:\\\"(.+)\\\"");

    private HotNewsDetailParser() {
    }

    @Override
    public List<HotNewsDetail> parse(final Document content) {
        // System.out.println(content);
        final Optional<String> cleanedOption = this.cleanFormat(content);
        if (cleanedOption.isPresent()) {
            return this.extraData(cleanedOption.get());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 清楚js格式 得到html内容
     *
     * @param content
     * @return
     */
    private Optional<String> cleanFormat(final Document content) {
        try {
            final Elements scripts = content.select("script");
            Element intro = null;
            Element detail = null;
            for (final Element e : scripts) {
                final String html = e.html();
                if (html.contains("\"domid\":\"Pl_Core_T8CustomTriColumn__10\"")) {
                    detail = e;
                } else if (html.contains("\"domid\":\"Pl_Third_Inline__3\"")) {
                    intro = e;
                }
                if (detail != null && intro != null) {
                    break;
                }
            }
            if (intro == null || detail == null) {
                return Optional.empty();
            }

            final String introHtml = intro.html();
            final String detailHtml = detail.html();
            final Matcher introM = HotNewsDetailParser.pattern.matcher(introHtml);
            final Matcher detailM = HotNewsDetailParser.pattern.matcher(detailHtml);
            if (introM.find() && detailM.find()) {
                final String introData = WeiboFormatUtil.cleanFormat(introM.group(1));
                final String detailData = WeiboFormatUtil.cleanFormat(detailM.group(1));
                return Optional.of(introData + detailData);
            } else {
                return Optional.empty();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * 抽取数据
     *
     * @param content
     * @return
     */
    private List<HotNewsDetail> extraData(final String content) {
        try {
            final Document doc = Jsoup.parse(content);
            final Elements intro = doc.select("p");
            final Elements details = doc.select("strong");
            final long readNum = NumberExtractor.extract(details.get(0).text());
            final long discussNum = NumberExtractor.extract(details.get(1).text());
            final long focusNum = NumberExtractor.extract(details.get(2).text());

            final HotNewsDetail detail = new HotNewsDetail(intro.text(), readNum, discussNum,
                    focusNum);
            return Arrays.asList(detail);
        } catch (final Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void main(final String[] args) throws IOException {
        final HotNewsDetailCrawler crawler = new HotNewsDetailCrawler(
                "http://weibo.com/p/100808ee6557c398bc02cd6358607e54007151?from=faxian_huati");
        System.out.println(HotNewsDetailParser.instance.parse(crawler.makeConnection().get()));
    }
}
