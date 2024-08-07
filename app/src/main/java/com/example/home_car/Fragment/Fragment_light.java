package com.example.home_car.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.home_car.DataSender.TcpClient;
import com.example.home_car.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment_light extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private static final String PREFS_NAME = "HOME_CAR_DATA";
    private static final String LIGHT_STATUS1 = "light_status1";
    private static final String LIGHT_STATUS2 = "light_status2";
    private static final String LIGHT_STATUS3 = "light_status3";
    private static final String LIGHT_STATUS4 = "light_status4";
    private static final String LIGHT_STATUS5 = "light_status5";
    private static final String LIGHT_STATUS6 = "light_status6";
    private static final String LIGHT_STATUS7 = "light_status7";
    private boolean islight1On,islight2On,islight3On,islight4On,islight5On,islight6On,islight7On;
    private LinearLayout LL_light1, LL_light2, LL_light3, LL_light4,LL_light5, LL_light6, LL_light7;
    private ImageView Iv_light1, Iv_light2, Iv_light3, Iv_light4, Iv_light5, Iv_light6, Iv_light7;
    private TextView Tv_light1, Tv_light2, Tv_light3, Tv_light4, Tv_light5, Tv_light6, Tv_light7;
    private TcpClient tcpClient;
    private SharedPreferences sharedPreferences;

    FragmentManager fragmentManager;


    public Fragment_light() {
        // Required empty public constructor
    }

    public static Fragment_light newInstance(String param1, String param2) {
        Fragment_light fragment = new Fragment_light();
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
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        tcpClient=new TcpClient(getContext());

        // 获取灯光开关控件
        Tv_light1=view.findViewById(R.id.tv_light1);
        Tv_light2=view.findViewById(R.id.tv_light2);
        Tv_light3=view.findViewById(R.id.tv_light3);
        Tv_light4=view.findViewById(R.id.tv_light4);
        Tv_light5=view.findViewById(R.id.tv_light5);
        Tv_light6=view.findViewById(R.id.tv_light6);
        Tv_light7=view.findViewById(R.id.tv_light7);
        LL_light1 = view.findViewById(R.id.ll_light1);
        LL_light2 = view.findViewById(R.id.ll_light2);
        LL_light3 = view.findViewById(R.id.ll_light3);
        LL_light4 = view.findViewById(R.id.ll_light4);
        LL_light5 = view.findViewById(R.id.ll_light5);
        LL_light6 = view.findViewById(R.id.ll_light6);
        LL_light7 = view.findViewById(R.id.ll_light7);
        Iv_light1 = view.findViewById(R.id.iv_light1);
        Iv_light2 = view.findViewById(R.id.iv_light2);
        Iv_light3 = view.findViewById(R.id.iv_light3);
        Iv_light4 = view.findViewById(R.id.iv_light4);
        Iv_light5 = view.findViewById(R.id.iv_light5);
        Iv_light6 = view.findViewById(R.id.iv_light6);
        Iv_light7 = view.findViewById(R.id.iv_light7);

        // 从SharedPreferences中恢复灯光开关状态
        islight1On = sharedPreferences.getBoolean(LIGHT_STATUS1, false);
        islight2On = sharedPreferences.getBoolean(LIGHT_STATUS2, false);
        islight3On = sharedPreferences.getBoolean(LIGHT_STATUS3, false);
        islight4On = sharedPreferences.getBoolean(LIGHT_STATUS4, false);
        islight5On = sharedPreferences.getBoolean(LIGHT_STATUS5, false);
        islight6On = sharedPreferences.getBoolean(LIGHT_STATUS6, false);
        islight7On = sharedPreferences.getBoolean(LIGHT_STATUS7, false);

        updateLightImagview(Iv_light1,islight1On);
        updateLightImagview(Iv_light2,islight2On);
        updateLightImagview(Iv_light3,islight3On);
        updateLightImagview(Iv_light4,islight4On);
        updateLightImagview(Iv_light5,islight5On);
        updateLightImagview(Iv_light6,islight6On);
        updateLightImagview(Iv_light7,islight7On);
        updateLightLinearLayout(LL_light1,islight1On);
        updateLightLinearLayout(LL_light2,islight2On);
        updateLightLinearLayout(LL_light3,islight3On);
        updateLightLinearLayout(LL_light4,islight4On);
        updateLightLinearLayout(LL_light5,islight5On);
        updateLightLinearLayout(LL_light6,islight6On);
        updateLightLinearLayout(LL_light7,islight7On);
        updateLightTextview(Tv_light1,islight1On);
        updateLightTextview(Tv_light2,islight2On);
        updateLightTextview(Tv_light3,islight3On);
        updateLightTextview(Tv_light4,islight4On);
        updateLightTextview(Tv_light5,islight5On);
        updateLightTextview(Tv_light6,islight6On);
        updateLightTextview(Tv_light7,islight7On);
        LL_light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight1On=!islight1On;
                updateLightImagview(Iv_light1,islight1On);
                updateLightTextview(Tv_light1,islight1On);
                updateLightLinearLayout(LL_light1,islight1On);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });

        LL_light2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight2On=!islight2On;
                updateLightImagview(Iv_light2,islight2On);
                updateLightTextview(Tv_light2,islight2On);
                updateLightLinearLayout(LL_light2,islight2On);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });

        LL_light3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight3On=!islight3On;
                updateLightImagview(Iv_light3,islight3On);
                updateLightTextview(Tv_light3,islight3On);
                updateLightLinearLayout(LL_light3,islight3On);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });

        LL_light4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight4On=!islight4On;
                updateLightImagview(Iv_light4,islight4On);
                updateLightTextview(Tv_light4,islight4On);
                updateLightLinearLayout(LL_light4,islight4On);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_light5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight5On=!islight5On;
                updateLightImagview(Iv_light5,islight5On);
                updateLightTextview(Tv_light5,islight5On);
                updateLightLinearLayout(LL_light5,islight5On);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_light6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight6On=!islight6On;
                updateLightImagview(Iv_light6,islight6On);
                updateLightTextview(Tv_light6,islight6On);
                updateLightLinearLayout(LL_light6,islight6On);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_light7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                islight7On=!islight7On;
                updateLightImagview(Iv_light7,islight7On);
                updateLightTextview(Tv_light7,islight7On);
                updateLightLinearLayout(LL_light7,islight7On);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tcpClient != null) {
            tcpClient.closeConnection();  // 假设 TcpClient 有一个关闭连接的方法
        }
    }

    private void updateLightImagview(ImageView imageView,  boolean isOn) {
        if (isOn) {
            imageView.setSelected(true);
            // 假设light_on是开灯时的图标
        } else {
            imageView.setSelected(false);
            // 假设light_off是关灯时的图标
        }
    }
    private void updateLightLinearLayout(LinearLayout linearLayout,  boolean isOn) {
        if (isOn) {
            linearLayout.setSelected(true);
            // 假设light_on是开灯时的图标
        } else {
            linearLayout.setSelected(false);
            // 假设light_off是关灯时的图标
        }
    }
    private void updateLightTextview(TextView textView,  boolean isOn) {
        if (isOn) {
            textView.setTextColor(getResources().getColor(R.color.white));
            // 假设light_on是开灯时的图标
        } else {
            textView.setTextColor(getResources().getColor(R.color.black3));
            // 假设light_off是关灯时的图标
        }
    }
    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LIGHT_STATUS1, islight1On);
        editor.putBoolean(LIGHT_STATUS2, islight2On);
        editor.putBoolean(LIGHT_STATUS3, islight3On);
        editor.putBoolean(LIGHT_STATUS4, islight4On);
        editor.putBoolean(LIGHT_STATUS5, islight5On);
        editor.putBoolean(LIGHT_STATUS6, islight6On);
        editor.putBoolean(LIGHT_STATUS7, islight7On);
        editor.apply();
    }

}