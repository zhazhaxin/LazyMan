package cn.hotwoo.alien.servicelife.moudel.lbs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.amap.api.location.AMapLocalDayWeatherForecast;
import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;

import java.util.List;

import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/9/1.
 */
public class WeatherPresenter extends BasePresenter<WeatherActivity> implements AMapLocalWeatherListener {

    private LocationManagerProxy mLocationManagerProxy;

    @Override
    protected void onCreate(WeatherActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        getData();
    }

    public void getData(){
        getView().showProgress();
        mLocationManagerProxy = LocationManagerProxy.getInstance(getView());
        mLocationManagerProxy.requestWeatherUpdates(
                LocationManagerProxy.WEATHER_TYPE_LIVE, this);
        mLocationManagerProxy.requestWeatherUpdates(
                LocationManagerProxy.WEATHER_TYPE_FORECAST, this);//WEATHER_TYPE_FORECAST天气预报模式
    }

    @Override
    public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
        if(aMapLocalWeatherLive!=null && aMapLocalWeatherLive.getAMapException().getErrorCode() == 0){
            getView().setLiveData(aMapLocalWeatherLive);
        }else{
            Utils.Toast("获取数据失败");
            getView().dismissProgress();
        }
    }

    @Override
    public void onWeatherForecaseSearched(AMapLocalWeatherForecast aMapLocalWeatherForecast) {
        if(aMapLocalWeatherForecast != null && aMapLocalWeatherForecast.getAMapException().getErrorCode() == 0){
            List<AMapLocalDayWeatherForecast> forcasts = aMapLocalWeatherForecast.getWeatherForecast();
            getView().setForecastData(forcasts);
        }else{
            Utils.Toast("获取数据失败");
            getView().dismissProgress();
        }
    }

    public void matchWeather(String weather,ImageView weatherImg) {
        if(weather.equals("多云")) {
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_cloudy));
        }else if (weather.startsWith("雷阵雨")) {
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_thundershower));
        }else if(weather.matches("雨")){
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_rain));
        }else if ( weather.matches("雪")) {
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_snow));
        }else if (weather.matches("雾")){
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_foggy));
        }else if (weather.matches("阴")){
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_overcast));
        }else if (weather.matches("晴")){
            weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_fine));
        }else weatherImg.setImageDrawable(getResourceDrawable(R.drawable.ic_weather_overcast));
    }

    public Drawable getResourceDrawable(int id){
        return getView().getResources().getDrawable(id);
    }
}
