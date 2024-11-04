/*
 * @Description: 创建目录事件
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 21:36:27
 * @Copyright: Copyright (c) 2024 zhirun zhang
 *             Copyright (c) 2024 Xinyi Liu (CairBin)
 */
package top.cairbin.ftp.client.event;

import top.cairbin.ftp.client.listener.CustomEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDirectoryEvent extends CustomEvent{
    private String path;
    public CreateDirectoryEvent(Object source, String name, String path){
        super(source, name);
        this.path = path;
    }

    
}
