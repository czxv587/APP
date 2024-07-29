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
import com.example.home_car.DataSender.HardwareData;
import com.example.home_car.R;


public class LivingRoomFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private TextView temperatureTextView,Tv_model;
    private LinearLayout increaseButton,decreaseButton,airswitch,modelswitch;
    private ImageView Iv_aircon,Iv_switch,Iv_model,Iv_socketpower,Iv_usbpower,Iv_colorglass,Iv_basspower,Iv_poweramplifier;
    private int temperature;
    private boolean isAirPowerOn;
    private boolean isCoolMode;
    private boolean isSocketPowerOn;
    private boolean isUsbPowerOn;
    private boolean isColorGlassOn;
    private boolean isBassPowerOn;
    private boolean isPowerAmplifierOn;
    private final int MIN_TEMPERATURE = 16;
    private final int MAX_TEMPERATURE = 30;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "temperature_prefs";
    private static final String TEMPERATURE_KEY = "current_temperature";
    private static final String AIRPOWER_KEY = "airpower_status";
    private static final String MODE_KEY = "mode_status";
    private static final String SOCKETPOWER_KEY = "socketpower_status";
    private static final String USBPOWER_KEY = "usbpower_status";
    private static final String COLORGLASS_KEY = "clorglass_status";
    private static final String BASSPOWER_KEY = "basspower_status";
    private static final String POWERAMPLIFIER_KEY = "poweramplifier_status";

    public LivingRoomFragment() {
        // Required empty public constructor
    }


    public static LivingRoomFragment newInstance(String param1, String param2) {
        LivingRoomFragment fragment = new LivingRoomFragment();
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
        View view = inflater.inflate(R.layout.fragment_living_room, container, false);

        temperatureTextView=view.findViewById(R.id.tv_temp);
        Tv_model=view.findViewById(R.id.tv_model);
        increaseButton=view.findViewById(R.id.increaseBnt);
        decreaseButton=view.findViewById(R.id.decreaseBnt);
        airswitch=view.findViewById(R.id.airBnt);
        modelswitch=view.findViewById(R.id.modelBnt);
        Iv_aircon=view.findViewById(R.id.iv_aircon);
        Iv_switch=view.findViewById(R.id.iv_switch);
        Iv_model=view.findViewById(R.id.iv_model);
        Iv_socketpower=view.findViewById(R.id.iv_socketpower);
        Iv_usbpower=view.findViewById(R.id.iv_usbpower);
        Iv_colorglass=view.findViewById(R.id.iv_colerglass);
        Iv_basspower=view.findViewById(R.id.iv_basspower);
        Iv_poweramplifier=view.findViewById(R.id.iv_poweramplifier);

        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        temperature = sharedPreferences.getInt(TEMPERATURE_KEY, MIN_TEMPERATURE);
        isAirPowerOn = sharedPreferences.getBoolean(AIRPOWER_KEY, false);
        isCoolMode = sharedPreferences.getBoolean(MODE_KEY, true);
        isSocketPowerOn = sharedPreferences.getBoolean(SOCKETPOWER_KEY, false);
        isUsbPowerOn = sharedPreferences.getBoolean(USBPOWER_KEY, false);
        isColorGlassOn = sharedPreferences.getBoolean(COLORGLASS_KEY, false);
        isBassPowerOn = sharedPreferences.getBoolean(BASSPOWER_KEY, false);
        isPowerAmplifierOn = sharedPreferences.getBoolean(POWERAMPLIFIER_KEY, false);
        updateTemperatureDisplay();
        updatePowerStatus();
        updateModeStatus();
        fetchAndUpdateHardwareData();

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperature < MAX_TEMPERATURE) {
                    temperature++;
                    updateTemperatureDisplay();
                    savePreferences();
                    HardwareController.sendTemperatureUpdate(temperature);
                }
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperature > MIN_TEMPERATURE) {
                    temperature--;
                    updateTemperatureDisplay();
                    savePreferences();
                    HardwareController.sendTemperatureUpdate(temperature);
                }
            }
        });

        airswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAirPowerOn = !isAirPowerOn;
                updatePowerStatus();
                savePreferences();
                HardwareController.sendPowerUpdate(isAirPowerOn);
            }
        });
        modelswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCoolMode = !isCoolMode;
                updateModeStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isCoolMode);
            }
        });
        Iv_socketpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSocketPowerOn = !isSocketPowerOn;
                updateModeStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isSocketPowerOn);
            }
        });
        Iv_usbpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUsbPowerOn = !isUsbPowerOn;
                updateModeStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isUsbPowerOn);
            }
        });
        Iv_colorglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isColorGlassOn = !isColorGlassOn;
                updateModeStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isColorGlassOn);
            }
        });
        Iv_basspower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBassPowerOn = !isBassPowerOn;
                updateModeStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isBassPowerOn);
            }
        });
        Iv_poweramplifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPowerAmplifierOn = !isPowerAmplifierOn;
                updateModeStatus();
                savePreferences();
                HardwareController.sendModeUpdate(isPowerAmplifierOn);
            }
        });
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
        editor.putBoolean(AIRPOWER_KEY, isAirPowerOn);
        editor.putBoolean(MODE_KEY, isCoolMode);
        editor.putBoolean(MODE_KEY, isCoolMode);
        editor.putBoolean(MODE_KEY, isCoolMode);
        editor.putBoolean(MODE_KEY, isCoolMode);
        editor.putBoolean(MODE_KEY, isCoolMode);
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