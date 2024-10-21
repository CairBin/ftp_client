/*
 * @Description: 自动注册Listener
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-14 02:16:08
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 00:08:24
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.client.listener;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

import com.google.inject.Inject;

import top.cairbin.ftp.InjectorFactory;
import top.cairbin.ftp.logger.ILogger;

public class ListenerRegistry implements IListenerRegistry{
    @Inject
    private ILogger logger;

    
    private final IEventSource eventSource;
    
    @Inject
    public ListenerRegistry(IEventSource eventSource) {
        this.eventSource = eventSource;
    }

    /**
     * @description: 注册所有带有 @Listener 注解的类
     * @param {String} packageName 待扫描的包名
     */    
    @Override
    public void registerListeners(String packageName) {
        Reflections reflections = new Reflections(packageName);  // 使用 Reflections 库来扫描类

        // 获取所有带有 @Listener 注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Listener.class);

        for (Class<?> clazz : annotatedClasses) {
            try {
                // 创建实例并注册为监听器
                Object instance = clazz.getDeclaredConstructor().newInstance();
                if(instance instanceof ICustomEventListener) {
                    InjectorFactory.getInjector().injectMembers(instance);  // 依赖注入
                    eventSource.registerListener((ICustomEventListener) instance);
                    logger.debug("Registered listener: " + clazz.getSimpleName());
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                logger.error("[ListenerRegistry] Error registering listener " + e);
            }
        }
    }

    
    @Override
    public IEventSource getEventSource() {
        return this.eventSource;
    }
}