/*
 * @Description: 上传文件
 * @License: MIT License
 * @Author: Yangbo Wang
 * @version: 1.0.0
 * @Date: 2024-10-29 21:00:00
 * @LastEditors: Yangbo Wang
 * @LastEditTime: 2024-11-04 21:40:00
 * @Copyright: Copyright (c) 2024 Yangbo Wang
 */

package top.cairbin.ftp.client.event;

import lombok.Getter;
import lombok.Setter;
import top.cairbin.ftp.client.listener.CustomEvent;

@Getter
@Setter
public class UploadFilesEvent extends CustomEvent{
    
    private String localDirectory;
    private String remoteDirectory;

    public UploadFilesEvent(Object source, String name,String localDirectory,String remoteDirectory) {
        super(source, name);
        this.localDirectory = localDirectory;
        this.remoteDirectory = remoteDirectory;
    }
    
}