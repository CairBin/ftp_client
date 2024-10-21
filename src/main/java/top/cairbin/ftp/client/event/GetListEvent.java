/*
 * @Description: ls对应事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 01:15:03
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 01:16:04
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class GetListEvent extends CustomEvent{
    private String path;
    public GetListEvent(Object source, String name, String path) {
        super(source, name);
        this.path = path;
    }
    
}
