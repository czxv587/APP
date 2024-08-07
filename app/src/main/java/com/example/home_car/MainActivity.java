package com.example.home_car;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.home_car.DataGraph.BatteryView;
import com.example.home_car.DataSender.TcpClient;
import com.example.home_car.Fragment.Fragment_device;
import com.example.home_car.Fragment.Fragment_waterelec;
import com.example.home_car.Fragment.Fragment_light;
import com.example.home_car.Fragment.Fragment_safety;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private LinearLayout LL_light,LL_elecequipment,LL_elecsystem,LL_other;
    private ImageView Iv_light,Iv_elecequipment,Iv_elecsystem,Iv_other;
    private TextView Tv_light,Tv_elecequipment,Tv_elecsystem,Tv_other,Tv_testNum;
    private BatteryView batteryView;

    FragmentManager fragmentManager;
    private TcpClient tcpClient;

    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tv_testNum=findViewById(R.id.testNum);

        //设置电池电量为50%
        batteryView = findViewById(R.id.batteryView1);
        batteryView.setBatteryLevel(50);


        tcpClient = new TcpClient(this);


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
                Fragment_safety fragment4= Fragment_safety.newInstance("","");
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

}