package com.example.linzhizhou.petchase_12_3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.NaviLatLng;
import com.tckj.zyfsdk.ZYFSdk;
import com.tckj.zyfsdk.entity.BaseEntity;
import com.tckj.zyfsdk.entity.DeviceDetailsEntity;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFBindDeviceListener;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFGetBindDeviceListener;

import java.util.List;


public class RideRouteCalculateActivity extends BaseActivity {

    private Location location;
    private String locationProvider;
    private LocationManager locationManager;
    private double jd;
    private double wd;
    private double deviceJd;
    private double deviceWd;
    private Context context;
    //private  GetBindDeviceLocation getDeviceLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_navi);
        context = getApplicationContext();
        mAMapNaviView = findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
        //getDeviceLocation=new GetBindDeviceLocation();
    }

    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        requestPermission();
        getLocationInfo();
        NaviLatLng tempStartPoint=new NaviLatLng(wd,jd);

        ZYFSdk.getInstance().getBindDeviceDetails(context, "001221A003E6", new ZYFGetBindDeviceListener() {
            @Override
            public void onComplete(DeviceDetailsEntity deviceDetailsEntity) {
                deviceJd=Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(8).getFieldValue().toString());
                deviceWd=Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(9).getFieldValue().toString());
            }

            @Override
            public void onError(Exception e) {
//                int a;
//                deviceJd=1;
//                deviceWd=1;
            }
        });
        //double b=getDeviceLocation.getDeviceJd();
        Intent getJWd=getIntent();
        String getJd=getJWd.getStringExtra("passjd");
        String getWd=getJWd.getStringExtra("passwd");
        deviceJd=Double.parseDouble(getJd);
        deviceWd=Double.parseDouble(getWd);
        NaviLatLng tempEndPoint=new NaviLatLng(deviceWd,deviceJd);
        mAMapNavi.calculateRideRoute(tempStartPoint,tempEndPoint);
    }

    @Override
    public void onCalculateRouteSuccess(int[] ids) {
        super.onCalculateRouteSuccess(ids);
        mAMapNavi.startNavi(NaviType.GPS);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "拒绝将无法进行宠物追踪哦", Toast.LENGTH_SHORT);
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

//    ZYFGetBindDeviceListener zyfGetBindDeviceListener(){
//
//    }
    private void getLocationInfo() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            // 如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        }
//    else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//       // 如果是Network
//       locationProvider = LocationManager.NETWORK_PROVIDER;
//    }
        else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        // 获取Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            // 不为空,显示地理位置经纬度
            jd = location.getLongitude();
            wd = location.getLatitude();
        } else {
            Toast.makeText(this, "GPS未定位到位置", Toast.LENGTH_SHORT);
            System.out.println("GPS未定位到位置,请查看是否打开了GPS ？");
        }
        // 监视地理位置变化
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationProvider, 2000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //如果地点发生了改变，则重新计算路径，以获得实时定位导航的效果
//                getLocationInfo();
//                NaviLatLng tempStartPoint=new NaviLatLng(wd,jd);
//                ZYFSdk.getInstance().getBindDeviceDetails(context, "001221A003E6", new ZYFGetBindDeviceListener() {
//                    @Override
//                    public void onComplete(DeviceDetailsEntity deviceDetailsEntity) {
//                        deviceJd=Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(8).getFieldValue().toString());
//                        deviceWd=Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(9).getFieldValue().toString());
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });
//
//                NaviLatLng tempEndPoint=new NaviLatLng(deviceWd,deviceJd);
//                mAMapNavi.calculateRideRoute(tempStartPoint,tempEndPoint);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }


//    class GetBindDeviceLocation implements ZYFGetBindDeviceListener{
//
//        private double deviceJd;
//        private double deviceWd;
//        public double getDeviceJd(){
//            return deviceJd;
//        }
//        public double getDeviceWd(){
//            return deviceWd;
//        }
//        @Override
//        public void onComplete(DeviceDetailsEntity deviceDetailsEntity) {
//            deviceJd=Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(8).getFieldValue().toString());
//            deviceWd=Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(9).getFieldValue().toString());
//        }
//
//        @Override
//        public void onError(Exception e) {
//            deviceJd=116.435765;   //若初始化失败，则把目标点设在北京
//            deviceWd=39.925846;
//        }
//    }
}
