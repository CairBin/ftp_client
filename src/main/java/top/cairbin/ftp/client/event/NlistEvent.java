/*
 * @Description: NList事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-11-04 23:00:10
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 23:00:58
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class NlistEvent extends CustomEvent {
    private String path;

    public NlistEvent(Object source, String name, String path) {
        super(source, name);
        this.path = path;
    }
    
}
