/*
 * @Description: SocketClient 测试
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 22:50:40
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 05:15:03
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;

import top.cairbin.ftp.logger.ILogger;
import top.cairbin.ftp.socket.ISocketClient;
import top.cairbin.ftp.socket.SocketConfig;


public class SocketTest {
    private Injector container;

    private ILogger logger;

    private int serverPort = 8848;

    private Thread serThread;

    public SocketTest() throws InterruptedException{
        // 先初始化容器
        this.container = Guice.createInjector(new AppModule());
        this.logger = container.getInstance(ILogger.class);        
        // 启动服务器
        serThread = new ServerThread();
        serThread.start();
        // 等待服务器
        while(!((ServerThread)(serThread)).isReady) {
            Thread.sleep(3000);
            logger.info("Waiting for server thread");
        }
    }

    
    ISocketClient testCreateClient() throws Exception {
        logger.info("Trying to create client socket...");
        ISocketClient client = container.getInstance(ISocketClient.class);
        SocketConfig config = new SocketConfig();
        config.setHost("localhost");
        config.setPort(8848);
        config.setEncode("UTF-8");
        client.createSocket(config);
        return client;
    }

    // 两个方法不要同时用@Test否则端口会重复绑定而报错
    // @Test
    public void testWriter() throws Exception {
        var client = testCreateClient();
        logger.info("Trying to write message to server...");
        var writer = client.getWriter();
        writer.flush();
        writer.write("Hello,server!");
        writer.newLine();
        writer.flush();
    }

    @Test 
    public void testReader() throws Exception {
        var client = testCreateClient();
        logger.info("Trying to receive message from server...");
        var reader = client.getReader();
        logger.info("Get reader");
        String s = reader.readLine();
        logger.info("Client: Received message from client: " + s);
    }

    class ServerThread extends Thread {
        public boolean isEnd = false;
        public boolean isReady = false;
        private ServerSocket server;

        private void createServer() throws IOException{
            this.server = new ServerSocket(serverPort);
            logger.info("Success to create a server on port "+serverPort);
        }

        private void recvAndSendMessage() throws IOException{
            while(!isEnd){
                Socket socket = server.accept();
                logger.info("Connected from " + socket.getRemoteSocketAddress());
                var writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), 
                    "UTF-8"
                ));
                writer.flush();
                writer.write("Hello, client!");
                writer.newLine();
                writer.flush();
                logger.info("Server has sent message");

                var reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), 
                    "UTF-8"
                ));

                String msg = reader.readLine();
                logger.info("Server:Received message: " + msg);
            }
        }

        @Override
        public void run(){
            logger.info("Server thread started");
            try {
                createServer();
                this.isReady = true;
                recvAndSendMessage();
            } catch (IOException e) {
                logger.error(e);
                e.printStackTrace();
            }
                
        }
    }
}
