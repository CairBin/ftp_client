/*
 * @Description: 监听登陆事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 02:12:50
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 02:46:27
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.listener;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.IFtpClient;
import top.cairbin.ftp.client.UserState;
import top.cairbin.ftp.client.event.LoginEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;

@Listener
public class LoginListener implements ICustomEventListener {

    @Inject
    private ILogger logger;

    @Inject
    private ControlSocket control;

    @Inject
    IFtpClient client;


    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("login"))
            return;
        
        client.setState(UserState.LOGGING);
        LoginEvent e = (LoginEvent)event;
        String username = e.getUsername();
        
        logger.debug("[LoginListener] Login as " + username);
        control.getControlSocket().getWriter().write("USER " + username + "\r\n");
        control.getControlSocket().getWriter().flush();
        
        String message = control.getControlSocket().getReader().readLine();
        logger.debug("[LoginListener] Server response: " + message);
        String[] msg = message.split(" ");
        if(msg[0].equals("530")){
            System.out.println(message);
            client.setState(UserState.OFFLINE);
            return;
        }
        
        String token = e.getToken();
        control.getControlSocket().getWriter().write("PASS " + token + "\r\n");
        control.getControlSocket().getWriter().flush();

        message = control.getControlSocket().getReader().readLine();
        logger.debug("[LoginListener] Server response: " + message);
        msg = message.split(" ");

        if(msg[0].equals("530")){
            System.out.println(message);
            client.setState(UserState.OFFLINE);
            return;
        }

        System.out.println(message);
        client.setState(UserState.ONLINE);
    }
    
}
