package com.example.home_car.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.home_car.DataSender.TcpClient;
import com.example.home_car.R;


public class Fragment_waterelec extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView Iv_waterPump,Iv_inverter;
    private boolean iswaterPumpOn;
    private boolean isInverterOn;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HOME_CAR_DATA";
    private static final String WATERPUMP_KEY = "waterPump_status";
    private static final String INVERTER_KEY = "inverter_status";
    private TcpClient tcpClient;

    public Fragment_waterelec() {
        // Required empty public constructor
    }


    public static Fragment_waterelec newInstance(String param1, String param2) {
        Fragment_waterelec fragment = new Fragment_waterelec();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        tcpClient=new TcpClient(getContext());

        Iv_waterPump = view.findViewById(R.id.iv_waterPump);
        Iv_inverter = view.findViewById(R.id.iv_inverter);

        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        iswaterPumpOn = sharedPreferences.getBoolean(WATERPUMP_KEY, false);
        isInverterOn = sharedPreferences.getBoolean(INVERTER_KEY,false);
        updateWaterPumpStatus();
        updateInverterStatus();

        Iv_waterPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iswaterPumpOn =!iswaterPumpOn;
                updateWaterPumpStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });

        Iv_inverter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isInverterOn =!isInverterOn;
                updateInverterStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });


        return view;
        // Inflate the layout for this fragment
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tcpClient != null) {
            tcpClient.closeConnection();  // 假设 TcpClient 有一个关闭连接的方法
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void updateLightImageView(ImageView imageView, boolean isOn) {
        if (isOn) {
            imageView.setSelected(true); // 假设light_on是开灯时的图标
        } else {
            imageView.setSelected(false); // 假设light_off是关灯时的图标
        }
    }

    private void updateWaterPumpStatus() {
        if (iswaterPumpOn) {
            Iv_waterPump.setSelected(true);
        } else {
            Iv_waterPump.setSelected(false);
        }
    }

    private void updateInverterStatus() {
        if (isInverterOn) {
            Iv_inverter.setSelected(true);
        } else {
            Iv_inverter.setSelected(false);
        }
    }
    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(WATERPUMP_KEY, iswaterPumpOn);
        editor.putBoolean(INVERTER_KEY,isInverterOn);
        editor.apply();
    }
}