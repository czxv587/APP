package com.example.home_car.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_car.DataSender.HardwareData;
import com.example.home_car.R;
import com.example.home_car.RoomPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class Fragment_device extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TextView temperatureTextView,Tv_model;
    private LinearLayout increaseButton,decreaseButton,airswitch,modelswitch;
    private ImageView Iv_aircon,Iv_switch,Iv_model;
    private int temperature;
    private boolean isPowerOn;
    private boolean isCoolMode;
    private final int MIN_TEMPERATURE = 16;
    private final int MAX_TEMPERATURE = 30;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "temperature_prefs";
    private static final String TEMPERATURE_KEY = "current_temperature";
    private static final String POWER_KEY = "power_status";
    private static final String MODE_KEY = "mode_status";



    public Fragment_device() {
        // Required empty public constructor
    }


    public static Fragment_device newInstance(String param1, String param2) {
        Fragment_device fragment = new Fragment_device();
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
        View view = inflater.inflate(R.layout.fragment_2, container, false);
//        temperatureTextView=view.findViewById(R.id.tv_temp);
//        Tv_model=view.findViewById(R.id.tv_model);
//        increaseButton=view.findViewById(R.id.increaseBnt);
//        decreaseButton=view.findViewById(R.id.decreaseBnt);
//        airswitch=view.findViewById(R.id.airBnt);
//        modelswitch=view.findViewById(R.id.modelBnt);
//        Iv_aircon=view.findViewById(R.id.iv_aircon);
//        Iv_switch=view.findViewById(R.id.iv_switch);
//        Iv_model=view.findViewById(R.id.iv_model);
//
//        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        temperature = sharedPreferences.getInt(TEMPERATURE_KEY, MIN_TEMPERATURE);
//        isPowerOn = sharedPreferences.getBoolean(POWER_KEY, true);
//        isCoolMode = sharedPreferences.getBoolean(MODE_KEY, false);
//        updateTemperatureDisplay();
//        updatePowerStatus();
//        updateModeStatus();
//        fetchAndUpdateHardwareData();
//
//        increaseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (temperature < MAX_TEMPERATURE) {
//                    temperature++;
//                    updateTemperatureDisplay();
//                    savePreferences();
//                    HardwareController.sendTemperatureUpdate(temperature);
//                }
//            }
//        });
//
//        decreaseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (temperature > MIN_TEMPERATURE) {
//                    temperature--;
//                    updateTemperatureDisplay();
//                    savePreferences();
//                    HardwareController.sendTemperatureUpdate(temperature);
//                }
//            }
//        });
//
//        airswitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isPowerOn = !isPowerOn;
//                updatePowerStatus();
//                savePreferences();
//                HardwareController.sendPowerUpdate(isPowerOn);
//            }
//        });
//        modelswitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isCoolMode = !isCoolMode;
//                updateModeStatus();
//                savePreferences();
//                HardwareController.sendModeUpdate(isCoolMode);
//            }
//        });


        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        RoomPagerAdapter pagerAdapter = new RoomPagerAdapter(getActivity());
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("厨房");
                        break;
                    case 1:
                        tab.setText("客厅");
                        break;
                    case 2:
                        tab.setText("卫浴");
                        break;
                }
            }
        }).attach();

        return view;
    }

    private void fetchAndUpdateHardwareData() {
        HardwareData hardwareData = HardwareData.fetchHardwareData();

        // 更新UI
        temperatureTextView.setText(String.valueOf(hardwareData.getTemperature()));
        if (hardwareData.isSwitchState()) {
            Iv_switch.setSelected(true);
            Iv_aircon.setSelected(true);
        } else {
            Iv_switch.setSelected(false);
            Iv_aircon.setSelected(false);
        }
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TEMPERATURE_KEY, temperature);
        editor.putBoolean(POWER_KEY, isPowerOn);
        editor.putBoolean(MODE_KEY, isCoolMode);
        editor.apply();
    }

    private void updateModeStatus() {
        if (isCoolMode) {
            Iv_model.setSelected(true);
            Tv_model.setText("制冷");
        } else {
            Iv_model.setSelected(false);
            Tv_model.setText("制热");
        }
    }

    private void updatePowerStatus() {
        if (isPowerOn) {
            Iv_switch.setSelected(true);
            Iv_aircon.setSelected(true);
        } else {
            Iv_switch.setSelected(false);
            Iv_aircon.setSelected(false);
        }
    }

    private void updateTemperatureDisplay() {
        temperatureTextView.setText(String.valueOf(temperature));
        
    }
}