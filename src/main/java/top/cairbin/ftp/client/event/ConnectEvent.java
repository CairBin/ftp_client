/*
 * @Description: 连接事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 01:41:21
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:03:18
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;
import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class ConnectEvent extends CustomEvent{
    private String host;
    private int port;

    public ConnectEvent(Object source, String name, String host, int port) {
        super(source, name);
        this.host = host;
        this.port = port;
    }
    
}
