/*
 * @Description: Logger接口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 16:48:08
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-13 17:17:02
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.logger;

public interface ILogger {
    /**
     * @description: debug级别日志
     */    
    public void debug(Object msg);

    /**
     * @description: 输出info级别日志
     */    
    public void info(Object msg);

    /**
     * @description: 输出warn级别日志
     */
    public void warn(Object msg);

    /**
     * @description: 输出error级别日志
     */
    public void error(Object msg);
}
