/*
 * @Description: Socket配置
 * @License: MIT License
 * @Author: Xinyi Liu(CairBin)
 * @version: 1.0.0
 * @Date: 2024-10-13 16:10:15
 * @LastEditors: Xinyi Liu(CairBin)
 * @LastEditTime: 2024-10-13 16:13:59
 * @Copyright: Copyright (c) 2024 Xinyi Liu(CairBin)
 */
package top.cairbin.ftp.socket;
import lombok.Data;


@Data
public class SocketConfig { 
    public int port;        // 服务器端口号
    public String host;     // 服务器主机地址
    public String encode;   // 发送消息的编码
}
