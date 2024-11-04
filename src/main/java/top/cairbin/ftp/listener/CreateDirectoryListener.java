/*
 * @Description: 创建目录事件监听器
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 22:13:48
 * @Copyright: Copyright (c) 2024 zhirun zhang
               Copyright (c) 2024 Xinyi Liu(CairBin)
 */

package top.cairbin.ftp.listener;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.event.CreateDirectoryEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;






@Listener
public class CreateDirectoryListener implements ICustomEventListener {

    @Inject
    private ILogger logger;
    
    @Inject
    private ControlSocket control;

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("createDirectory"))
            return;

        CreateDirectoryEvent e = (CreateDirectoryEvent)event;
        String path = e.getPath();

        logger.debug("[CreatDirectoryListener] path: " + path);
        control.getControlSocket().getWriter().write("MKD " + path + "\r\n");
        control.getControlSocket().getWriter().flush();

        String message = control.getControlSocket().getReader().readLine();
        logger.debug("[CreateDirectoryListener] Server message: " + message);
        System.out.println(message);


    }
    
}
