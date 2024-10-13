/*
 * @Description: file content
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 02:54:28
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:04:44
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class DownloadFileEvent extends CustomEvent{
    private String localFilePath;
    private String remoteFilePath;

    public DownloadFileEvent(Object source, String name, String localFilePath, String remoteFilePath) {
        super(source,name);
        this.localFilePath = localFilePath;
        this.remoteFilePath = remoteFilePath;
    }
}
