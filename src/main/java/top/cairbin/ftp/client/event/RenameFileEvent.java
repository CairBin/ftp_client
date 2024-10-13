/*
 * @Description: 重命名文件事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 03:12:08
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:15:08
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class RenameFileEvent extends CustomEvent {
    private String oldFilePath;
    private String newFilePath;
    public RenameFileEvent(Object source, String name, String oldFilePath, String newFilePath) {
        super(source, name);
        this.oldFilePath = oldFilePath;
        this.newFilePath = newFilePath;
    }
    
}
