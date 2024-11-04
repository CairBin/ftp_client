/*
 * @Description: 监听进入目录事件
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 21:38:45
 * @Copyright: Copyright (c) 2024 zhirun zhang
 *             Copyright (c) 2024 Xinyi Liu (CairBin)
 */
package top.cairbin.ftp.listener;
import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.event.EnterDirectoryEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;


@Listener
public class EnterDirectoryListener implements ICustomEventListener {
   
    @Inject
    private ILogger logger;
    
    @Inject
    private ControlSocket control;

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("enterDirectory"))
            return;
        EnterDirectoryEvent e = (EnterDirectoryEvent)event;
        String path = e.getPath();

        logger.debug("[EnterDirectoryListener] path: " + path);
        if(path == null || path.isEmpty()){
            logger.debug("[EnterDirectoryListener] Path cannot be empty.");
            System.out.println("Error: Path cannot be empty.");
            return;
        }

        // 发送数据
        this.control.getControlSocket().getWriter().write("CWD " + path + "\r\n");
        this.control.getControlSocket().getWriter().flush();
       
        // 接收数据
        String response = this.control.getControlSocket().getReader().readLine();
        logger.debug("[EnterDirectoryListener] " + response);
        System.out.println(response);
       
    }

  
}


