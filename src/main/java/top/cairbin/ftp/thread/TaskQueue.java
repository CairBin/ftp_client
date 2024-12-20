/*
 * @Description: 线程池队列
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-21 23:55:39
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-21 23:55:49
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.thread;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private final Queue<ITask> queue = new LinkedList<>();

    /**
     * @description: 向队列中添加任务
     * @param {Task} task 任务
     * @return {*}
     */    
    public synchronized void addTask(ITask task) {
        queue.offer(task);
        notifyAll(); // 唤醒等待任务的线程
    }

    /**
     * @description: 获取任务，队列为空时阻塞等待
     * @return {Task} 任务
     * @throws InterruptedException 任务队列为空时抛出
     */
    public synchronized ITask getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // 队列为空时等待任务
        }
        return queue.poll();
    }
}