/*
 * @Description: 退出事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 00:38:53
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 00:39:10
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import top.cairbin.ftp.client.listener.CustomEvent;

public class QuitEvent extends CustomEvent {
    
    public QuitEvent(Object source, String name) {
        super(source, name);
    }
    
}
