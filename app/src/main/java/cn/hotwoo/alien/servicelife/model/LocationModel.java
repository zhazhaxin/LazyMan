package cn.hotwoo.alien.servicelife.model;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import cn.hotwoo.alien.servicelife.model.bean.MapLocation;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/9/1.
 */
public class LocationModel extends AbsModel implements AMapLocationListener {

    private static final String LOCATION = "location";
    private static final String WEATHER_FORECAST="weatherForcast";
    private static final String WEATHER_LIVE="weatherLive";
    private LocationManagerProxy mLocationManagerProxy;
    private Context mContext;

    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
        mContext = context;
        init();
    }

    public static LocationModel getInstance() {
        return getInstance(LocationModel.class);
    }
    private void init() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(mContext);
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 100, this);
        mLocationManagerProxy.setGpsEnable(false);
    }

    public void saveLocationToFile(MapLocation data) {
        Utils.writeObjectToFile(data, FileManager.getInstance().getChild(FileManager.Dir.OBJECT, LOCATION));
    }

    public MapLocation getLocationFromFile() {
        return (MapLocation) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.OBJECT, LOCATION));
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
            saveLocationToFile(new MapLocation(amapLocation.getLatitude(),amapLocation.getLongitude()));
        }
    }

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
}
