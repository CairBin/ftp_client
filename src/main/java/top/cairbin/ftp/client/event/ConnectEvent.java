/*
 * @Description: 连接事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 01:29:42
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class ConnectEvent extends CustomEvent {
    private int port;
    private String host;
    public ConnectEvent(Object source, String name, String host, int port) {
        super(source, name);
        this.port = port;
        this.host = host;
    }
    
}
