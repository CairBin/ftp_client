/*
 * @Description: 删除目录事件
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: zhirun zhang
 * @LastEditTime: 2024-10-22 01:29:42
 * @Copyright: Copyright (c) 2024 zhirun zhang
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class DeleteDirectoryEvent extends CustomEvent {
    
    private String path;
    public DeleteDirectoryEvent (Object source, String name, String path){
        super(source, name);
        this.path = path;
    }
    
}
