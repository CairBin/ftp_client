/*
 * @Description: 退出监听器
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 00:40:47
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 01:36:43
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.listener;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;

@Listener
public class QuitListener implements ICustomEventListener {
    @Inject
    private ILogger logger;

    @Inject
    private ControlSocket control;

    @Override
    public void onCustomEvent(CustomEvent event) {
        if(!event.getEventName().equals("quit"))
            return;
        logger.debug("[QuitListener] Quitting FTP client...");
        if(!control.getControlSocket().isClosed())
            control.getControlSocket().close();
        
        System.exit(0);
    }
    
}
