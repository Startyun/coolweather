package android.coolweather.com.coolweather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient =new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mapView=(MapView)findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);//将“显示当前自己位置”功能开启
        positionText=(TextView)findViewById(R.id.position_text_view);
        List<String>permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
    }
        if(ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()) {
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this,permissions,1);
        }else {
            requestLocation();
        }
    }


    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);//缩放级别
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }



     private void requestLocation() {
        initLocation();
        mLocationClient.start();
     }

     private void initLocation() {
         LocationClientOption option=new LocationClientOption();
         option.setScanSpan(1);   //每5秒钟刷新一次
         option.setIsNeedAddress(true);   //获取当前详细的地址信息
         //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//指定为GPS模式
         mLocationClient.setLocOption(option);
     }


     @Override
     protected void onResume() {
        super.onResume();
        mapView.onResume();
     }

     @Override
     protected void onPause() {
        super.onPause();
        mapView.onPause();
     }


     @Override
     protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
     }

     @Override
    public void onRequestPermissionsResult(int requestCode,String [] permissions,int[]grantResults) {
        switch (requestCode) {
            case 1:
                if(grantResults.length>0) {
                    for(int result : grantResults) {
                        if(result !=PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
     }


     public class MyLocationListener implements BDLocationListener {
        @Override
         public void onReceiveLocation(final BDLocation location) {
            if(location.getLocType()==BDLocation.TypeGpsLocation
                    ||location.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                        StringBuilder currentPosition= new StringBuilder();
                        currentPosition.append("维度：").append(location.getLatitude()).append("\n");
                        currentPosition.append("经线：").append(location.getLongitude()).append("\n");
                        currentPosition.append("国家:").append(location.getCountry()).append("\n");
                        currentPosition.append("省:").append(location.getProvince()).append("\n");
                        currentPosition.append("市:").append(location.getCity()).append("\n");
                        currentPosition.append("区:").append(location.getDistrict()).append("\n");
                        currentPosition.append("街道:").append(location.getStreet()).append("\n");
                        currentPosition.append("定位方式:");
                        if(location.getLocType() == BDLocation.TypeGpsLocation) {
                            currentPosition.append("GPS");
                        }else if(location.getLocType()==BDLocation.TypeNetWorkLocation) {
                            currentPosition.append("网络");
                        }
                        positionText.setText(currentPosition);
                    }
            });
        }

//         //@Override
//         public void onConnectHotSpotMessage(String s,int i) {
//
//        }

     }


}
