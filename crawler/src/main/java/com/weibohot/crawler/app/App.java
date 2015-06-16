package com.weibohot.crawler.app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.weibohot.crawler.HotNewsDetailCrawler;
import com.weibohot.crawler.HotNewsListCrawler;
import com.weibohot.crawler.entity.HotNewsDetail;
import com.weibohot.crawler.entity.HotNewsTitle;
import com.weibohot.crawler.scheduler.ExecutionContext;
import com.weibohot.crawler.util.DBUtil;

public class App {

    private final ExecutionContext context = new ExecutionContext();

    public static void main(final String[] args) {
        final App app = new App();
        app.scheduler(60 * 1000 * 20);
    }

    public void scheduler(final long time) {
        this.context.scheduler.scheduleWithFixedDelay(() -> this.runOnce(), 0, time,
                TimeUnit.MILLISECONDS);
    }

    private void runOnce() {
        System.out.println(LocalDateTime.now().toString() + " runs");
        final HotNewsListCrawler listCrawler = new HotNewsListCrawler();
        List<HotNewsTitle> titles;
        try {
            titles = listCrawler.fetchContent();
        } catch (final IOException e1) {
            e1.printStackTrace();
            return;
        }
        final List<Future<Optional<HotNewsDetail>>> fs = new ArrayList<>(titles.size());
        for (final HotNewsTitle title : titles) {
            fs.add(this.context.taskExecutor.submit(() -> {
                try {
                    return Optional.<HotNewsDetail> of(new HotNewsDetailCrawler(title.url)
                    .fetchContent().get(0));
                } catch (final Exception e) {
                    e.printStackTrace();
                    return Optional.<HotNewsDetail> empty();
                }
            }));
        }
        for (int i = 0; i < titles.size(); i++) {
            final HotNewsTitle title = titles.get(i);
            try {
                final Optional<HotNewsDetail> detailOp = fs.get(i).get();
                if (detailOp.isPresent()) {
                    final HotNewsDetail detail = detailOp.get();
                    final long typeId = DBUtil.insertOrIgnoreType(title.type);
                    DBUtil.insertNews(title.tagName, detail.introduction, detail.readNum,
                            detail.discussNum, detail.focusNum, typeId);
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

    }
}
