/*
 * @Description: 自动注册Listener
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 02:16:08
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 02:39:30
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.reflections.Reflections;

import com.google.inject.Inject;

public class ListenerRegistry implements IListenerRegistry{
    private IEventSource eventSource;
    
    @Inject
    public ListenerRegistry(IEventSource eventSource) {
        this.eventSource = eventSource;
    }

    // 注册所有带有 @Listener 注解的类
    public void registerListeners(String packageName) {
        Reflections reflections = new Reflections(packageName);  // 使用 Reflections 库来扫描类

        // 获取所有带有 @Listener 注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Listener.class);

        for (Class<?> clazz : annotatedClasses) {
            try {
                // 创建实例并注册为监听器
                Object instance = clazz.getDeclaredConstructor().newInstance();
                if (instance instanceof ICustomEventListener) {
                    eventSource.registerListener((ICustomEventListener) instance);
                    System.out.println("Registered listener: " + clazz.getSimpleName());
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    
    @Override
    public IEventSource getEventSource() {
        return this.eventSource;
    }
}