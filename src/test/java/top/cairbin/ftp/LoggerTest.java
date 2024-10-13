/*
 * @Description: 日志测试
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 16:59:39
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-13 23:17:44
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import top.cairbin.ftp.logger.ILogger;

public class LoggerTest {

    private static Injector container;    
    private ILogger logger;

    public LoggerTest() {
        container = Guice.createInjector(new AppModule());
        this.logger = container.getInstance(ILogger.class);
    }

    @Test
    public void logDebug(){
        this.logger.debug("Hello! This is a log message!");
    }

    @Test
    public void logInfo(){
        this.logger.info("Hello! This is a log message!");
    }

    @Test
    public void logWarn(){
        this.logger.warn("Hello! This is a log message!");
    }

    @Test
    public void logError(){
        this.logger.error("Hello! This is a log message!");
    }
}
