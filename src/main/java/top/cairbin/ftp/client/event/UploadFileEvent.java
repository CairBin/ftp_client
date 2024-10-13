/*
 * @Description: 文件上传事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:47:47
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:04:57
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;


import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Setter
@Getter
public class UploadFileEvent extends CustomEvent{
    private String localFilePath;
    private String remoteFilePath;

    public UploadFileEvent(Object source, String name, String localFilePath, String remoteFilePath) {
        super(source,name);
        this.localFilePath = localFilePath;
        this.remoteFilePath = remoteFilePath;
    }
    
}
