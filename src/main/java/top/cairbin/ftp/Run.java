/*
 * @Description: 启动
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-22 01:01:28
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-11-04 23:59:05
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

    private void cd(String[] params) throws Exception{
        if(params.length != 2){
            System.out.println("Usage: cd <path>");
            return;
        }
        String path = params[1];
        if(path == null || path.isEmpty()){
            System.out.println("Error: Path cannot be empty.");
            return;
        }

        this.client.cd(path);
    }

    private void deleteFile(String[] params) throws Exception{
        if(params.length != 2){
            System.out.println("Usage: deleteFile <filename>");
            return;
        }

        String filename = params[1];
        if(filename == null || filename.isEmpty()){
            System.out.println("Error: Filename cannot be empty.");
            return;
        }

        client.deleteFile(filename);
    }

    private void rename(String[] params) throws Exception{
        if(params.length!= 3){
            System.out.println("Usage: rename <oldpath> <newpath>");
            return;
        }

        String oldPath = params[1];
        String newPath = params[2];
        if(oldPath == null || oldPath.isEmpty() || newPath == null || newPath.isEmpty()){
            System.out.println("Error: Path cannot be empty.");
            return;
        }

        client.rename(oldPath, newPath);
    }

    private void remove(String[] params) throws Exception{
        if(params.length!= 2){
            System.out.println("Usage: rm <filename>");
            return;
        }

        String filename = params[1];
        if(filename == null || filename.isEmpty()){
            System.out.println("Error: Filename cannot be empty.");
            return;
        }

        client.remove(filename);
    }

    private void mkdir(String[] params) throws Exception{
        if(params.length!= 2){
            System.out.println("Usage: rm <filename>");
            return;
        }

        String filename = params[1];
        if(filename == null || filename.isEmpty()){
            System.out.println("Error: Filename cannot be empty.");
            return;
        }

        client.mkdir(filename);
    }

    private void pwd() throws Exception{
        client.pwd();
    }

    private void nls(String[] params) throws Exception{
        String path = null;
        if(params.length == 2)
            path = params[1];
        else if(params.length > 2){
            System.out.println("Too many parameters.");
            return;
        }

        this.client.nls(path);
    }

    private void get(String[] params) throws Exception{
        if(params.length!= 3){
            System.out.println("Usage: get <remote_path> <local_path>");
            return;
        }

        String remotePath = params[1];
        String localPath = params[2];
        if(remotePath == null || remotePath.isEmpty() || localPath == null || localPath.isEmpty()){
            System.out.println("Error: Path cannot be empty.");
            return;
        }

        client.download(remotePath, localPath);
    }

    private void put(String[] params) throws Exception{
        if(params.length!= 3){
            System.out.println("Usage: put <local_path> <remote_path>");
            return;
        }

        String localPath = params[1];
        String remotePath = params[2];
        if(localPath == null || localPath.isEmpty() || remotePath == null || remotePath.isEmpty()){
            System.out.println("Error: Path cannot be empty.");
            return;
        }

        client.upload(remotePath, localPath);
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
                }else if(cmd.equals("nls")){
                    this.nls(params);
                }else if(cmd.equals("cd")){
                    this.cd(params);
                }else if(cmd.equals("delete")){
                    this.deleteFile(params);
                }else if(cmd.equals("rename")){
                    this.rename(params);
                }else if(cmd.equals("rm")){
                    this.remove(params);
                }else if(cmd.equals("mkdir")){
                    this.mkdir(params);
                }else if(cmd.equals("pwd")){
                    this.pwd();
                }else if(cmd.equals("put")){
                    this.put(params);
                }else if(cmd.equals("get")){
                    this.get(params);
                }else{
                    System.out.println("Error: Unknown command.");
                }

            }
        }
    }
    
}
