/*
 * @Description: FTP客户端实现
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 01:33:30
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:34:31
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client;

import com.google.inject.Inject;
import top.cairbin.ftp.client.event.ChangeWorkingDirectoryEvent;
import top.cairbin.ftp.client.event.ConnectEvent;
import top.cairbin.ftp.client.event.CreateDirectoryEvent;
import top.cairbin.ftp.client.event.DeleteDirectoryEvent;
import top.cairbin.ftp.client.event.DeleteFileEvent;
import top.cairbin.ftp.client.event.DisconnectEvent;
import top.cairbin.ftp.client.event.DownloadFileEvent;
import top.cairbin.ftp.client.event.GetCurrentWorkingDirectoryEvent;
import top.cairbin.ftp.client.event.ListFilesEvent;
import top.cairbin.ftp.client.event.RenameFileEvent;
import top.cairbin.ftp.client.event.SetTransferModeEvent;
import top.cairbin.ftp.client.event.UploadFileEvent;
import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.IListenerRegistry;
import top.cairbin.ftp.logger.ILogger;

public class FtpClient implements IFtpClient{

    private ILogger logger;
    private IListenerRegistry registry;
    private ControlSocket client;

    @Inject
    public FtpClient(ILogger logger, IListenerRegistry registry, ControlSocket client) {
        this.logger = logger;
        this.registry = registry;
        this.
        // 自动扫描
        registry.registerListeners("top.cairbin.ftp.listener");
    }

    @Override
    public void connect(String serverAddress, int port) throws Exception{
        logger.info("Triggered event: connect");
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
        logger.info("Triggered event: disconnect");
        registry.getEventSource().triggerEvent(new DisconnectEvent(this, "disconnect"));
    }

    @Override
    public boolean isConnected() {
        return !client.getControlSocket().isClosed();
    }

    @Override
    public void uploadFile(String localFilePath, String remoteFilePath) throws Exception {
        logger.info("Triggered event: uploadFile");
        registry.getEventSource()
            .triggerEvent(new UploadFileEvent(
                this,
                "uploadFile",
                localFilePath,
                remoteFilePath
            ));
    }

    @Override
    public void downloadFile(String remoteFilePath, String localFilePath) throws Exception {
        logger.info("Triggered event: uploadFile");
        registry.getEventSource()
            .triggerEvent(new DownloadFileEvent(
                this,
                "downloadFile",
                localFilePath,
                remoteFilePath
            ));
    }

    @Override
    public void deleteFile(String remoteFilePath) throws Exception {
        logger.info("Triggered event: deleteFile");
        registry.getEventSource().triggerEvent(new DeleteFileEvent(
            this, 
            "deleteFile", 
            remoteFilePath
        ));
    }

    @Override
    public void renameFile(String oldFilePath, String newFilePath) throws Exception {
        logger.info("Triggered event: renameFile");
        registry.getEventSource().triggerEvent(new RenameFileEvent(
            this, 
            "renameFile", 
            oldFilePath,
            newFilePath
        ));
    }

    @Override
    public void listFiles(String directoryPath) throws Exception {
        logger.info("Triggered event: listFiles");
        registry.getEventSource().triggerEvent(new ListFilesEvent(
            this, 
            "listFiles", 
            directoryPath
        ));
    }

    @Override
    public void createDirectory(String directoryPath) throws Exception {
        logger.info("Triggered event: createDirectory");
        registry.getEventSource().triggerEvent(new CreateDirectoryEvent(
            this, 
            "listFiles", 
            directoryPath
        ));
    }

    @Override
    public void deleteDirectory(String directoryPath) throws Exception {
        logger.info("Triggered event: deleteDirectory");
        registry.getEventSource().triggerEvent(new DeleteDirectoryEvent(
            this, 
            "deleteDirectory", 
            directoryPath
        ));
    }

    @Override
    public void changeWorkingDirectory(String directoryPath) throws Exception {
        logger.info("Triggered event: changeWorkingDirectory");
        registry.getEventSource().triggerEvent(new ChangeWorkingDirectoryEvent(
            this, 
            "changeWorkingDirectory", 
            directoryPath
        ));
    }

    @Override
    public void getCurrentWorkingDirectory() throws Exception {
        logger.info("Triggered event: getCurrentWorkingDirectory");
        registry.getEventSource().triggerEvent(new GetCurrentWorkingDirectoryEvent(
            this,
            "getCurrentWorkingDirectory"
        ));
    }

    @Override
    public void setTransferMode(boolean binaryMode) throws Exception {
        logger.info("Triggered event: setTransferMode");
        registry.getEventSource().triggerEvent(new SetTransferModeEvent(
            this, 
            "setTransferMode", 
            binaryMode
        ));
    }
    
}
