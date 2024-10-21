/*
 * @Description: 建立控制连接
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 01:24:08
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 02:36:52
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.listener;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.event.ConnectEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.socket.SocketConfig;

@Listener
public class ConnectListener implements ICustomEventListener {
    @Inject
    private ControlSocket control;
    @Inject
    private ILogger logger;
    
    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("connect"))
            return;
        
        ConnectEvent e = (ConnectEvent)event;
        String host = e.getHost();
        int port = e.getPort();
        SocketConfig config = new SocketConfig();

        config.setEncode("UTF-8");
        config.setHost(host);
        config.setPort(port);
        logger.debug(String.format("[ConnectListener] Connecting to server %s:%d", host, port));
        control.getControlSocket().createSocket(config);
        logger.debug(String.format("[ConnectListener] Success to establish control connection."));

        String message = control.getControlSocket().getReader().readLine();
        System.out.println(message);
    }
    
}
