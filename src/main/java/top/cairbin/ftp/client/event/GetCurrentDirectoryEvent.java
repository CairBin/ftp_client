/*
 * @Description: 显示当前目录事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 02:10:34
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 21:44:47
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class GetCurrentDirectoryEvent extends CustomEvent{
    
    
    public GetCurrentDirectoryEvent(Object source, String name) {
        super(source, name);
       
    }
    
}
