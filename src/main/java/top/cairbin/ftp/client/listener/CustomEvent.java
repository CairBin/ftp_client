/*
 * @Description: 事件基类
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:59:27
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:58:26
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

import java.util.EventObject;

public class CustomEvent extends EventObject {
    private String eventName;

    public CustomEvent(Object source,String name) {
        super(source);
        this.eventName = name;
    }

    /**
     * @description: 获取事件名
     * @return {String} 事件名
     */    
    public String getEventName(){
        return eventName;
    }
    
}
