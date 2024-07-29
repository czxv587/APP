package com.example.home_car;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.home_car.DataSender.DataSend;
import com.example.home_car.Fragment.Fragment_device;
import com.example.home_car.Fragment.Fragment_waterelec;
import com.example.home_car.Fragment.Fragment_light;
import com.example.home_car.Fragment.Fragment_other;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout LL_light,LL_elecequipment,LL_elecsystem,LL_other;
    private ImageView Iv_light,Iv_elecequipment,Iv_elecsystem,Iv_other;
    private TextView Tv_light,Tv_elecequipment,Tv_elecsystem,Tv_other;
    private BatteryView batteryView;
    private Button sendButton;
    FragmentManager fragmentManager;
    private DataSend dataSend;
    private Random random;
    private Socket socket;
    private OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置电池电量为50%
        batteryView = findViewById(R.id.batteryView1);
        batteryView.setBatteryLevel(50);
        
        //生成随机数据
//        random = new Random();
//
//        // 初始化DataSender，连接到指定IP地址和端口
//        try {
//            dataSend = new DataSend("10.99.108.175", 8080);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "无法连接到服务器", Toast.LENGTH_SHORT).show();
//        }

        // 获取按钮并设置点击事件监听器
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 生成0到100之间的随机数
//                int randomNumber = random.nextInt(101);
//                String data = String.valueOf(randomNumber);
//
//                // 发送随机数
//                if (dataSend != null) {
//                    try {
//                        dataSend.sendData(data);
//                        Toast.makeText(MainActivity.this, "发送数据: " + data, Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Toast.makeText(MainActivity.this, "数据发送失败", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(MainActivity.this, "DataSend 未初始化", Toast.LENGTH_SHORT).show();
//                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 假设你要连接的IP和端口
                            socket = new Socket("10.99.108.175", 8080);
                            outputStream = socket.getOutputStream();
                            // 生成0到100之间的随机数
                            int randomNumber = (int) (Math.random() * 101);
                            String data = String.valueOf(randomNumber);
                            outputStream.write(data.getBytes());
                            outputStream.flush();
                            // 更新UI需要在主线程中进行
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "发送数据成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "数据发送失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } finally {
                            try {
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (socket != null) {
                                    socket.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

       //初始化Fragment
        initView();
        initEvent();

    }

    private void initEvent() {
        Iv_light.setSelected(true);
        Tv_light.setTextColor(getResources().getColor(R.color.black1));
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment_light fragment1= Fragment_light.newInstance("","");

        fragmentTransaction.replace(R.id.fcv,fragment1).commit();
        LL_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetleftstate();
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_light fragment1= new Fragment_light();
                fragmentTransaction.replace(R.id.fcv,fragment1).commit();
                fragmentTransaction.addToBackStack(null);
                Iv_light.setSelected(true);
                Tv_light.setTextColor(getResources().getColor(R.color.black1));
            }
        });
        LL_elecequipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetleftstate();
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_waterelec fragment2= Fragment_waterelec.newInstance("","");
                fragmentTransaction.replace(R.id.fcv,fragment2).commit();
                Iv_elecequipment.setSelected(true);
                Tv_elecequipment.setTextColor(getResources().getColor(R.color.black1));

            }
        });
        LL_elecsystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetleftstate();
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_device fragment3= Fragment_device.newInstance("","");
                fragmentTransaction.replace(R.id.fcv,fragment3).commit();
                Iv_elecsystem.setSelected(true);
                Tv_elecsystem.setTextColor(getResources().getColor(R.color.black1));


            }
        });
        LL_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetleftstate();
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_other fragment4= Fragment_other.newInstance("","");
                fragmentTransaction.replace(R.id.fcv,fragment4).commit();
                Iv_other.setSelected(true);
                Tv_other.setTextColor(getResources().getColor(R.color.black1));

            }
        });
    }
    private void resetleftstate(){
        Iv_light.setSelected(false);
        Tv_light.setTextColor(getResources().getColor(R.color.white1));
        Iv_elecequipment.setSelected(false);
        Tv_elecequipment.setTextColor(getResources().getColor(R.color.white1));
        Iv_elecsystem.setSelected(false);
        Tv_elecsystem.setTextColor(getResources().getColor(R.color.white1));
        Iv_other.setSelected(false);
        Tv_other.setTextColor(getResources().getColor(R.color.white1));

    }
    private void initView() {
        LL_light=findViewById(R.id.ll_Light);
        LL_elecequipment=findViewById(R.id.ll_ElecEquipment);
        LL_elecsystem=findViewById(R.id.ll_ElecSystem);
        LL_other=findViewById(R.id.ll_Other);

        Iv_light=findViewById(R.id.iv_Light);
        Iv_elecequipment=findViewById(R.id.iv_ElecEquipment);
        Iv_elecsystem=findViewById(R.id.iv_ElecSystem);
        Iv_other=findViewById(R.id.iv_Other);
        
        Tv_light=findViewById(R.id.tv_Light);
        Tv_elecequipment=findViewById(R.id.tv_ElecEquipment);
        Tv_elecsystem=findViewById(R.id.tv_ElecSystem);
        Tv_other=findViewById(R.id.tv_Other);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭Socket连接
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}