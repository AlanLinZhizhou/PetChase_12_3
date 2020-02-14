package com.example.linzhizhou.petchase_12_3.navigate;

import android.os.Bundle;

import com.amap.api.navi.AMapNaviView;
import com.example.linzhizhou.petchase_12_3.R;


public class BasicWalkNaviActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_navi);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
    }


    @Override
    public void onInitNaviSuccess() {
        mAMapNavi.calculateWalkRoute(sList.get(0), eList.get(0));

    }

//    @Override
//    public void onCalculateRouteSuccess(int[] ints) {
//        super.onCalculateRouteSuccess(ints);
//        //mAMapNavi.startNavi(NaviType.CRUISE);
//    }
}
