/*
 * @Description: 线程池实现
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-21 23:57:05
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 00:00:51
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.thread;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import top.cairbin.ftp.logger.ILogger;

public class ThreadPool implements IThreadPool{
    private final List<Worker> workers;
    private final TaskQueue taskQueue;

    @Inject
    ILogger logger;

    /**
     * @description: 构造函数，创建线程池
     * @param {int} poolSize 线程池中线程数量
     */
    public ThreadPool(int poolSize) {
        this.workers = new ArrayList<>();
        this.taskQueue = new TaskQueue();
        logger.info("[ThreadPool] Initializing thread pool. The pool size is " + poolSize  + " .");
        
        // 初始化线程池，创建指定数量的工作线程
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(taskQueue);
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * @description: 构造函数，使用系统默认线程的两倍数创建线程池
     */
    public ThreadPool(){
        this(Runtime.getRuntime().availableProcessors()*2);
    }

    /**
     * @description: 提交任务到任务队列
     * @param {Task} task 任务
     * @return {*}
     */    
    @Override
    public void submit(ITask task) {
        taskQueue.addTask(task);
    }

    /**
     * @description: 关闭线程池，所有任务执行完成后关闭所有线程
     * @return {*}
     */
    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            logger.info("[ThreadPool] Shutting down worker " + worker.getId() + " .");
            worker.shutdown();
        }
    }
}
