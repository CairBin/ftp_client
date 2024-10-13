/*
 * @Description: 切换传输模式事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 03:32:00
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:32:50
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class SetTransferModeEvent extends CustomEvent {
    private boolean transferMode;

    public SetTransferModeEvent(Object source, String name, boolean transferMode) {
        super(source, name);
        this.transferMode = transferMode;
    }
    
}
