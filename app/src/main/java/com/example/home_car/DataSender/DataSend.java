package com.example.home_car.DataSender;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class DataSend {
    private Socket socket;
    private OutputStream outputStream;

    // 构造函数，初始化Socket连接
    public DataSend(String ipAddress, int port) throws IOException {
        // 连接到指定IP地址和端口的服务器
        socket = new Socket("10.99.108.175", 8080);
        outputStream = socket.getOutputStream();
    }

    // 发送数据方法
    public void sendData(String data) throws IOException {
        // 将数据转换为字节数组并发送
        outputStream.write(data.getBytes());
        outputStream.flush();
    }

    // 关闭连接方法
    public void close() throws IOException {
        // 关闭输出流和Socket连接
        outputStream.close();
        socket.close();
    }
}
