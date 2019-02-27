package com.example.linzhizhou.petchase_12_3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
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
    private LocationManager locationManager;
    private Location location;
    private String locationProvider;

    private String passjd;
    private String passwd;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private Button getDataBtn;
    private ImageButton btn_cycling;
    private ImageButton car_navi;
    private ImageButton walk_navi;
    private double jd;   //手机的经纬度
    private double wd;
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_navigate, null);
        //locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mapView = view.findViewById(R.id.fragment_map);
        mapView.onCreate(savedInstanceState);
        init();
        //给手机位置标点
        requestPermission();
        getLocationInfo();
        LatLng latLng = new LatLng(wd, jd);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        Marker marker = aMap.addMarker(markerOptions);
        //给宠物位置标点
        ZYFSdk.getInstance().getBindDeviceDetails(context, "001221A003E6", new ZYFGetBindDeviceListener() {
            @Override
            public void onComplete(DeviceDetailsEntity deviceDetailsEntity) {
                // List a=new ArrayList();
                passjd=deviceDetailsEntity.getRtData().getRecords().get(8).getFieldValue().toString();
                passwd=deviceDetailsEntity.getRtData().getRecords().get(9).getFieldValue().toString();
                double dLnt=Double.parseDouble(passjd);
                double dLat=Double.parseDouble(passwd);
                //double dLnt = Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(8).getFieldValue().toString());//经度
                //double dLat = Double.parseDouble(deviceDetailsEntity.getRtData().getRecords().get(9).getFieldValue().toString());//经度
                LatLng latLng = new LatLng(dLat, dLnt);
                //final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("宠物").snippet("Destination"));
                CameraUpdate cu = CameraUpdateFactory.changeLatLng(latLng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                //设置自定义图标
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.head_icon2));
                Marker marker = aMap.addMarker(markerOptions);
                aMap.moveCamera(cu);
            }

            @Override
            public void onError(Exception e) {
                //获取失败
                //  info.setText("获取失败");
                Toast.makeText(getActivity(), "宠物经纬度获取失败，请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });

        //标点
        //LatLng latLng = new LatLng(39.906901,116.397972);
        //final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));

        ToggleButton toggleButton = view.findViewById(R.id.tb);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                } else {
                    aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                }
            }
        });
        car_navi = view.findViewById(R.id.drive_navi);
        car_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomCarActivity.class);
                startActivity(intent);
            }
        });
        btn_cycling = view.findViewById(R.id.btn_cycling);
        btn_cycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RideRouteCalculateActivity.class);
                intent.putExtra("passjd",passjd);
                intent.putExtra("passwd",passwd);
                startActivity(intent);
            }
        });
        walk_navi = view.findViewById(R.id.walt_navi);
        walk_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BasicWalkNaviActivity.class);
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

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

//    private void updatePosition(Location location) {
//    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(getActivity(), "拒绝将无法进行宠物追踪哦", Toast.LENGTH_SHORT);
            }
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

    private void getLocationInfo() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            // 如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        }
    else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
       // 如果是Network
       locationProvider = LocationManager.NETWORK_PROVIDER;
    }
        else {
            Toast.makeText(getActivity(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        // 获取Location
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            jd =location.getLongitude();
            wd =location.getLatitude();
        } else {
           // ToastUtils.showToast(this, "GPS未定位到位置");
            Toast.makeText(getActivity(),"GPS未定位到位置",Toast.LENGTH_SHORT);
            System.out.println("GPS未定位到位置,请查看是否打开了GPS ？");
        }
        // 监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 2000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

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
}
