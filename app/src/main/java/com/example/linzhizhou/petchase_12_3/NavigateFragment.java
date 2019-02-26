package com.example.linzhizhou.petchase_12_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.iflytek.cloud.thirdparty.B;
import com.tckj.zyfsdk.ZYFSdk;
import com.tckj.zyfsdk.entity.DeviceDetailsEntity;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFGetBindDeviceListener;

import java.util.ArrayList;
import java.util.List;

public class NavigateFragment extends Fragment {
    Context context;
    private MapView mapView;
    private AMap aMap;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    private Button getDataBtn;
    private ImageButton btn_cycling;
    private ImageButton car_navi;
    private ImageButton walk_navi;
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(this.getView()!=null){
            this.getView().setVisibility(menuVisible?View.VISIBLE:View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_navigate,null);
        mapView=view.findViewById(R.id.fragment_map);
        mapView.onCreate(savedInstanceState);
        init();
        ToggleButton toggleButton=view.findViewById(R.id.tb);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }
                else {
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                }
            }
        });
        car_navi=view.findViewById(R.id.drive_navi);
        car_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CustomCarActivity.class);
                startActivity(intent);
            }
        });
        btn_cycling=view.findViewById(R.id.btn_cycling);
        btn_cycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RideRouteCalculateActivity.class);
                startActivity(intent);
            }
        });
        walk_navi=view.findViewById(R.id.walt_navi);
        walk_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BasicWalkNaviActivity.class);
                startActivity(intent);
            }
        });
        //getDataBtn=view.findViewById(R.id.getDataButton);
       // final TextView info=view.findViewById(R.id.info);
//        getDataBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                ZYFSdk.getInstance().getBindDeviceDetails(context, "001221A003E6", new ZYFGetBindDeviceListener() {
//                    @Override
//                    public void onComplete(DeviceDetailsEntity deviceDetailsEntity) {
//                       // List a=new ArrayList();
//                      //  info.setText(deviceDetailsEntity.getRtData().getRecords().get(8).getFieldValue().toString());//经度
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        //获取失败
//                      //  info.setText("获取失败");
//                    }
//                });
//            }
//        });
        return view;
    }

    private void init(){
        if(aMap==null){
            aMap=mapView.getMap();
        }
    }
}
