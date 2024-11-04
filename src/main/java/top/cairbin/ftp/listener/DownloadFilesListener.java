/*
 * @Description: 下载文件
 * @License: MIT License
 * @Author: Yangbo Wang
 * @version: 1.0.0
 * @Date: 2024-10-28 20:00:00
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-05 00:08:22
 * @Copyright: Copyright (c) 2024 Yangbo Wang
 *             Copyright (c) 2024 Xinyi Liu (CairBin)
 */

package top.cairbin.ftp.listener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.DataSocket;
import top.cairbin.ftp.client.event.DownloadFilesEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.socket.ISocketClient;
import top.cairbin.ftp.socket.SocketConfig;

@Listener
public class DownloadFilesListener implements ICustomEventListener{

    @Inject
    private DataSocket data;

    @Inject
    private ControlSocket control;

    @Inject
    private ILogger logger;
    

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("downloadFiles"))
            return;

        // 通知建立被动连接
        var writer = control.getControlSocket().getWriter();
        writer.write("PASV\r\n");
        writer.flush();
        // 建立被动连接
        establishing();

        DownloadFilesEvent e1 = (DownloadFilesEvent)event;
        String remotedirectory = e1.getRemoteDirectory();
        logger.debug("[DownloadFilesListener] Get remote file: " + remotedirectory);
        writer.write("RETR " + remotedirectory + "\r\n");
        writer.flush();
        String response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
        if(!response.startsWith("150")) return;

        // 下载
        download(e1.getLocalDirectory());
        data.getDataSocket().close();

        response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
    }

    private void download(String name) throws IOException{
        logger.debug("[DownloadFilesListener] Save to " + name);
        InputStream dataIn = data.getDataSocket().getSocket().getInputStream();
        FileOutputStream fileOut = new FileOutputStream(name);

        byte[] buffer = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = dataIn.read(buffer))!= -1) {
            fileOut.write(buffer, 0, bytesRead);
        }
        fileOut.close();
        dataIn.close();
    }

    private void establishing() throws Exception{
        String response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
        String[] addr = matchPasvMessage(response);
        if (!response.startsWith("227") || addr == null) {
            logger.error("Error in getting passive mode IP address: " + response);
            throw new Exception("Error in getting passive mode IP address: " + response);
        }
        SocketConfig config = new SocketConfig();
        config.setHost(addr[0]);
        config.setPort(Integer.parseInt(addr[1]));
        config.setEncode("UTF-8");
        data.getDataSocket().createSocket(config);
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

