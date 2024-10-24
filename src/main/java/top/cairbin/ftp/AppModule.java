/*
 * @Description: 配置DI
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 17:27:08
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 02:23:44
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.DataSocket;
import top.cairbin.ftp.client.FtpClient;
import top.cairbin.ftp.client.IFtpClient;
import top.cairbin.ftp.client.listener.EventSource;
import top.cairbin.ftp.client.listener.IEventSource;
import top.cairbin.ftp.client.listener.IListenerRegistry;
import top.cairbin.ftp.client.listener.ListenerRegistry;
import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.logger.Log4jLogger;
import top.cairbin.ftp.socket.ISocketClient;
import top.cairbin.ftp.socket.SocketClient;
import top.cairbin.ftp.thread.IThreadPool;
import top.cairbin.ftp.thread.ThreadPool;

public class AppModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(ISocketClient.class)
            .to(SocketClient.class);
        
        bind(ControlSocket.class);

        bind(DataSocket.class);
        
        bind(IEventSource.class)
            .to(EventSource.class);
        
        bind(IListenerRegistry.class)
            .to(ListenerRegistry.class);

        bind(ILogger.class)
            .to(Log4jLogger.class)
            .asEagerSingleton();

        bind(IThreadPool.class)
            .to(ThreadPool.class)
            .asEagerSingleton();
        
        bind(IListenerRegistry.class)
            .to(ListenerRegistry.class);
    }

    @Provides
    @Singleton
    public IFtpClient getClient(ILogger logger, IListenerRegistry registry, ControlSocket client){
        return new FtpClient(logger, registry, client);
    }


}
