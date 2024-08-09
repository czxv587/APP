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

import com.example.home_car.DataSender.TcpClient;
import com.example.home_car.R;


public class LivingRoomFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private TextView temperatureTextView,Tv_model;
    private LinearLayout LL_socketpower,LL_usbpower,LL_colorglass,LL_basspower,LL_poweramplifier;
    private ImageView Iv_aircon,Iv_model,Iv_socketpower,Iv_usbpower,Iv_colorglass,Iv_basspower,Iv_poweramplifier;
    private ImageView Iv_airSwitch,IV_socketpowerSwitch,Iv_usbpowerSwitch,Iv_colorglassSwitch,Iv_basspowerSwitch,Iv_poweramplifierSwitch;
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
    private static final String PREFS_NAME = "HOME_CAR_DATA";
    private static final String TEMPERATURE_KEY = "current_temperature";
    private static final String AIRPOWER_KEY = "airpower_status";
    private static final String MODE_KEY = "mode_status";
    private static final String SOCKETPOWER_KEY = "socketpower_status";
    private static final String USBPOWER_KEY = "usbpower_status";
    private static final String COLORGLASS_KEY = "clorglass_status";
    private static final String BASSPOWER_KEY = "basspower_status";
    private static final String POWERAMPLIFIER_KEY = "poweramplifier_status";
    private TcpClient tcpClient;

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

        tcpClient = new TcpClient(getContext());

//        temperatureTextView=view.findViewById(R.id.tv_temp);
//        Tv_model=view.findViewById(R.id.tv_model);
//        increaseButton=view.findViewById(R.id.increaseBnt);
//        decreaseButton=view.findViewById(R.id.decreaseBnt);
//        airswitch=view.findViewById(R.id.airBnt);
//        modelswitch=view.findViewById(R.id.modelBnt);
        Iv_aircon=view.findViewById(R.id.iv_aircon);
//        Iv_model=view.findViewById(R.id.iv_model);
        Iv_socketpower=view.findViewById(R.id.iv_socketpower);
        Iv_usbpower=view.findViewById(R.id.iv_usbpower);
        Iv_colorglass=view.findViewById(R.id.iv_colerglass);
        Iv_basspower=view.findViewById(R.id.iv_basspower);
        Iv_poweramplifier=view.findViewById(R.id.iv_poweramplifier);

        Iv_airSwitch=view.findViewById(R.id.btn_aircon);
        IV_socketpowerSwitch=view.findViewById(R.id.btn_socketpower);
        Iv_usbpowerSwitch=view.findViewById(R.id.btn_usbpower);
        Iv_colorglassSwitch=view.findViewById(R.id.btn_colorglass);
        Iv_basspowerSwitch=view.findViewById(R.id.btn_basspower);
        Iv_poweramplifierSwitch=view.findViewById(R.id.btn_poweramplifier);

        LL_socketpower=view.findViewById(R.id.ll_socketpower);
        LL_usbpower=view.findViewById(R.id.ll_usbpower);
        LL_colorglass=view.findViewById(R.id.ll_colerglass);
        LL_basspower=view.findViewById(R.id.ll_basspower);
        LL_poweramplifier=view.findViewById(R.id.ll_poweramplifier);


        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

//        temperature = sharedPreferences.getInt(TEMPERATURE_KEY, MIN_TEMPERATURE);
        isAirPowerOn = sharedPreferences.getBoolean(AIRPOWER_KEY, false);
//        isCoolMode = sharedPreferences.getBoolean(MODE_KEY, true);
        isSocketPowerOn = sharedPreferences.getBoolean(SOCKETPOWER_KEY, false);
        isUsbPowerOn = sharedPreferences.getBoolean(USBPOWER_KEY, false);
        isColorGlassOn = sharedPreferences.getBoolean(COLORGLASS_KEY, false);
        isBassPowerOn = sharedPreferences.getBoolean(BASSPOWER_KEY, false);
        isPowerAmplifierOn = sharedPreferences.getBoolean(POWERAMPLIFIER_KEY, false);
//        updateTemperatureDisplay();
        updateAirPowerStatus();
//        updateAirModeStatus();
        updateSocketPowerStatus();
        updateUsbPowerStatus();
        updateColorGlassStatus();
        updateBassPowerStatus();
        updatePowerAmplifierStatus();

//        fetchAndUpdateHardwareData();//从硬件获取数据更新UI

//        increaseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (temperature < MAX_TEMPERATURE) {
//                    temperature++;
//                    updateTemperatureDisplay();
//                    savePreferences();
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tcpClient.sendTcpData();
//
//                        }
//                    }).start();
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
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tcpClient.sendTcpData();
//
//                        }
//                    }).start();
//                }
//            }
//        });

        Iv_aircon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAirPowerOn = !isAirPowerOn;
                updateAirPowerStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
//        modelswitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isCoolMode = !isCoolMode;
//                updateAirModeStatus();
//                savePreferences();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tcpClient.sendTcpData();
//
//                    }
//                }).start();
//            }
//        });
        LL_socketpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSocketPowerOn = !isSocketPowerOn;
                updateSocketPowerStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_usbpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUsbPowerOn = !isUsbPowerOn;
                updateUsbPowerStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_colorglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isColorGlassOn = !isColorGlassOn;
                updateColorGlassStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_basspower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBassPowerOn = !isBassPowerOn;
                updateBassPowerStatus();
                savePreferences();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.sendTcpData();

                    }
                }).start();
            }
        });
        LL_poweramplifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPowerAmplifierOn = !isPowerAmplifierOn;
                updatePowerAmplifierStatus();
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

    private void updatePowerAmplifierStatus() {
        if (isPowerAmplifierOn) {
            Iv_poweramplifier.setSelected(true);
            Iv_poweramplifierSwitch.setSelected(true);
        } else {
            Iv_poweramplifier.setSelected(false);
            Iv_poweramplifierSwitch.setSelected(false);
        }
    }

    private void updateBassPowerStatus() {
        if (isBassPowerOn) {
            Iv_basspower.setSelected(true);
            Iv_basspowerSwitch.setSelected(true);
        } else {
            Iv_basspower.setSelected(false);
            Iv_basspowerSwitch.setSelected(false);
        }
    }


    private void updateColorGlassStatus() {
        if (isColorGlassOn) {
            Iv_colorglass.setSelected(true);
            Iv_colorglassSwitch.setSelected(true);
        } else {
            Iv_colorglass.setSelected(false);
            Iv_colorglassSwitch.setSelected(false);
        }
    }

    private void updateUsbPowerStatus() {
        if (isUsbPowerOn) {
            Iv_usbpower.setSelected(true);
            Iv_usbpowerSwitch.setSelected(true);
        } else {
            Iv_usbpower.setSelected(false);
            Iv_usbpowerSwitch.setSelected(false);
        }
    }

    private void updateSocketPowerStatus() {
        if (isSocketPowerOn) {
            Iv_socketpower.setSelected(true);
            IV_socketpowerSwitch.setSelected(true);
        } else {
            Iv_socketpower.setSelected(false);
            IV_socketpowerSwitch.setSelected(false);
        }
    }

//    private void fetchAndUpdateHardwareData() {
//        HardwareData hardwareData = HardwareData.fetchHardwareData();
//
//        // 更新UI
//        temperatureTextView.setText(String.valueOf(hardwareData.getTemperature()));
//        if (hardwareData.isSwitchState()) {
//            Iv_switch.setSelected(true);
//            Iv_aircon.setSelected(true);
//        } else {
//            Iv_switch.setSelected(false);
//            Iv_aircon.setSelected(false);
//        }
//    }


    private void updateAirModeStatus() {
        if (isCoolMode) {
            Iv_model.setSelected(true);
            Tv_model.setText("制冷");
        } else {
            Iv_model.setSelected(false);
            Tv_model.setText("制热");
        }
    }

    private void updateAirPowerStatus() {
        if (isAirPowerOn) {
            Iv_aircon.setSelected(true);
            Iv_airSwitch.setSelected(true);
        } else {
            Iv_aircon.setSelected(false);
            Iv_airSwitch.setSelected(false);
        }
    }

    private void updateTemperatureDisplay() {
        temperatureTextView.setText(String.valueOf(temperature));
    }
    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(TEMPERATURE_KEY, temperature);
        editor.putBoolean(AIRPOWER_KEY, isAirPowerOn);
//        editor.putBoolean(MODE_KEY, isCoolMode);
        editor.putBoolean(SOCKETPOWER_KEY, isSocketPowerOn);
        editor.putBoolean(USBPOWER_KEY, isUsbPowerOn);
        editor.putBoolean(COLORGLASS_KEY, isColorGlassOn);
        editor.putBoolean(BASSPOWER_KEY, isBassPowerOn);
        editor.putBoolean(POWERAMPLIFIER_KEY, isPowerAmplifierOn);
        editor.apply();
    }
}