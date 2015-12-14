package cn.hotwoo.alien.servicelife.moudel.lbs;

import android.location.Location;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

import java.util.List;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.LocationModel;
import cn.hotwoo.alien.servicelife.model.bean.MapLocation;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/9/1.
 */
public class WalkPresenter extends BasePresenter<WalkActivity> implements AMapLocationListener, LocationSource, RouteSearch.OnRouteSearchListener {

    private PoiSearch.Query query;
    private static Double latitude = null;
    private static Double longitude = null;
    //定位
    private LocationManagerProxy mLocationManagerProxy;
    private LocationSource.OnLocationChangedListener mListener;

    private RouteSearch routeSearch;

    @Override
    protected void onCreate(WalkActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        init();
    }

    @Override
    protected void onCreateView(WalkActivity view) {
        super.onCreateView(view);
        routeSearch = new RouteSearch(getView());
        routeSearch.setRouteSearchListener(this);
    }

    public void init() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(getView());
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);
        mLocationManagerProxy.setGpsEnable(false);
    }

    public void routeQuery(LatLonPoint endPoint) {
        LatLonPoint startPoint = new LatLonPoint(latitude, longitude);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
        RouteSearch.WalkRouteQuery routeQuery = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WalkMultipath);
        routeSearch.calculateWalkRouteAsyn(routeQuery);
    }

    public void nearSearch(String keyWord) {
        getView().showProgress();
        query = new PoiSearch.Query(keyWord, "", "");//titycode
        // keyWord表示搜索字符串，第二个参数表示POI搜索类型，默认为：生活服务、餐饮服务、商务住宅
        //cityCode表示POI搜索区域，（这里可以传空字符串，空字符串代表全国在全国范围内进行搜索）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查第一页
        PoiSearch poiSearch = new PoiSearch(getView(), query);
        if (latitude != null && longitude != null)
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,
                    longitude), 2000));//设置周边搜索的中心点以及区域
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int code) {
                //result.getPois()可以获取到PoiItem列表，Poi详细信息可参考PoiItem类
                //若当前城市查询不到所需Poi信息，可以通过result.getSearchSuggestionCitys()获取当前Poi搜索的建议城市
                //如果搜索关键字明显为误输入，则可通过result.getSearchSuggestionKeywords()方法得到搜索关键词建议
                //返回结果成功或者失败的响应码。0为成功，其他为失败（详细信息参见网站开发指南-错误码对照表）
                if (code == 0) {
                    if (poiResult != null && poiResult.getQuery() != null) {
                        if (poiResult.getQuery().equals(query)) {
                            // 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                            List<SuggestionCity> suggestionCities = poiResult.getSearchSuggestionCitys();
                            List<PoiItem> data = poiResult.getPois();
                            if (data != null && data.size() > 0) {
                                getView().aMap.clear();
                                PoiOverlay poiOverlay = new PoiOverlay(getView().aMap, data);
                                poiOverlay.removeFromMap();
                                poiOverlay.addToMap();
                                poiOverlay.zoomToSpan();
                            } else if (suggestionCities != null && suggestionCities.size() > 0) {
                                Utils.Toast(suggestionCities.toString());
                            } else {
                                Utils.Toast("搜索失败");
                            }
                        }
                    }
                }
                getView().dismissProgress();
            }

            @Override
            public void onPoiItemDetailSearched(PoiItemDetail poiItemDetail, int i) {
            }
        });
        poiSearch.searchPOIAsyn();//开始搜索
    }

    //不管定位是否成功，都会回调该方法
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
            LocationModel.getInstance().saveLocationToFile(new MapLocation(amapLocation.getLatitude(), amapLocation.getLongitude()));
            //获取位置信息
            latitude = amapLocation.getLatitude();
            longitude = amapLocation.getLongitude();

            mListener.onLocationChanged(amapLocation);  // 显示系统小蓝点
            if (amapLocation.getProvider().equals(LocationManagerProxy.NETWORK_PROVIDER)) {
                Bundle locBundle = amapLocation.getExtras();
                if (locBundle != null) {
                    String desc = locBundle.getString("desc");
                    Utils.Log(desc);
                }
            }
            getView().dismissProgress();
            return;
        }
        Utils.Toast("定位失败");
        getView().dismissProgress();
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

    //LocationSource的方法
    //激活定位
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mLocationManagerProxy == null) {
            mLocationManagerProxy = LocationManagerProxy.getInstance(getView());
            /**
             * 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
             注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
             在定位结束后，在合适的生命周期调用destroy()方法
             其中如果间隔时间为-1，则定位只定一次
             */
            mLocationManagerProxy.requestLocationData(
                    LocationProviderProxy.AMapNetwork, 60 * 1000, 100, this);
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(this);
            mLocationManagerProxy.destroy();
        }
        mLocationManagerProxy = null;
    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }
}
