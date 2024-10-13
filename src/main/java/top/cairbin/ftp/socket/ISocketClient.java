/*
 * @Description: Socket客户端接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 15:46:14
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 01:58:34
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 *             
 */

package top.cairbin.ftp.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

public interface ISocketClient {
    /**
     * @throws Exception 
     * @description: 用于激活创建Socket对象
     */    
    void createSocket(SocketConfig config) throws Exception;

    /**
     * @description: 接收消息
     * @return {BufferedReader} 接受消息的缓冲区
     * @throws IOException 
     * @throws UnsupportedEncodingException 
     */    
    BufferedReader getReader() throws Exception;
    
    /**
     * @description: 以配置给定的编码方式发送消息
     * @return {BufferWriter} 写消息的缓冲区
     * @throws IOException 
     * @throws UnsupportedEncodingException 
     */    
    BufferedWriter getWriter() throws Exception;

    /**
     * @description: 关闭连接
     */    
    void close();

    /**
     * @description: 判断是否连接
     * @return {boolean}
     */    
    boolean isClosed();

}
