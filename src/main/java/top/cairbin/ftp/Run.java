/*
 * @Description: 启动
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 01:01:28
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-22 02:34:50
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp;

import java.util.Scanner;

import com.google.inject.Inject;

import top.cairbin.ftp.client.IFtpClient;
import top.cairbin.ftp.logger.ILogger;

public class Run {
    @Inject
    private ILogger logger;

    @Inject
    private IFtpClient client;

    private boolean isRunning = true;

    private void quit() throws Exception{
        client.quit();
        isRunning = false;
    }

    private void ls(String[] params) throws Exception{
        String path = null;
        if(params.length == 2)
            path = params[1];
        else if(params.length > 2){
            System.out.println("Too many parameters.");
            return;
        }

        this.client.getList(path);
    }

    private void login(Scanner scanner) throws Exception{
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        client.login(username, password);
    }

    public void run(String host, int port) throws Exception{
        try (Scanner scanner = new Scanner(System.in)) {
            client.connect(host, port);
            login(scanner);
            while(isRunning){
                System.out.print("FTP>");
                String input = scanner.nextLine();
                if(input.isEmpty() || input.equals("\n") || input.equals("\r\n"))
                    continue;
                String[] params = input.split(" ");
                String cmd = params[0].toLowerCase();

                if(cmd.equals("quit")){
                    this.quit();
                }else if(cmd.equals("ls")){
                    this.ls(params);
                }

            }
        }
    }
    
}
