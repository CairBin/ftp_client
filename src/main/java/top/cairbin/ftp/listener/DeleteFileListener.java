/*
 * @Description: 删除文件事件监听器
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 22:08:18
 * @Copyright: Copyright (c) 2024 zhirun zhang
 *             Copyright (c) 2024 Xinyi Liu (CairBin)
 */
package top.cairbin.ftp.listener;



import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.event.DeleteFileEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;

@Listener
public class DeleteFileListener implements ICustomEventListener {

    @Inject
    private ILogger logger;

    @Inject
    private ControlSocket control;

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("deleteFile"))
            return;

        DeleteFileEvent e = (DeleteFileEvent)event;
        String name = e.getFname();

        logger.debug("[DeleteFileListener] name: " + name);
        control.getControlSocket().getWriter().write("DELE " + name + "\r\n");
        control.getControlSocket().getWriter().flush();

        String message = control.getControlSocket().getReader().readLine();
        logger.debug("[DeleteFileListener] Server message: " + message);
        System.out.println(message);



    }
    
}
