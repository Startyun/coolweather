package android.coolweather.com.coolweather.PoiList;

import com.baidu.mapapi.model.LatLng;

public class poibean {

    private String name;
    private String uid;
    private String address;
    private String province;
    private String city;
    private String area;
    private String street_id;
    private String phoneNum;
    private LatLng location;


    public poibean(String name, String uid, String address, String province, String city, String area, String street_id,
                   String phoneNum, LatLng location){
        this.name=name;
        this.uid=uid;
        this.address=address;
        this.province=province;
        this.city=city;
        this.area=area;
        this.street_id=street_id;
        this.phoneNum=phoneNum;
        this.location=location;
    }


    public LatLng getLocation() {
        return location;
    }

    public String getPhoneNum() {

        return phoneNum;
    }

    public String getStreet_id() {

        return street_id;
    }

    public String getArea() {

        return area;
    }

    public String getCity() {

        return city;
    }

    public String getProvince() {

        return province;
    }

    public String getAddress() {

        return address;
    }

    public String getUid() {

        return uid;
    }

    public String getName() {

        return name;
    }
}
