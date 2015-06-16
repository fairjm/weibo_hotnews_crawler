package com.weibohot.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.weibohot.server.entity.News;
import com.weibohot.server.util.DBUtil;

public class NewsDao {

    private NewsDao() {
    }

    public static final NewsDao instance = new NewsDao();

    public List<News> listLatestNews(final int newsCount) {
        try (Connection conn = DBUtil.getConnection()) {
            final String sql = "SELECT title,readnum,discussionnum,focusnum,created from hotnews order by newsid desc limit ?";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newsCount);
            final ResultSet rs = ps.executeQuery();
            final List<News> ns = new LinkedList<>();
            while (rs.next()) {
                final News n = new News();
                n.setTitle(rs.getString(1));
                n.setReadNum(rs.getLong(2));
                n.setDiscussNum(rs.getLong(3));
                n.setFocusNum(rs.getLong(4));
                n.setCreated(rs.getTimestamp(5));
                ns.add(n);
            }
            return ns;
        } catch (final Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void main(final String[] args) {
        final NewsDao dao = new NewsDao();
        System.out.println(dao.listLatestNews(10));
    }
}
