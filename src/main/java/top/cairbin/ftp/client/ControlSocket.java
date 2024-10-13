/*
 * @Description: 控制连接，容器返回，单例
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 01:48:12
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 01:52:45
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import top.cairbin.ftp.socket.ISocketClient;

@Singleton
public class ControlSocket {
    private ISocketClient client;

    @Inject
    public ControlSocket(ISocketClient client) {
        this.client = client;
    }

    public ISocketClient getControlSocket(){
        return client;
    }
}
