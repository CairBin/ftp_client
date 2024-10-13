/*
 * @Description: 获取当前工作目录
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 03:28:29
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:28:49
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import top.cairbin.ftp.client.listener.CustomEvent;

public class GetCurrentWorkingDirectoryEvent extends CustomEvent {

    public GetCurrentWorkingDirectoryEvent(Object source, String name) {
        super(source, name);
    }
    
}
