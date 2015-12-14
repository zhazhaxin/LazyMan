package cn.hotwoo.alien.servicelife.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/18.
 */
public class MapLocation implements Serializable {

    private double latitude;
    private double longitude;

    public MapLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
