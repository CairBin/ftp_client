/*
 * @Description: Socket客户端，实现ISocketClient接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 16:18:04
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 03:03:57
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */

package top.cairbin.ftp.socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.google.inject.Inject;

import top.cairbin.ftp.logger.ILogger;

public class SocketClient implements ISocketClient{
    private Socket socket;
    private SocketConfig config;
    private final ILogger logger;
    private boolean isClosed;

    private BufferedReader reader;
    private BufferedWriter writer;

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
        if(this.reader != null){
            return this.reader;
        }

        this.reader = new BufferedReader(new InputStreamReader(
            socket.getInputStream(), 
            config.encode
        ));
        return this.reader;
    }

    @Override
    public BufferedWriter getWriter() throws Exception {
        if(this.writer != null){
            return this.writer;
        }
        this.writer = new BufferedWriter(new OutputStreamWriter(
            socket.getOutputStream(), 
            config.encode
        ));
        return this.writer;
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

    @Override
    public Socket getSocket() {
        return this.socket;
    }
}
