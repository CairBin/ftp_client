/*
 * @Description: FTP客户端实现
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 01:33:30
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-05 00:01:28
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client;

import com.google.inject.Inject;

import top.cairbin.ftp.client.event.ChangeDirectoryEvent;
import top.cairbin.ftp.client.event.ConnectEvent;
import top.cairbin.ftp.client.event.CreateDirectoryEvent;
import top.cairbin.ftp.client.event.DeleteDirectoryEvent;
import top.cairbin.ftp.client.event.DeleteFileEvent;
import top.cairbin.ftp.client.event.DownloadFilesEvent;
import top.cairbin.ftp.client.event.EnterDirectoryEvent;
import top.cairbin.ftp.client.event.GetCurrentDirectoryEvent;
import top.cairbin.ftp.client.event.GetListEvent;
import top.cairbin.ftp.client.event.LoginEvent;
import top.cairbin.ftp.client.event.NlistEvent;
import top.cairbin.ftp.client.event.QuitEvent;
import top.cairbin.ftp.client.event.UploadFilesEvent;
import top.cairbin.ftp.client.listener.IListenerRegistry;
import top.cairbin.ftp.logger.ILogger;

public class FtpClient implements IFtpClient{

    
    private final ILogger logger;

    private final IListenerRegistry registry;

    private final ControlSocket client;

    private TransferMode mode = TransferMode.PORT;

    private UserState state = UserState.OFFLINE;

    @Inject
    public FtpClient(ILogger logger, IListenerRegistry registry, ControlSocket client) {
        this.logger = logger;
        this.registry = registry;
        this.client = client;
        registry.registerListeners("top.cairbin.ftp.listener");
    }

    @Override
    public void connect(String serverAddress, int port) throws Exception{
        logger.debug("[FtpClient] Triggered event: connect");
        // 触发消息
        registry.getEventSource()
            .triggerEvent(new ConnectEvent(
                this,
                "connect",
                serverAddress,
                port
            ));
    }

    @Override
    public void disconnect() throws Exception {
        logger.debug("[FtpClient] Triggered event: disconnect");
        // registry.getEventSource().triggerEvent(new DisconnectEvent(this, "disconnect"));
    }

    @Override
    public boolean isConnected() {
        return !client.getControlSocket().isClosed();
    }

    
    @Override
    public void quit() throws Exception{
        logger.debug("[FtpClient] Triggered event: quit");
        registry.getEventSource().triggerEvent(new QuitEvent(this, "quit"));
    }

    @Override
    public void getList(String path) throws Exception{
        logger.debug("[FtpClient] Triggered event: getList");
        registry.getEventSource().triggerEvent(new GetListEvent(this, "getList", path));
    }

    @Override
    public void setTransferMode(TransferMode mode) {
        this.mode = mode;
    }

    @Override
    public TransferMode getTransferMode() {
        return mode;
    }

    @Override
    public UserState getState() {
        return this.state;
    }

    @Override
    public void setState(UserState state) {
        this.state = state;
    }

    @Override
    public void login(String username, String password) throws Exception {
        logger.debug("[FtpClient] Triggered event: login");
        registry.getEventSource().triggerEvent(new LoginEvent(this, "login", username, password, username.toLowerCase().equals("anonymous")));
    }

    @Override
    public void cd(String path) throws Exception {
        logger.debug("[FtpClient] Triggered event: cd");
        registry.getEventSource().triggerEvent(new EnterDirectoryEvent(this, "enterDirectory", path));
    }

    @Override
    public void deleteFile(String filename) throws Exception {
        logger.debug("[FtpClient] Triggered event: deleteFile");
        registry.getEventSource().triggerEvent(new DeleteFileEvent(this, "deleteFile", filename));
    }

    @Override
    public void rename(String oldPath, String newPath) throws Exception{
        logger.debug("[FtpClient] Triggered event: changeDirectoryEvent");
        registry.getEventSource().triggerEvent(new ChangeDirectoryEvent(this, "changeDirectory", oldPath, newPath));
    }

    @Override
    public void remove(String path) throws Exception {
        logger.debug("[FtpClient] Triggered event: remove");
        registry.getEventSource().triggerEvent(new DeleteDirectoryEvent(this, "deleteDirectory", path));
    }

    @Override
    public void mkdir(String path) throws Exception {
        logger.debug("[FtpClient] Triggered event: mkdir");
        registry.getEventSource().triggerEvent(new CreateDirectoryEvent(this, "createDirectory", path));
    }

    @Override
    public void pwd() throws Exception {
        logger.debug("[FtpClient] Triggered event: pwd");
        registry.getEventSource().triggerEvent(new GetCurrentDirectoryEvent(this, "getCurrentDirectory"));
    }

    @Override
    public void nls(String path) throws Exception {
        logger.debug("[FtpClient] Triggered event: nls");
        registry.getEventSource().triggerEvent(new NlistEvent(this, "nlist", path));
    }

    @Override
    public void download(String remote, String local) throws Exception {
        logger.debug("[FtpClient] Triggered event: downloadFiles");
        registry.getEventSource().triggerEvent(new DownloadFilesEvent(this, "downloadFiles", local, remote));
    }

    @Override
    public void upload(String remote, String local) throws Exception {
        logger.debug("[FtpClient] Triggered event: uploadFiles");
        registry.getEventSource().triggerEvent(new UploadFilesEvent(this, "uploadFiles", local, remote));
    }
}
