/*
 * @Description: 任务接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-21 23:54:09
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-21 23:54:29
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.thread;

@FunctionalInterface
public interface ITask {
    /**
     * @description: 执行任务
     * @return {*}
     */
    void execute();
}