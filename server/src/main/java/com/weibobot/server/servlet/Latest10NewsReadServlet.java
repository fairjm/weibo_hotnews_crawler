package com.weibobot.server.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibohot.server.dao.NewsDao;
import com.weibohot.server.entity.News;
import com.weibohot.server.util.DataFileGenerator;

/**
 * Servlet implementation class Latest10NewsReadServlet
 */
@WebServlet("/news/latest/readnum")
public class Latest10NewsReadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String name = "lastest_read_num";

    private final String title = "最新的新闻阅读量";

    // 10分钟过期一次
    private final int expiration = 1000 * 60 * 10;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String fileName = this.getServletContext().getRealPath("/generation/" + this.name);
        System.out.println(fileName);
        final Path filePath = Paths.get(fileName);
        final File file = filePath.toFile();
        this.refreshFile(file);
        request.setAttribute("data", this.name);
        request.setAttribute("title", this.title);
        request.getRequestDispatcher("/WEB-INF/jsp/bar.jsp").forward(request, response);
    }

    private synchronized void refreshFile(final File file) throws IOException {
        if (file.exists() && file.lastModified() > System.currentTimeMillis() - this.expiration) {
            return;
        }
        final List<News> newsList = NewsDao.instance.listLatestNews(10);
        final Map<String, String> m = new HashMap<String, String>(newsList.size(), 1);
        for (final News n : newsList) {
            m.put(n.getTitle(), n.getReadNum() + "");
        }
        DataFileGenerator.write(file, m);
    }
}
