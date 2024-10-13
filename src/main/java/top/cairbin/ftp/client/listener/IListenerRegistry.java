/*
 * @Description: Listener registry接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:31:54
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 02:40:37
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

public interface IListenerRegistry {
    /**
     * @description: 自动扫描Listener
     * @param {String} packageName 包名
     * @return {*}
     */    
    void registerListeners(String packageName);
    
    /**
     * @description: 获取消息源，用于触发事件或添加Listener
     * @return {*} 消息源
     */    
    IEventSource getEventSource();
}
