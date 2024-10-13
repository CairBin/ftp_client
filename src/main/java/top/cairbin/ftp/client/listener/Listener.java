/*
 * @Description: 监听器注解
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:13:37
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 02:13:42
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 注解将在运行时可用
public @interface Listener {
}