package cn.hotwoo.alien.servicelife.moudel.lbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.jude.beam.bijection.RequiresPresenter;

import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.LocationModel;
import cn.hotwoo.alien.servicelife.util.AMapUtil;

/**
 * Created by alien on 2015/9/1.
 */
@RequiresPresenter(WalkPresenter.class)
public class WalkActivity extends BaseActivity<WalkPresenter> implements RouteSearch.OnRouteSearchListener,
        AMap.OnMapClickListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener {

    public static final int SEARCH_REQUEST_CODE = 520;

    private TextView nearby;
    private TextView navigation;

    private MapView mapView;
    public static AMap aMap;
    private Marker targetMk;
    private LatLonPoint endPoint = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lbs_activity_walk);

        nearby = (TextView) findViewById(R.id.nearby);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);// 必须要写

        initLocation();

        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WalkActivity.this, SearchSourceActivity.class), SEARCH_REQUEST_CODE);
            }
        });

    }

    //定位
    private void initLocation() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        if (LocationModel.getInstance().getLocationFromFile() != null) {
            aMap.setPointToCenter((int) LocationModel.getInstance().getLocationFromFile().getLatitude(),
                    (int) LocationModel.getInstance().getLocationFromFile().getLongitude());
        }
        setUpMap();
        //设置点击监听
        aMap.setOnMapClickListener(this);
        aMap.setOnMarkerClickListener(this);
        aMap.setOnInfoWindowClickListener(this);

    }

    private void setUpMap() {
        showProgress();
        aMap.setLocationSource(getPresenter());// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式：定位（AMap.LOCATION_TYPE_LOCATE）、跟随（AMap.LOCATION_TYPE_MAP_FOLLOW）
        // 地图根据面向方向旋转（AMap.LOCATION_TYPE_MAP_ROTATE）三种模式
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);  //设置定位模式
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_REQUEST_CODE && resultCode == SearchSourceActivity.SEARCH_RESULT_CODE) {
            getPresenter().nearSearch(data.getStringExtra(SearchSourceActivity.SEARCH_CONTENT));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lbs_walk, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                break;
            case R.id.satellite:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.night:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onMapClick(LatLng latLng) {
        targetMk = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 1)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_nearby)).position(latLng)
                .title("点击选择为目的地"));
        targetMk.showInfoWindow();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
        } else {
            marker.showInfoWindow();
        }
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(targetMk)) {
//            endTextView.setText("地图上的终点");
            endPoint = AMapUtil.convertToLatLonPoint(targetMk.getPosition());
            targetMk.hideInfoWindow();
            targetMk.remove();
        }
    }
}
