/*
 * @Description: 事件源
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:17:55
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 03:01:26
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

import java.util.ArrayList;
import java.util.List;

public class EventSource implements IEventSource{
    private List<ICustomEventListener> listeners = new ArrayList<>();

    // 注册监听器
    public void registerListener(ICustomEventListener listener) {
        listeners.add(listener);
    }

    // 触发事件
    public void triggerEvent(CustomEvent event) {
        for (ICustomEventListener listener : listeners) {
            listener.onCustomEvent(event);
        }
    }
}
