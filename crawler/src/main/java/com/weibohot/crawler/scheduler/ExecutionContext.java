package com.weibohot.crawler.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class ExecutionContext {

    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime()
            .availableProcessors());

    public final ExecutorService taskExecutor = Executors.newWorkStealingPool();
}
