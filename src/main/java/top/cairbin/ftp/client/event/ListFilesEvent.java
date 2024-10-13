/*
 * @Description: file content
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 03:16:28
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:18:20
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class ListFilesEvent extends CustomEvent{
    public ListFilesEvent(Object source, String name, String dirPath) {
        super(source, name);
        this.directoryPath = dirPath;  
    }

    private String directoryPath;
    
    
}
