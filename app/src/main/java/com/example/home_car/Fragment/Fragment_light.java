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

import com.example.home_car.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment_light extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private static final String SHARED_PREFS = "LightPreferences";
    private static final String LIGHT_STATUS1 = "light_status1";
    private static final String LIGHT_STATUS2 = "light_status2";
    private static final String LIGHT_STATUS3 = "light_status3";
    private static final String LIGHT_STATUS4 = "light_status4";
    private static final String LIGHT_STATUS5 = "light_status5";
    private static final String LIGHT_STATUS6 = "light_status6";
    private static final String LIGHT_STATUS7 = "light_status7";
    private LinearLayout LL_light1, LL_light2, LL_light3, LL_light4,LL_light5, LL_light6, LL_light7;
    private ImageView Iv_light1, Iv_light2, Iv_light3, Iv_light4, Iv_light5, Iv_light6, Iv_light7;
    private TextView Tv_light1, Tv_light2, Tv_light3, Tv_light4, Tv_light5, Tv_light6, Tv_light7;

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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

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
        boolean lightStatus1 = sharedPreferences.getBoolean(LIGHT_STATUS1, false);
        boolean lightStatus2 = sharedPreferences.getBoolean(LIGHT_STATUS2, false);
        boolean lightStatus3 = sharedPreferences.getBoolean(LIGHT_STATUS3, false);
        boolean lightStatus4 = sharedPreferences.getBoolean(LIGHT_STATUS4, false);
        boolean lightStatus5 = sharedPreferences.getBoolean(LIGHT_STATUS5, false);
        boolean lightStatus6 = sharedPreferences.getBoolean(LIGHT_STATUS6, false);
        boolean lightStatus7 = sharedPreferences.getBoolean(LIGHT_STATUS7, false);

        updateLightImagview(Iv_light1,lightStatus1);
        updateLightImagview(Iv_light2,lightStatus2);
        updateLightImagview(Iv_light3,lightStatus3);
        updateLightImagview(Iv_light4,lightStatus4);
        updateLightImagview(Iv_light5,lightStatus5);
        updateLightImagview(Iv_light6,lightStatus6);
        updateLightImagview(Iv_light7,lightStatus7);
        updateLightLinearLayout(LL_light1,lightStatus1);
        updateLightLinearLayout(LL_light2,lightStatus2);
        updateLightLinearLayout(LL_light3,lightStatus3);
        updateLightLinearLayout(LL_light4,lightStatus4);
        updateLightLinearLayout(LL_light2,lightStatus5);
        updateLightLinearLayout(LL_light3,lightStatus6);
        updateLightLinearLayout(LL_light4,lightStatus7);
        updateLightTextview(Tv_light1,lightStatus1);
        updateLightTextview(Tv_light2,lightStatus2);
        updateLightTextview(Tv_light3,lightStatus3);
        updateLightTextview(Tv_light4,lightStatus4);
        updateLightTextview(Tv_light2,lightStatus5);
        updateLightTextview(Tv_light3,lightStatus6);
        updateLightTextview(Tv_light4,lightStatus7);
        LL_light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS1, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS1, currentStatus);
                editor.apply();
                updateLightImagview( Iv_light1,currentStatus);
                updateLightLinearLayout(LL_light1,currentStatus);
                updateLightTextview(Tv_light1,currentStatus);
            }
        });

        LL_light2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS2, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS2, currentStatus);
                editor.apply();
                updateLightImagview(Iv_light2,currentStatus);
                updateLightLinearLayout(LL_light2,currentStatus);
                updateLightTextview(Tv_light2,currentStatus);
            }
        });

        LL_light3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS3, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS3, currentStatus);
                editor.apply();
                updateLightImagview(Iv_light3,currentStatus);
                updateLightLinearLayout(LL_light3,currentStatus);
                updateLightTextview(Tv_light3,currentStatus);
            }
        });

        LL_light4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS4, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS4, currentStatus);
                editor.apply();
                updateLightImagview(Iv_light4,currentStatus);
                updateLightLinearLayout(LL_light4,currentStatus);
                updateLightTextview(Tv_light4,currentStatus);
            }
        });
        LL_light5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS5, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS5, currentStatus);
                editor.apply();
                updateLightImagview(Iv_light5,currentStatus);
                updateLightLinearLayout(LL_light5,currentStatus);
                updateLightTextview(Tv_light5,currentStatus);
            }
        });
        LL_light6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS6, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS6, currentStatus);
                editor.apply();
                updateLightImagview(Iv_light6,currentStatus);
                updateLightLinearLayout(LL_light6,currentStatus);
                updateLightTextview(Tv_light6,currentStatus);
            }
        });
        LL_light7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = !sharedPreferences.getBoolean(LIGHT_STATUS7, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LIGHT_STATUS7, currentStatus);
                editor.apply();
                updateLightImagview(Iv_light7,currentStatus);
                updateLightLinearLayout(LL_light7,currentStatus);
                updateLightTextview(Tv_light7,currentStatus);
            }
        });

        return view;
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

}