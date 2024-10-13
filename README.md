# ftp_client

## 描述

使用Java写的FTP客户端，尚未完成。

## 开发

### 代码规范

类名、接口等请遵循大驼峰命名，方法名、局部变量、属性请遵循小驼峰方式命名。

另外在文件开头请添加作者信息以及版权描述：
```java
/*
 * @Description: 描述
 * @License: MIT License
 * @Author: 作者名字
 * @version: 1.0.0
 * @Date: 2024-10-14 01:48:12
 * @LastEditors: 最后一次编辑的人员
 * @LastEditTime: 2024-10-14 01:52:45
 * @Copyright: Copyright (c) 2024 名字1
 *              Copyright (c) 2024 名字2
 *              只要改动过代码的人员就在这里加一行
 */
```

对于成员方法应按照以下格式
```java
/**
     * @description: 功能描述
     * @return {返回类型} 返回值描述
     * @throws 抛出异常的类型 抛出异常描述
*/    
```



### 事件监听

在`top.cairbin.ftp.listener`中创建监听器，实现`ICustomEventListener`，并用`@Listener`注解修饰类，则可以自动注册监听器。

```java
package top.cairbin.ftp.listener;

import top.cairbin.ftp.client.listener.CustomEvent;
import top.cairbin.ftp.client.listener.ICustomEventListener;

public class ExampleListener implements ICustomEventListener {

    @Override
    public void onCustomEvent(CustomEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCustomEvent'");
    }
    
}

```

重载的方法接受一个CustomEvent，这是本项目所有事件的**事件基础类**，它继承自`java.util.EventObject`。

使用`getEventName()`方法可以获取事件名：
```java
// onCustomEvent方法内部
String name = event.getEventName();
```

传入的事件会对所有监听器进行广播，可以通过事件名来判断是否是你所需的：

```java
// onCustomEvent方法内部
String name = event.getEventName();
if(name.equals("myEvent")){
    [...]
}
```

事件可以携带参数，实际上`CustomEvent`本身携带两个参数，一个是刚才所说的**事件名**，另一个为**事件源**。

事件源是发出此事件的对象，你可以用以下方法去获取它。注意，它将返回一个`Object`对象，你必须强制将它转换为原来的类型。

```java
SourceClass source = (SourceClass)event.getSource();
```

当然，在本项目中有一些特殊事件，他们它们通常会携带特殊的参数，我们以`ConnectEvent`为例，它的事件名为`connect`，其他事件也类似，即类名去掉`Event`然后首字母小写。它携带两个参数，一个是FTP服务器的主机地址，另一个是FTP控制连接的端口号，你要获取所携带的参数需进行类型转换：

```java

[...]
public class ConnectListener implements ICustomEventListener {

    @Override
    public void onCustomEvent(CustomEvent event) {
        if(!event.getEventName().equals("connect"))
            return;

        // 强制转换为ConnectEvent
        ConnectEvent e = (ConnectEvent)event;
        
        // 通过Getter来获取参数
        String host = e.getHost();    // 服务器地址
        int port = e.getPort();    // 服务器端口号
    }
    
}
```

这里需要注意，在实现Listener的时候，Listener的类名按照规范应该为**事件类名**（注意不是事件名）将`Event`改为`Listener`。例如`ConnectEvent`对应的事件名为`connect`，它的监听器类名应该为`ConnectListener`。

### 如何获取控制连接和数据连接对象

本项目在`top.cairbin.ftp.client`实现了`ControlSocket`和`DataSocket`，这两个实际上分别对应控制连接、数据连接的`ISocketClient`单例工厂，并在IOC容器中进行注册。也就是说你可以在全局访问它们。

例如我们在connect事件的监听器中一定会用到`ControlSocket`建立控制连接。通过`ControlSocket`的`getControlSocket`来获取对应的`ISocketClient`进行创建连接。

在其他监听器中，你可能需要它们来进行收法消息，其他监听器与`ConnectListener`共享一个对象，也就是说其他大部分事件应当发生在`connect`事件之后，否则Socket连接没有初始化。

```java

[...]
public class ConnectListener implements ICustomEventListener {
    private final ControlSocket control;

    @Inject
    public ConnectListener(ControlSocket control){
        this.control = control;
    }

    @Override
    public void onCustomEvent(CustomEvent event) {
        if(!event.getEventName().equals("connect"))
            return;

        // 强制转换为ConnectEvent
        ConnectEvent e = (ConnectEvent)event;
        
        // 通过Getter来获取参数
        String host = e.getHost();    // 服务器地址
        int port = e.getPort();    // 服务器端口号

        // Socket配置
        // 需要端口号、主机、和编码方式
        SocketConfig config;
        config.setHost("127.0.0.1");
        config.setPort(21);
        config.setEncode("UTF-8");

        // 获取用于控制连接的对象
        try(){
            control.getControlSocket().createSocket(config);
        }
        [...]

    }
    
}
```

### 使用日志

在必要的时候记得打印日志，日志记录器是一个单例，你必须从容器以依赖注入的方式获取它
```java
class MyClass{
    private ILogger logger;
    @Inject
    public MyClass(ILogger logger){
        this.logger = logger;
        this.logger.info("This is a log message");
    }
}

```

`ILogger`规定了4种级别日志，分别为debug、info、warn、error，本项目默认使用Log4J进行实现，在`src/main/resources/log4j2.xml`里可以进行配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <!-- 控制台输出 -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"/>
        </Console>
    </Appenders>

    <Loggers>
        <!-- 默认全局日志记录器 -->
        <Root level="debug">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```
### 关于ISocketClient

注意：以依赖注入的方式获取实现`ISocketClient`接口的对象并不是单例。

在某些情况下，你可能不想用`ControlSocket`和`DataSocket`建立的连接，这时候可以通过容器获取`ISocketClient`的实现对象。

但你应当清楚，这个对象与其他类并不共享，也就是说你从其他类再通过依赖注入的方式获取的是一个全新的对象。

下面对`ISocketClient`进行演示：
```java
// client是获取的ISocketClient对象

// 此处config为SocketConfig类型，使用方式请参考上文
if(client.isClosed())   // 判断连接是否关闭
    client.createSocket(config);
// 如过你想要给服务器发送消息，则需要获取writer
// 注意flush方法位置，建议发送前后都调用一次
var writer = client.getWriter();
writer.flush();
writer.write("Hello");
writer.newLine();   // 换行
writer.flush();

// 读取数据
var reader = client.getReader();
logger.info("Get reader");
String s = reader.readLine();
logger.info("Client: Received message from client: " + s);


client.close(); // 关闭连接
```


### 自定义事件

如果你需要自定义事件，则需继承`CustomEvent`类，在构造器中调用父类构造器，并添加子类代码。例如我们可以用以下例子来创建一个携带自定义参数的事件：

```java
import top.cairbin.ftp.client.listener.CustomEvent;

public class MyEvent extends CustomEvent{
    private String myParam; // 自定义携带参数

    public MyEvent(Object source, String name, String myParam) {
        super(source, name);
        this.myParam = myParam;
    }
    
}
```

事件必须通过`IEventSource`进行分发，如过不修改FTP客户端代码，或者自定义事件系统，你大概率用不到这个功能。

关于实现了`IEventSource`接口的对象，你可以在类中以依赖注入的方式获取，本项目使用Guice框架，推荐构造器注入，当然你也可以字段注入（属性注入）。

```java
class MyClass{
    private IEventSource source;
    @Inject
    public MyClass(IEventSource source){
        this.source = source;
    }

    public test(){
        // 注册监听器
        source.registerListener(new MyListener());

        // 触发事件
        source.triggerEvent(new MyEvent(
            this,       // 产生此事件的对象，以this为例
            "myEvent",  // 事件名
            "myParam"   // 刚才自定义的参数
        ));
    }
}
```

可以参考测试目录下的`EventTest.java`。

### 自动扫描监听器

默认自动扫描是通过`IListenerRegistry`的实现对象`ListenerRegistry`实现的。`ListenerRegistry`通过IOC容器获取`IEventSource`实例，然后利用`registerListeners(String package)`方法进行注册的，这个方法的参数为需要扫描的包名，它会利用Java反射来扫描某个包下的所有类，一旦发现被`@Listener`修饰，调用`IEvent`的`registerListeners(ICustomListener)`方法进行注册。

在`FtpClient`类中已经包含`IListenerRegistry`类型的一个成员，它会默认从`top.cairbin.ftp.listener`中进行扫描。

当然你若需要实现自己的事件系统，则可以通过依赖注入的方式得到一个对象，注意这个对象并不是单例。

如过你需要单例，请手动封装一个用于获取单例的工厂，用`@Singleton`注解修饰类，并在`AppModule`中进行依赖配置。


### 依赖注入

依赖注入使用Guice框架，其配置项在`AppModule.java`文件中

```java
public class AppModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(ISocketClient.class)
            .to(SocketClient.class);
        
        bind(ControlSocket.class);
        
        bind(IFtpClient.class)
            .to(FtpClient.class);
        
        bind(IEventSource.class)
            .to(EventSource.class);
        
        bind(IListenerRegistry.class)
            .to(ListenerRegistry.class);
    }

    @Provides
    @Singleton
    public ILogger getLogger() {
        return new Log4jLogger();
    }

}
```

详细用法请参考[Guice文档](https://github.com/google/guice/wiki/Motivation)



## 其他

主要用到的第三方库（可能不全）

* Guice
* Log4j
* Lombok