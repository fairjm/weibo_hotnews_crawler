package com.weibohot.crawler;

import java.util.List;

import org.jsoup.nodes.Document;

/**
 *
 * 解析文本内容
 *
 * @param <T>
 *            返回数据的类型
 */
public interface Parser<T> {

    public List<T> parse(Document content);
}
