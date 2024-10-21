/*
 * @Description: 事件源
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:17:55
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 01:49:29
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

import java.util.ArrayList;
import java.util.List;

public class EventSource implements IEventSource{
    private List<ICustomEventListener> listeners = new ArrayList<>();

    // 注册监听器
    @Override
    public void registerListener(ICustomEventListener listener) {
        listeners.add(listener);
    }

    // 触发事件
    @Override
    public void triggerEvent(CustomEvent event) throws Exception {
        for (ICustomEventListener listener : listeners) {
            listener.onCustomEvent(event);
        }
    }
}
