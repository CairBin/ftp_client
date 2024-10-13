/*
 * @Description: 数据连接单例工厂
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 04:44:56
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 04:46:24
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import top.cairbin.ftp.socket.ISocketClient;

@Singleton
public class DataSocket {
    private ISocketClient client;
    
    @Inject
    public DataSocket(ISocketClient client) {
        this.client = client;
    }

    public ISocketClient getDataSocket(){
        return client;
    }
}
