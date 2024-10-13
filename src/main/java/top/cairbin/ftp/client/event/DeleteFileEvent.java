/*
 * @Description: 删除文件事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 03:07:24
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:08:14
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class DeleteFileEvent extends CustomEvent{

    private String remoteFilePath;

    public DeleteFileEvent(Object source, String name, String remoteFilePath) {
        super(source, name);
        this.remoteFilePath = remoteFilePath;
    }
    
}
