/*
 * @Description: Socket客户端，实现ISocketClient接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 16:18:04
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 02:00:15
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */

package top.cairbin.ftp.socket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.inject.Inject;

import top.cairbin.ftp.logger.ILogger;

public class SocketClient implements ISocketClient{
    private Socket socket;
    private SocketConfig config;
    private final ILogger logger;
    private boolean isClosed;

    @Inject
    public SocketClient(ILogger logger){
        this.logger = logger;
        this.isClosed = false;
    }

    @Override
    public void createSocket(SocketConfig config) throws Exception {
        this.socket = new Socket(config.host, config.port);
        if(this.socket == null){
            logger.error("socket connected failed");
            throw new Exception("socket connection failed");
        }
        this.isClosed = false;
        logger.info("Connect to server "+config.host+":"+config.port);
        this.config = config;
        
    }

    @Override
    public BufferedReader getReader() throws Exception {
        return new BufferedReader(new InputStreamReader(
            socket.getInputStream(), 
            config.encode
        ));
    }

    @Override
    public BufferedWriter getWriter() throws Exception {
        return new BufferedWriter(new OutputStreamWriter(
            socket.getOutputStream(), 
            config.encode
        ));
    }

    @Override
    public void close() {
        try {
            socket.close();
            this.isClosed = true;
            logger.info("Close socket connection");
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

}
