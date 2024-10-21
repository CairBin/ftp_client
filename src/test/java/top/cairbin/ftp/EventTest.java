/*
 * @Description: 事件测试
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-14 03:35:42
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 01:50:49
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;
import top.cairbin.ftp.client.listener.IListenerRegistry;
import top.cairbin.ftp.client.listener.Listener;
import top.cairbin.ftp.logger.ILogger;


class MyEvent extends CustomEvent{

    public MyEvent(Object source) {
        super(source, "myEvent");
    }
    
}

@Listener
class MyListener implements ICustomEventListener{

    @Override
    public void onCustomEvent(CustomEvent event) {
        if(event.getEventName().equals("myEvent"))
            System.out.println("Hello, this is listener!");
        
    }
    
}

public class EventTest {
    private ILogger logger;
    private Injector container;
    private IListenerRegistry registry;
    

    public EventTest() {
        this.container = Guice.createInjector(new AppModule());
        this.logger = container.getInstance(ILogger.class);
        this.registry = container.getInstance(IListenerRegistry.class);
    }

    @Test
    public void testEvent() throws Exception{
        this.registry.getEventSource()
            .registerListener(new MyListener());

        this.registry.getEventSource()
            .triggerEvent(new MyEvent(this));
    }


}
