package com.weibohot.crawler.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.weibohot.crawler.Conf;

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

    private static Connection getConnection() throws SQLException {
        return DBUtil.dataSource.getConnection();
    }

    public static long insertOrIgnoreType(final String type) throws SQLException {
        final String trimed = type.trim();
        DBUtil.insertType(trimed);
        return DBUtil.getTypeId(trimed);
    }

    public static void insertNews(final String title, final String intro, final long readNum,
            final long discussionnum, final long focusNum, final long typeId) throws SQLException {
        final String sql = "INSERT INTO `hotnews`(`title`,`intro`,`readnum`,`discussionnum`,`focusnum`,`categoryid`)"
                + "VALUES (?,?,?,?,?,?) ON Duplicate key update intro=?,readnum=?,discussionnum=?,focusnum=?";
        try (Connection conn = DBUtil.getConnection()) {
            final PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, intro);
            statement.setLong(3, readNum);
            statement.setLong(4, discussionnum);
            statement.setLong(5, focusNum);
            statement.setLong(6, typeId);
            statement.setString(7, intro);
            statement.setLong(8, readNum);
            statement.setLong(9, discussionnum);
            statement.setLong(10, focusNum);
            statement.execute();
        }
    }

    private static void insertType(final String type) throws SQLException {
        final String sql = "insert ignore into category(categoryname) values(?)";
        try (Connection conn = DBUtil.getConnection()) {
            final PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, type);
            statement.execute();
        }
    }

    private static long getTypeId(final String type) throws SQLException {
        final String sql = "select categoryid from category where categoryname = ?";
        try (Connection conn = DBUtil.getConnection()) {
            final PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, type);
            try {
                final ResultSet rs = statement.executeQuery();
                rs.next();
                return rs.getLong(1);
            } catch (final Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    public static void main(final String[] args) throws SQLException {
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        System.out.println(DBUtil.insertOrIgnoreType("hello"));
        DBUtil.insertNews("testtest", "test", 111, 222, 111, 13);
    }
}
