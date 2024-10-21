/*
 * @Description: 线程池接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-21 23:54:46
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-21 23:55:09
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.thread;

public interface IThreadPool {
    /**
     * @description: 提交任务到任务队列
     * @param {Task} task 任务
     * @return {*}
     */    
    public void submit(ITask task);



    /**
     * @description: 关闭线程池，所有任务执行完成后关闭所有线程
     * @return {*}
     */
    public void shutdown();
}