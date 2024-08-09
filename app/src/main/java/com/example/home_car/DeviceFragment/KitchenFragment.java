package com.example.home_car.DeviceFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.home_car.DataSender.TcpClient;
import com.example.home_car.R;

public class KitchenFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout LL_inducooker,LL_icebox,LL_hotcup,LL_Rangehood;
    private ImageView Iv_inducooker,Iv_icebox,Iv_hotcup,Iv_Rangehood;
    private ImageView Btn_inducooker,Btn_icebox,Btn_hotcup,Btn_rangehood;
    private boolean isInducookerOn,isIceboxOn,isHotcupOn,isRangehoodOn;

    private TcpClient tcpClient;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HOME_CAR_DATA";
    private static final String INDUCOOKER_KEY = "inducooker_status";
    private static final String ICEBOX_KEY = "icebox_status";
    private static final String HOTCUP_KEY = "hotcup_status";
    private static final String RANGEHOOD_KEY = "rangehood_status";

    public KitchenFragment() {
        // Required empty public constructor
    }

    public static KitchenFragment newInstance(String param1, String param2) {
        KitchenFragment fragment = new KitchenFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kitchen, container, false);

        tcpClient = new TcpClient(getContext());

        Iv_inducooker=view.findViewById(R.id.iv_inducooker);
        Iv_hotcup=view.findViewById(R.id.iv_hotcup);
        Iv_icebox=view.findViewById(R.id.iv_icebox);
        Iv_Rangehood=view.findViewById(R.id.iv_Rangehood);
        LL_inducooker=view.findViewById(R.id.ll_inducooker);
        LL_hotcup=view.findViewById(R.id.ll_hotcup);
        LL_icebox=view.findViewById(R.id.ll_icebox);
        LL_Rangehood=view.findViewById(R.id.ll_Rangehood);
        Btn_inducooker=view.findViewById(R.id.btn_inducooker);
        Btn_hotcup=view.findViewById(R.id.btn_hotcup);
        Btn_icebox=view.findViewById(R.id.btn_icebox);
        Btn_rangehood=view.findViewById(R.id.btn_Rangehood);

        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        isIceboxOn = sharedPreferences.getBoolean(ICEBOX_KEY, false);
        isHotcupOn = sharedPreferences.getBoolean(HOTCUP_KEY, false);
        isInducookerOn = sharedPreferences.getBoolean(INDUCOOKER_KEY, false);
        isRangehoodOn = sharedPreferences.getBoolean(RANGEHOOD_KEY, false);


        updateDeviceImagview(Iv_icebox,isIceboxOn);
        updateDeviceImagview(Iv_hotcup,isHotcupOn);
        updateDeviceImagview(Iv_inducooker,isInducookerOn);
        updateDeviceImagview(Iv_Rangehood,isRangehoodOn);
        updateDeviceImagview(Btn_icebox,isIceboxOn);
        updateDeviceImagview(Btn_hotcup,isHotcupOn);
        updateDeviceImagview(Btn_inducooker,isInducookerOn);
        updateDeviceImagview(Btn_rangehood,isRangehoodOn);
//        fetchAndUpdateHardwareData();//从硬件获取数据更新UI


        LL_inducooker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInducookerOn = !isInducookerOn;
//                updateInducookerStatus();
                updateDeviceImagview(Iv_inducooker,isInducookerOn);
                updateDeviceImagview(Btn_inducooker,isInducookerOn);
//                updateDeviceLinearLayout(LL_inducooker,isInducookerOn);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_icebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIceboxOn = !isIceboxOn;
//                updateIceboxStatus();
                updateDeviceImagview(Iv_icebox,isIceboxOn);
                updateDeviceImagview(Btn_icebox,isIceboxOn);
//                updateDeviceLinearLayout(LL_icebox,isIceboxOn);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_hotcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHotcupOn = !isHotcupOn;
//                updateHotcupStatus();
                updateDeviceImagview(Iv_hotcup,isHotcupOn);
                updateDeviceImagview(Btn_hotcup,isHotcupOn);
//                updateDeviceLinearLayout(LL_hotcup,isHotcupOn);
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_Rangehood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRangehoodOn = !isRangehoodOn;
//                updateRangehoodStatus();
                updateDeviceImagview(Iv_Rangehood,isRangehoodOn);
                updateDeviceImagview(Btn_rangehood,isRangehoodOn);
//                updateDeviceLinearLayout(LL_Rangehood,isRangehoodOn);
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
//    private void updateInducookerStatus() {
//        if (isInducookerOn) {
//            Iv_inducooker.setSelected(true);
//        } else {
//            Iv_inducooker.setSelected(false);
//        }
//    }
//
//
//    private void updateHotcupStatus() {
//        if (isHotcupOn) {
//            Iv_hotcup.setSelected(true);
//        } else {
//            Iv_hotcup.setSelected(false);
//        }
//    }
//
//    private void updateIceboxStatus() {
//        if (isIceboxOn) {
//            Iv_icebox.setSelected(true);
//        } else {
//            Iv_icebox.setSelected(false);
//        }
//    }
//
//    private void updateRangehoodStatus() {
//        if (isRangehoodOn) {
//            Iv_Rangehood.setSelected(true);
//        } else {
//            Iv_Rangehood.setSelected(false);
//        }
//    }

    private void updateDeviceImagview(ImageView imageView,  boolean isOn) {
        if (isOn) {
            imageView.setSelected(true);
            // 假设light_on是开灯时的图标
        } else {
            imageView.setSelected(false);
            // 假设light_off是关灯时的图标
        }
    }
//    private void updateDeviceLinearLayout(LinearLayout linearLayout,  boolean isOn) {
//        if (isOn) {
//            linearLayout.setSelected(true);
//            // 假设light_on是开灯时的图标
//        } else {
//            linearLayout.setSelected(false);
//            // 假设light_off是关灯时的图标
//        }
//    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(HOTCUP_KEY, isHotcupOn);
        editor.putBoolean(ICEBOX_KEY, isIceboxOn);
        editor.putBoolean(INDUCOOKER_KEY, isInducookerOn);
        editor.putBoolean(RANGEHOOD_KEY, isRangehoodOn);
        editor.apply();
    }
}