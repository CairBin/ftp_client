/*
 * @Description: 显示当前目录监听器
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 22:52:16
 * @Copyright: Copyright (c) 2024 zhirun zhang
 *             Copyright (c) 2024 Xinyi Liu (CairBin)
 */
package top.cairbin.ftp.listener;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.DataSocket;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.socket.SocketConfig;
import top.cairbin.ftp.thread.IThreadPool;

@Listener
public class GetCurrentDirectoryListener implements ICustomEventListener {

    @Inject
    private ILogger logger;

    @Inject
    private ControlSocket control;

    @Inject
    IThreadPool threadPoll;

    @Inject
    private DataSocket dataSocket;


    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if (!event.getEventName().equals("getCurrentDirectory"))
            return;
        
        logger.debug("[GetCurrentDirectoryListener] Start to get current directory.");
        
        // 发送数据
        control.getControlSocket().getWriter().write("PWD\r\n");
        control.getControlSocket().getWriter().flush();

        // 接受数据
        String message = control.getControlSocket().getReader().readLine();
        System.out.println(message);

    }

    

}
