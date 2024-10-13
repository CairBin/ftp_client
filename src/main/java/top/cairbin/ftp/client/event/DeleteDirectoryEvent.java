/*
 * @Description: 删除目录事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 03:22:17
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:23:40
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class DeleteDirectoryEvent extends CustomEvent {
    private String directoryPath;
    public DeleteDirectoryEvent(Object source, String name, String dirPath) {
        super(source, name);
        this.directoryPath = dirPath;
    }
    
}
