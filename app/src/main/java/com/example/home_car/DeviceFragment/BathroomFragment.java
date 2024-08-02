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


public class BathroomFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout LL_hotwind,LL_closetool,LL_extractorfan;
    private ImageView Iv_hotwind,Iv_closetool,Iv_extractorfan;

    private boolean isHotwindOn;
    private boolean isClosetoolOn;
    private boolean isExtractorfanOn;

    private TcpClient tcpClient;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HOME_CAR_DATA";
    private static final String HOTWIND_KEY = "hotwind_status";
    private static final String CLOSETOOL_KEY = "closetool_status";
    private static final String EXTRACTORFAN_KEY = "extractorfan_status";

    public BathroomFragment() {
        // Required empty public constructor
    }


    public static BathroomFragment newInstance(String param1, String param2) {
        BathroomFragment fragment = new BathroomFragment();
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
        View view = inflater.inflate(R.layout.fragment_bathroom, container, false);

        Iv_closetool=view.findViewById(R.id.iv_closetool);
        Iv_hotwind=view.findViewById(R.id.iv_hotwind);
        Iv_extractorfan=view.findViewById(R.id.iv_extractorfan);

        LL_closetool=view.findViewById(R.id.ll_closetool);
        LL_hotwind=view.findViewById(R.id.ll_hotwind);
        LL_extractorfan=view.findViewById(R.id.ll_extractorfan);



        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        isClosetoolOn = sharedPreferences.getBoolean(CLOSETOOL_KEY, false);
        isExtractorfanOn = sharedPreferences.getBoolean(EXTRACTORFAN_KEY, false);
        isHotwindOn = sharedPreferences.getBoolean(HOTWIND_KEY, false);

        tcpClient=new TcpClient(getContext());

        updateHotwindStatus();
        updateClosetoolStatus();
        updateExtractorfanStatus();

//        fetchAndUpdateHardwareData();//从硬件获取数据更新UI


        LL_hotwind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHotwindOn = !isHotwindOn;
                updateHotwindStatus();
                savePreferences();

            }
        });
        LL_closetool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClosetoolOn = !isClosetoolOn;
                updateClosetoolStatus();
                savePreferences();

            }
        });
        LL_extractorfan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExtractorfanOn = !isExtractorfanOn;
                updateExtractorfanStatus();
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

    private void updateHotwindStatus() {
        if (isHotwindOn) {
            Iv_hotwind.setSelected(true);
        } else {
            Iv_hotwind.setSelected(false);
        }
    }


    private void updateExtractorfanStatus() {
        if (isExtractorfanOn) {
            Iv_extractorfan.setSelected(true);
        } else {
            Iv_extractorfan.setSelected(false);
        }
    }

    private void updateClosetoolStatus() {
        if (isClosetoolOn) {
            Iv_closetool.setSelected(true);
        } else {
            Iv_closetool.setSelected(false);
        }
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(HOTWIND_KEY, isHotwindOn);
        editor.putBoolean(CLOSETOOL_KEY, isClosetoolOn);
        editor.putBoolean(EXTRACTORFAN_KEY, isExtractorfanOn);
        editor.apply();
    }
}