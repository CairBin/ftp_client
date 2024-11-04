/*
 * @Description: 改变目录事件
 * @License: MIT License
 * @Author: zhirun zhang
 * @version: 1.0.0
 * @Date: 2024-10-22 01:28:49
 * @LastEditors: zhirun zhang
 * @LastEditTime: 2024-10-22 01:29:42
 * @Copyright: Copyright (c) 2024 zhirun zhang
 */
package top.cairbin.ftp.client.event;
import top.cairbin.ftp.client.listener.CustomEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeDirectoryEvent extends CustomEvent{
    private String sour;
    private String dest;
    public ChangeDirectoryEvent(Object source, String name, String sour, String dest){
        super(source, name);
        this.sour = sour;
        this.dest = dest;
    }

    
    
}



