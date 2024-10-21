/*
 * @Description: 注入容器的静态工厂
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 00:03:53
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 00:05:11
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorFactory {
    private static final Injector injector = Guice.createInjector(new AppModule());

    public static Injector getInjector() {
        return injector;
    }
}
