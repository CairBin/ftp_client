/*
 * @Description: 程序入口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 15:36:44
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-14 04:47:37
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import com.google.inject.Guice;
import com.google.inject.Injector;

import top.cairbin.ftp.client.listener.EventSource;
import top.cairbin.ftp.client.listener.ListenerRegistry;

public class App 
{

    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new AppModule());
    }
}
