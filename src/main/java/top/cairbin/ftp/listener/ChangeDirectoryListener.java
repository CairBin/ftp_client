/*
 * @Description: 监听改变目录事件
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: zhirun zhang
 * @LastEditTime: 2024-10-22 01:29:42
 * @Copyright: Copyright (c) 2024 zhirun zhang
 */
package top.cairbin.ftp.listener;
import com.google.inject.Inject;



import top.cairbin.ftp.client.ControlSocket;

import top.cairbin.ftp.client.event.ChangeDirectoryEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;

@Listener
public class ChangeDirectoryListener implements ICustomEventListener {
   
    @Inject
    private ILogger logger;
    
    @Inject
    private ControlSocket control;
    
   

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("changeDirectory"))
            return;
        
        ChangeDirectoryEvent e = (ChangeDirectoryEvent)event;
        String sour = e.getSour();

        logger.debug("[ChangeDirectoryListener] source: " + sour);
        control.getControlSocket().getWriter().write("RNFR " + sour + "\r\n");
        control.getControlSocket().getWriter().flush();

        String message = control.getControlSocket().getReader().readLine();
        logger.debug("[ChangeDirectoryListener] Server message: " + message);
        System.out.println(message);
        
        String dest = e.getDest();
        
        logger.debug("[ChangeDirectoryListener] destination: " + dest);
        control.getControlSocket().getWriter().write("RNTO " + dest + "\r\n");
        control.getControlSocket().getWriter().flush();

        message = control.getControlSocket().getReader().readLine();
        logger.debug("[ChangeDirectoryListener] Server message: " + message);
        System.out.println(message);
       
       
       
    }

  

    
}


