/*
 * @Description: FTP客户端接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 01:24:22
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:10:26
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client;

import java.util.List;

public interface IFtpClient {

    /**
     * 连接到 FTP 服务器
     * @param serverAddress FTP 服务器地址
     * @param port FTP 服务器端口
     * @param username 用户名
     * @param password 密码
     * @return 是否连接成功
     * @throws Exception 抛出异常
     */
    void connect(String serverAddress, int port) throws Exception;

    /**
     * 断开与 FTP 服务器的连接
     * @throws Exception 自定义异常处理断开失败的情况
     */
    void disconnect() throws Exception;

    /**
     * 检查是否连接到 FTP 服务器
     * @return 是否连接到服务器
     */
    boolean isConnected();

    /**
     * 上传文件到 FTP 服务器
     * @param localFilePath 本地文件路径
     * @param remoteFilePath 远程文件路径
     * @throws Exception 自定义异常处理上传失败的情况
     */
    void uploadFile(String localFilePath, String remoteFilePath) throws Exception;

    /**
     * 从 FTP 服务器下载文件
     * @param remoteFilePath 远程文件路径
     * @param localFilePath 本地文件路径
     * @throws Exception 自定义异常处理下载失败的情况
     */
    void downloadFile(String remoteFilePath, String localFilePath) throws Exception;

    /**
     * 删除 FTP 服务器上的文件
     * @param remoteFilePath 远程文件路径
     * @throws Exception 自定义异常处理删除失败的情况
     */
    void deleteFile(String remoteFilePath) throws Exception;

    /**
     * 重命名 FTP 服务器上的文件
     * @param oldFilePath 旧文件路径
     * @param newFilePath 新文件路径
     * @throws Exception 自定义异常处理重命名失败的情况
     */
    void renameFile(String oldFilePath, String newFilePath) throws Exception;

    /**
     * 列出 FTP 服务器上的目录内容
     * @param directoryPath 目录路径
     * @throws Exception 自定义异常处理列出文件失败的情况
     */
    void listFiles(String directoryPath) throws Exception;

    /**
     * 创建目录
     * @param directoryPath 要创建的目录路径
     * @throws Exception 自定义异常处理创建目录失败的情况
     */
    void createDirectory(String directoryPath) throws Exception;

    /**
     * 删除目录
     * @param directoryPath 要删除的目录路径
     * @throws Exception 自定义异常处理删除目录失败的情况
     */
    void deleteDirectory(String directoryPath) throws Exception;

    /**
     * 切换当前工作目录
     * @param directoryPath 要切换到的目录路径
     * @throws Exception 自定义异常处理切换目录失败的情况
     */
    void changeWorkingDirectory(String directoryPath) throws Exception;

    /**
     * 获取当前工作目录
     * @throws Exception 自定义异常处理获取工作目录失败的情况
     */
    void getCurrentWorkingDirectory() throws Exception;

    /**
     * 设置传输模式（如 ASCII 或 Binary 模式）
     * @param binaryMode 是否为二进制模式
     * @throws Exception 自定义异常处理设置传输模式失败的情况
     */
    void setTransferMode(boolean binaryMode) throws Exception;
}