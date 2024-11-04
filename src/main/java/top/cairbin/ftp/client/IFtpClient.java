/*
 * @Description: FTP客户端接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 01:24:22
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 23:57:52
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client;

public interface IFtpClient {

    /**
     * 连接到 FTP 服务器
     * @param serverAddress FTP 服务器地址
     * @param port FTP 服务器端口
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
     * @description: 退出FTP
     */    
    void quit() throws Exception;

    /**
     * @description: 获取当前目录下文件及子目录列表
     * @param {String} 路径
     */    
    void getList(String path) throws Exception;

    /**
     * @description: 传输模式
     * @param {TransferMode} mode
     * @return {*}
     */    
    void setTransferMode(TransferMode mode);

    /**
     * @description: 获取传输模式
     * @return {*}
     */    
    TransferMode getTransferMode();

    /**
     * @description: 获取用户状态
     * @return {*}
     */    
    UserState getState();
    
    /**
     * @description: 设置用户状态
     * @return {*}
     */    
    void setState(UserState state);

    /**
     * @description: 登陆
     * @param {String} username
     * @param {String} password
     * @throws Exception 
     */    
    void login(String username, String password) throws Exception;

    /**
     * @description: 切换目录
     * @param {String} path 目录
     * @throws Exception
     */
    void cd(String path) throws Exception;

    /**
     * @description: 删除文件
     * @param {String} filename 文件名
     * @return {*}
     */    
    void deleteFile(String filename) throws Exception;

    /**
     * @description: 重命名或移动目录
     * @param {String} oldPath 旧目录
     * @param {String} newPath 新目录
     * @return {*}
     */    
    void rename(String oldPath, String newPath) throws Exception;

    /**
     * @description: 移除目录
     * @param {String} path 目录
     * @return {*}
     */    
    void remove(String path) throws Exception;

    /**
     * @description: 创建目录
     * @param {String} path 目录
     * @return {*}
     */    
    void mkdir(String path) throws Exception;

    /**
     * @description: 获取当前路径
     * @return {*}
     */    
    void pwd() throws Exception;

    /**
     * @description: 获取精简目录列表
     * @param {String} path 目录
     * @return {*}
     */
    void nls(String path) throws Exception;

    /**
     * @description: 下载文件
     * @param {String} remote 远程目录
     * @param {String} local 本地目录
     * @return {*}
     */    
    void download(String remote, String local) throws Exception;

    /**
     * @description: 上传文件
     * @param {String} remote 远程
     * @param {String} local 本地
     * @return {*}
     */    
    void upload(String remote, String local) throws Exception;
}