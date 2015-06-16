package com.weibohot.server.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.weibohot.server.Conf;

public class DBUtil {

    private final static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        DBUtil.dataSource.setUrl(Conf.Db.url);
        DBUtil.dataSource.setUsername(Conf.Db.user);
        DBUtil.dataSource.setPassword(Conf.Db.pwd);
        DBUtil.dataSource.setInitialSize(Conf.Db.initSize);
        DBUtil.dataSource.setMinIdle(Conf.Db.minIdle);
        DBUtil.dataSource.setMaxActive(Conf.Db.maxActive);
        DBUtil.dataSource.setValidationQuery(Conf.Db.validateQuery);
    }

    public static Connection getConnection() throws SQLException {
        return DBUtil.dataSource.getConnection();
    }

}
