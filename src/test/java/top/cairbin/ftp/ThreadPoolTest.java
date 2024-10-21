/*
 * @Description: 线程池测试
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 00:11:41
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 00:20:47
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import org.junit.Test;

import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.thread.IThreadPool;

public class ThreadPoolTest {
    private final ILogger logger;

    public ThreadPoolTest() {
        this.logger = InjectorFactory.getInjector().getInstance(ILogger.class);
    }

    @Test
    public void testThreadPool() {
        IThreadPool threadPool = InjectorFactory.getInjector().getInstance(IThreadPool.class);
        for(int i = 0; i < 11; i++) {
            threadPool.submit(() -> {
                logger.info("Thread " + Thread.currentThread().getId() + " is running...");
            });
        }
    }
}