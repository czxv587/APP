package com.example.home_car.DeviceFragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_car.R;

public class KitchenFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView temperatureTextView,Tv_model;
    private LinearLayout LL_inducooker,LL_;
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


        return view;
    }
}