/*
 * @Description: 登陆事件
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 02:10:34
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 02:12:37
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class LoginEvent extends CustomEvent {
    private String username;
    private String token;
    private boolean isAnonymous;

    public LoginEvent(Object source, String name, String username, String token, boolean isAnonymous) {
        super(source, name);
        this.username = username;
        this.token = token;
        this.isAnonymous = isAnonymous;
    }
    
}
