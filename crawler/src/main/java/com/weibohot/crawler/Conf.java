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

    public static final class Db {
        public final static String url = Conf.conf.getString("db.url");
        public final static String user = Conf.conf.getString("db.user");
        public final static String pwd = Conf.conf.getString("db.pwd");
        public final static int initSize = Conf.conf.getInt("db.initSize");
        public final static int minIdle = Conf.conf.getInt("db.minIdle");
        public final static int maxActive = Conf.conf.getInt("db.maxActive");
        public final static String validateQuery = Conf.conf.getString("db.validateQuery");
    }
}
