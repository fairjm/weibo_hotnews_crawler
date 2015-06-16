package com.weibobot.server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibohot.server.dao.CategoryDao;
import com.weibohot.server.entity.Category;

@WebServlet("/category/all")
public class CategoryPieChartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final List<Category> all = CategoryDao.instance.listAll();
        request.setAttribute("data", all);
        request.setAttribute("24h", false);
        request.getRequestDispatcher("/WEB-INF/jsp/category_pie_chart.jsp").forward(request,
                response);
    }

}
