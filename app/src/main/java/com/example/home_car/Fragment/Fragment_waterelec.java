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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_car.R;


public class Fragment_waterelec extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String SHARED_PREFS = "EquipPreferences";
    private static final String AIRCON_STATUS = "aircon_status";
    private static final String ICEBOX_STATUS = "icebox_status";
    private static final String MICROOVEN_STATUS = "microoven_status";

    private LinearLayout LL_aircon,LL_microoven,LL_icebox;
    private ImageView Iv_aircon,Iv_icebox,Iv_microoven;
    private TextView Tv_aircon,Tv_icebox,Tv_microoven;

    private String mParam1;
    private String mParam2;


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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // 获取灯光开关控件
        LL_aircon = view.findViewById(R.id.ll_aircon);
        LL_icebox = view.findViewById(R.id.ll_icebox);
        LL_microoven = view.findViewById(R.id.ll_microoven);

        Iv_aircon = view.findViewById(R.id.iv_aircon);
        Iv_icebox = view.findViewById(R.id.iv_icebox);
        Iv_microoven = view.findViewById(R.id.iv_microoven);

        // 从SharedPreferences中恢复灯光开关状态
        boolean lightStatus1 = sharedPreferences.getBoolean(AIRCON_STATUS, false);
        boolean lightStatus2 = sharedPreferences.getBoolean(ICEBOX_STATUS, false);
        boolean lightStatus3 = sharedPreferences.getBoolean(MICROOVEN_STATUS, false);

        updateLightImageView(Iv_aircon, lightStatus1);
        updateLightImageView(Iv_icebox, lightStatus2);
        updateLightImageView(Iv_microoven, lightStatus3);

        LL_aircon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(AIRCON_STATUS, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(AIRCON_STATUS, currentStatus);
                editor.apply();
                updateLightImageView(Iv_aircon, currentStatus);
            }
        });

        LL_icebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(ICEBOX_STATUS, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ICEBOX_STATUS, currentStatus);
                editor.apply();
                updateLightImageView(Iv_icebox, currentStatus);
            }
        });

        LL_microoven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(MICROOVEN_STATUS, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(MICROOVEN_STATUS, currentStatus);
                editor.apply();
                updateLightImageView(Iv_microoven, currentStatus);
            }
        });



        return view;
        // Inflate the layout for this fragment
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
}