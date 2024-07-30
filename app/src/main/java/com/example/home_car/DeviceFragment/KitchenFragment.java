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
import android.widget.TextView;

import com.example.home_car.DataSender.HardwareController;
import com.example.home_car.R;

public class KitchenFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout LL_inducooker,LL_icebox,LL_hotcup,LL_Rangehood;
    private ImageView Iv_inducooker,Iv_icebox,Iv_hotcup,Iv_Rangehood;

    private boolean isInducookerOn;
    private boolean isIceboxOn;
    private boolean isHotcupOn;
    private boolean isRangehoodOn;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "Kitchen";
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

        Iv_inducooker=view.findViewById(R.id.iv_inducooker);
        Iv_hotcup=view.findViewById(R.id.iv_hotcup);
        Iv_icebox=view.findViewById(R.id.iv_icebox);
        Iv_Rangehood=view.findViewById(R.id.iv_Rangehood);
        LL_inducooker=view.findViewById(R.id.ll_inducooker);
        LL_hotcup=view.findViewById(R.id.ll_hotcup);
        LL_icebox=view.findViewById(R.id.ll_icebox);
        LL_Rangehood=view.findViewById(R.id.ll_Rangehood);


        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        isIceboxOn = sharedPreferences.getBoolean(ICEBOX_KEY, false);
        isHotcupOn = sharedPreferences.getBoolean(HOTCUP_KEY, false);
        isInducookerOn = sharedPreferences.getBoolean(INDUCOOKER_KEY, false);
        isRangehoodOn = sharedPreferences.getBoolean(RANGEHOOD_KEY, false);

        updateIceboxStatus();
        updateHotcupStatus();
        updateInducookerStatus();
        updateRangehoodStatus();

//        fetchAndUpdateHardwareData();//从硬件获取数据更新UI


        LL_inducooker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInducookerOn = !isInducookerOn;
                updateInducookerStatus();
                savePreferences();
                HardwareController.sendPowerUpdate(isInducookerOn);
            }
        });
        LL_icebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIceboxOn = !isIceboxOn;
                updateIceboxStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isIceboxOn);
            }
        });
        LL_hotcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHotcupOn = !isHotcupOn;
                updateHotcupStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isHotcupOn);
            }
        });
        LL_Rangehood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRangehoodOn = !isRangehoodOn;
                updateRangehoodStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isRangehoodOn);
            }
        });


        return view;
    }
    private void updateInducookerStatus() {
        if (isInducookerOn) {
            Iv_inducooker.setSelected(true);
        } else {
            Iv_inducooker.setSelected(false);
        }
    }


    private void updateHotcupStatus() {
        if (isHotcupOn) {
            Iv_hotcup.setSelected(true);
        } else {
            Iv_hotcup.setSelected(false);
        }
    }

    private void updateIceboxStatus() {
        if (isIceboxOn) {
            Iv_icebox.setSelected(true);
        } else {
            Iv_icebox.setSelected(false);
        }
    }

    private void updateRangehoodStatus() {
        if (isRangehoodOn) {
            Iv_Rangehood.setSelected(true);
        } else {
            Iv_Rangehood.setSelected(false);
        }
    }
    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(HOTCUP_KEY, isHotcupOn);
        editor.putBoolean(ICEBOX_KEY, isIceboxOn);
        editor.putBoolean(INDUCOOKER_KEY, isInducookerOn);
        editor.putBoolean(RANGEHOOD_KEY, isRangehoodOn);
        editor.apply();
    }
}