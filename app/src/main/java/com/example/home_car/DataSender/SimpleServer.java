package com.example.home_car.DataSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;

        try {
            // 创建服务器端的ServerSocket，端口号为8080
            serverSocket = new ServerSocket(8080);
            System.out.println("服务器启动，等待客户端连接...");

            // 接受客户端连接
            clientSocket = serverSocket.accept();
            System.out.println("客户端已连接：" + clientSocket.getInetAddress());

            // 从客户端读取数据
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String receivedData;

            while ((receivedData = in.readLine()) != null) {
                System.out.println("接收到的数据：" + receivedData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (in != null) in.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

