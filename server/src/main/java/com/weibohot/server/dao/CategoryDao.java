package com.weibohot.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.weibohot.server.entity.Category;
import com.weibohot.server.util.DBUtil;

public class CategoryDao {

    private CategoryDao() {

    }

    public static final CategoryDao instance = new CategoryDao();

    public List<Category> list24h() {
        try (Connection conn = DBUtil.getConnection()) {
            final LocalDate date = LocalDate.now().minusDays(1);
            final String sql = "SELECT categoryname,count(newsid) FROM category AS c "
                    + "LEFT JOIN hotnews AS h ON h.categoryid = c.categoryid "
                    + "where h.created >= ? " + "GROUP BY categoryname";
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date.toString());
            final ResultSet rs = ps.executeQuery();
            final List<Category> cs = new LinkedList<>();
            while (rs.next()) {
                final Category c = new Category();
                c.setCategoryName(rs.getString(1));
                c.setNewsCount(rs.getInt(2));
                cs.add(c);
            }
            return cs;
        } catch (final Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Category> listAll() {
        try (Connection conn = DBUtil.getConnection()) {
            final String sql = "SELECT categoryname,count(newsid) FROM category AS c "
                    + "LEFT JOIN hotnews AS h ON h.categoryid = c.categoryid "
                    + "GROUP BY categoryname";
            final PreparedStatement ps = conn.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            final List<Category> cs = new LinkedList<>();
            while (rs.next()) {
                final Category c = new Category();
                c.setCategoryName(rs.getString(1));
                c.setNewsCount(rs.getInt(2));
                cs.add(c);
            }
            return cs;
        } catch (final Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void main(final String[] args) {
        final CategoryDao dao = new CategoryDao();
        System.out.println(dao.listAll());
        System.out.println(dao.list24h());
    }
}
