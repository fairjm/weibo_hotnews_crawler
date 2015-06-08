package com.weibohot.crawler;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Hello world!
 *
 */
public final class Conf {

    private final static Config conf = ConfigFactory.load();

    public final static String url = Conf.conf.getString("hot.url");

    public final static String sub = Conf.conf.getString("hot.cookie.sub");

    public final static String pageSuffix = Conf.conf.getString("hot.page_suffix");
}
