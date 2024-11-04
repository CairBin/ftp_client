/*
 * @Description: 程序入口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 15:36:44
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-05 00:02:31
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import com.google.inject.Injector;

import top.cairbin.ftp.logger.ILogger;
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        if(args.length != 2){
            System.out.println("Usage: java App <host> <port>");
            return;
        }

        Injector injector = InjectorFactory.getInjector();
        Run run = new Run();
        injector.injectMembers(run);
        ILogger logger = injector.getInstance(ILogger.class);
        
        try {
            run.run(args[0], Integer.parseInt(args[1]));
        }catch(Exception e){
            System.out.println("Error: Connection timed out");
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
