/*
 * @Description: 下载文件
 * @License: MIT License
 * @Author: Yangbo Wang
 * @version: 1.0.0
 * @Date: 2024-11-1 20:00:00
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-05 00:06:56
 * @Copyright: Copyright (c) 2024 Yangbo Wang
 *             Copyright (c) 2024 Xinyi Liu (CairBin)
 */

package top.cairbin.ftp.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.Inject;

import top.cairbin.ftp.client.ControlSocket;
import top.cairbin.ftp.client.DataSocket;
import top.cairbin.ftp.client.event.UploadFilesEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.socket.ISocketClient;
import top.cairbin.ftp.socket.SocketConfig;

@Listener
public class UploadFilesListener implements ICustomEventListener{

@Inject
    private DataSocket data;

    @Inject
    private ControlSocket control;


    @Inject
    private ILogger logger;

    @Override
    public void onCustomEvent(CustomEvent event) throws Exception {
        if(!event.getEventName().equals("uploadFiles"))
            return;

        // 通知建立被动连接
        var writer = control.getControlSocket().getWriter();
        writer.write("PASV\r\n");
        writer.flush();
        // 建立被动连接
        establishing();

        UploadFilesEvent e = (UploadFilesEvent)event;
        String remotedirectory = e.getRemoteDirectory();
        writer.write("STOR " + remotedirectory + "\r\n");
        writer.flush();
        String response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
        if(!response.startsWith("150")) return;

        // 上传文件
        upload(e.getLocalDirectory());
        data.getDataSocket().close();

        response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
    }

    private void upload(String name) throws IOException{
        //读取本地文件内容，并通过数据连接的输出流发送到服务器。
        OutputStream dataOut = data.getDataSocket().getSocket().getOutputStream();
        FileInputStream fileIn = new FileInputStream(name);
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = fileIn.read(buffer))!= -1) {
            dataOut.write(buffer, 0, bytesRead);
        }
        dataOut.flush();
        fileIn.close();
    }

    private void establishing() throws Exception{
        String response = control.getControlSocket().getReader().readLine();
        System.out.println(response);
        String[] addr = matchPasvMessage(response);
        if (!response.startsWith("227") || addr == null) {
            logger.error("Error in getting passive mode IP address: " + response);
            return;
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
