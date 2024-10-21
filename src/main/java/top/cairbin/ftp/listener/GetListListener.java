/*
 * @Description: ls命令
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 01:36:31
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 04:17:45
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.listener;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.DataSocket;
import top.cairbin.ftp.client.IFtpClient;
import top.cairbin.ftp.client.event.GetListEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.socket.SocketConfig;
import top.cairbin.ftp.thread.IThreadPool;

@Listener
public class GetListListener implements ICustomEventListener{
    @Inject
    private ILogger logger;
    @Inject
    private ControlSocket control;

    @Inject
    IThreadPool threadPool;

    @Inject
    private DataSocket dataSocket;

    @Inject
    private IFtpClient client;

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("getList"))
            return;
        GetListEvent e = (GetListEvent)event;
        String path = e.getPath();
        if(path == null)
            path = "";
        logger.debug("[GetListListener] path: " + path);
        control.getControlSocket().getWriter().write("PASV\r\n");
        control.getControlSocket().getWriter().flush();

        
        String response = control.getControlSocket().getReader().readLine();
        logger.debug("[GetListListener] " + response);
        String[] addr = matchPasvMessage(response);
        if (!response.startsWith("227") || addr == null) {
            logger.error("Error in getting passive mode IP address: " + response);
            return;
        }
        
        establishing(addr);
        
        logger.debug("[GetListListener]Trying to ls " + path);
        if(path.isEmpty())
            control.getControlSocket().getWriter().write("LIST\r\n");
        else
            control.getControlSocket().getWriter().write("LIST " + path + "\r\n");
        control.getControlSocket().getWriter().flush();
        response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
        StringBuffer sb = recvList();
        System.out.println(sb.toString());
        response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
        dataSocket.getDataSocket().close();
    }

    private StringBuffer recvList() throws IOException, Exception{
        StringBuffer s = new StringBuffer();
        while (true) {
            char[] buf = new char[1024];
            int len = dataSocket.getDataSocket().getReader().read(buf);
            if(len == -1) break;
            s.append(buf, 0, len);
        }
        return s;
    }

    private void establishing(String[] addr) throws Exception{
        SocketConfig config = new SocketConfig();
        config.setHost(addr[0]);
        config.setPort(Integer.parseInt(addr[1]));
        config.setEncode("UTF-8");
        dataSocket.getDataSocket().createSocket(config);
    }

    private String[] matchPasvMessage(String passiveModeMessage){
        String regex = "Entering Passive Mode \\((\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passiveModeMessage);

        // 如果匹配到数据
        if (matcher.find()) {
            // 提取IP地址
            String ip1 = matcher.group(1);
            String ip2 = matcher.group(2);
            String ip3 = matcher.group(3);
            String ip4 = matcher.group(4);
            String ipAddress = String.format("%s.%s.%s.%s", ip1, ip2, ip3, ip4);

            // 提取端口号（两部分组合起来）
            int port1 = Integer.parseInt(matcher.group(5));
            int port2 = Integer.parseInt(matcher.group(6));
            int port = port1 * 256 + port2;

            // 输出结果
            String[] addr = {ipAddress, ""+port};
            return addr;
        } else {
            return null;
        }
    }

    


}
