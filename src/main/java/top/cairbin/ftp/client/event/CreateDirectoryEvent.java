/*
 * @Description: 创建目录事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 03:18:56
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:23:01
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class CreateDirectoryEvent extends CustomEvent {
    private String directoryPath;
    public CreateDirectoryEvent(Object source, String name, String dirPath) {
        super(source, name);
        this.directoryPath = dirPath;
    }
    
}
