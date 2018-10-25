package android.coolweather.com.coolweather.menu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.coolweather.com.coolweather.R;
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
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.ArrayList;
import java.util.List;

public class ClickActivity extends AppCompatActivity implements
        OnGetGeoCoderResultListener {

    private BitmapDescriptor bitmap;
    public LocationClient mLocationClient;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    GeoCoder mSearch = null;
    private TextView tet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_click);
        mapView = (MapView) findViewById(R.id.bmapView);
        tet=(TextView)findViewById(R.id.searchKey);
        baiduMap = mapView.getMap();
        //中心点模块初始化，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        mapView.removeViewAt(1);
        baiduMap.setMyLocationEnabled(true);//将“显示当前自己位置”功能开启
        final List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(ClickActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(ClickActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(ClickActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(ClickActivity.this, permissions, 1);
        } else {
            requestLocation();
        }



        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener(){

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                return false;
            }

            @Override
            public void onMapClick(LatLng latLng){

                double latitude = latLng.latitude;
                double longitude = latLng.longitude;
                baiduMap.clear();
                LatLng point = new LatLng(latitude, longitude);
                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_31);
                MarkerOptions options = new MarkerOptions().position(point).icon(bitmap);
                baiduMap.addOverlay(options);
                ReverseGeoCodeOption op = new ReverseGeoCodeOption();
                op.location(latLng);
                mSearch.reverseGeoCode(op);
            }
        });

//        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(MarkerActivity marker) {
//                TextView tet3;
//                int index = marker.getZIndex();
//                tet3 = new TextView(getApplicationContext());
//                tet3.setBackgroundResource(R.drawable.ic_launcher_22);
//                tet3.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
//                tet3.setTextSize(12);
//                tet3.setText("地址");
//
//                InfoWindow mInfoWindow;
//
//               LatLng ll = marker.getPosition();//获取经纬度
//                Point p = baiduMap.getProjection().toScreenLocation(ll);
//                p.y -= 47;
//                LatLng llInfo = baiduMap.getProjection().fromScreenLocation(p);
//
//
//                Toast.makeText(ClickActivity.this,llInfo.toString(), Toast.LENGTH_SHORT).show();
//
//
//                mInfoWindow = new InfoWindow(tet3, llInfo , -47);
//
//
//                //baiduMap.showInfoWindow(mInfoWindow);
//                return true;
//
//            }
//        });

    }



    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {

            MapStatusUpdate update = null;
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


            update = MapStatusUpdateFactory.zoomTo(16f);//缩放
            baiduMap.animateMapStatus(update);

            update = MapStatusUpdateFactory.newLatLng(latLng);//定位
            baiduMap.animateMapStatus(update);

            //只能先缩放后定位，否则可能出现北京的地图

            isFirstLocate = false;


            /*更改系统图标*/
            MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_30);
            baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, false, mCurrentMarker));

        }

        /*让‘自己’在地图上显示*/
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);


    }


    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);   //每5秒钟刷新一次
        option.setCoorType("bd09ll");
        option.setIsNeedLocationPoiList(true);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }

        }
    }



    /*正向地理编码*/
    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {

    }



    /*反向地理编码*/
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(ClickActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
            return;
        }
//        baiduMap.clear();
//        baiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
//                .icon(BitmapDescriptorFactory.
//                        fromResource(R.mipmap.ic_launcher_30)));

        // baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result.getLocation()));


        Toast.makeText(ClickActivity.this, result.getAddress()+result.getSematicDescription(), Toast.LENGTH_LONG).show();
    }


}