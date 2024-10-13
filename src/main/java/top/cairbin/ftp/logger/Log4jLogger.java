/*
 * @Description: log4j ILogger实现
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 
 * @Date: 2024-10-13 16:50:04
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-13 22:29:06
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.logger;
import org.apache.logging.log4j.Logger;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;

@Singleton
public class Log4jLogger implements ILogger {
    private Logger logger = LogManager.getLogger("MyLogger");

    public Log4jLogger(){
        
    }
    

    @Override
    public void debug(Object msg) {
        this.logger.debug(msg);
    }

    @Override
    public void info(Object msg) {
        this.logger.info(msg);
    }

    @Override
    public void warn(Object msg) {
        this.logger.warn(msg);
    }

    @Override
    public void error(Object msg) {
        this.logger.error(msg);
    }
}