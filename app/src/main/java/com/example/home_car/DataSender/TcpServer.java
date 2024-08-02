package com.example.home_car.DataSender;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class TcpServer {
    private static final String SERVER_IP = "10.99.108.175"; // 替换为服务端IP
    private static final int SERVER_PORT = 12345; // 替换为服务端端口
    private Context context;

    public TcpServer(Context context) {
        this.context = context;
    }

    public void recieveTcpData() {
        try {
            // 建立Socket连接
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);


            // 接收服务端发送的JSON数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            Log.d("Received JSON", response);

            while ((response = reader.readLine()) != null) {
                Log.d("Received JSON", response);

                // 解析服务端发送的JSON数据并更新SharedPreferences
                updateSharedPreferences(response);
            }

            // 关闭资源
            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TCP", "Error: " + e.getMessage());
        }
    }

    private void updateSharedPreferences(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            SharedPreferences sharedPreferences = context.getSharedPreferences("HOME_CAR_DATA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonObject.get(key);
                if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                } else if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                } else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                } else if (value instanceof String) {
                    editor.putString(key, (String) value);
                }
            }
            editor.apply();
            Log.d("SharedPreferences", "Updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SharedPreferences", "Error updating SharedPreferences: " + e.getMessage());
        }
    }

}
