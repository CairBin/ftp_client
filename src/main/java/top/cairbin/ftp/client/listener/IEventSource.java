/*
 * @Description: 事件源接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:24:31
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:01:49
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;


public interface IEventSource {
    // 注册监听器
    void registerListener(ICustomEventListener listener);

    // 触发事件
    void triggerEvent(CustomEvent event);
}
