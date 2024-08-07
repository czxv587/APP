package com.example.home_car.DataSender;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class TcpClient {
    private static final String SERVER_IP = "10.99.108.175"; // 替换为服务端IP
    private static final int SERVER_PORT = 12345; // 替换为服务端端口
    private Context context;
    private OutputStream outputStream;
    private Socket socket;

    public TcpClient(Context context) {
        this.context = context;
    }

    public void sendTcpData() {
        try {
            // 建立Socket连接
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            SharedPreferences sharedPreferences = context.getSharedPreferences("HOME_CAR_DATA", Context.MODE_PRIVATE);
            Map<String, ?> allEntries = sharedPreferences.getAll();
            JSONObject jsonObject = new JSONObject(allEntries);
            String jsonString = jsonObject.toString();
            Log.d("JSON before change", jsonString);

            // 发送数据帧(目前传送JSON数据)
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(jsonString);

            // 关闭资源
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TCP", "Error: " + e.getMessage());
        }
    }
    public void closeConnection() {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            Log.d("TCP", "Connection closed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TCP", "Error closing connection: " + e.getMessage());
        }
    }
}
