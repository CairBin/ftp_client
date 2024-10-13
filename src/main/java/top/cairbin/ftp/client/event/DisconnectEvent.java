/*
 * @Description: 关闭连接事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:42:45
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:03:55
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;
import top.cairbin.ftp.client.listener.CustomEvent;

public class DisconnectEvent extends CustomEvent{

    public DisconnectEvent(Object source, String name) {
        super(source, name);
    }
    
}
