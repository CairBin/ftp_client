/*
 * @Description: 程序入口
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 15:36:44
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 04:55:47
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import com.google.inject.Injector;
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
        
        
        try {
            run.run(args[0], Integer.parseInt(args[1]));
        }catch(Exception e){
            System.out.println("Error: Connection timed out");
            System.exit(-1);
        }
    }
}
