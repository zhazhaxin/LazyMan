package cn.hotwoo.alien.servicelife.moudel.lbs;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocalDayWeatherForecast;
import com.amap.api.location.AMapLocalWeatherLive;
import com.jude.beam.bijection.RequiresPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;

/**
 * Created by alien on 2015/9/1.
 */
@RequiresPresenter(WeatherPresenter.class)
public class WeatherActivity extends BaseActivity<WeatherPresenter> {

    @Bind(R.id.location)
    ImageView location;
    @Bind(R.id.refresh)
    ImageView refresh;
    @Bind(R.id.temp)
    TextView temp;
    @Bind(R.id.wearther)
    TextView wearther;
    @Bind(R.id.tempRange1)
    TextView tempRange1;
    @Bind(R.id.tempRange2)
    TextView tempRange2;
    @Bind(R.id.tempRange3)
    TextView tempRange3;
    @Bind(R.id.tempRange4)
    TextView tempRange4;
    @Bind(R.id.humidity)
    TextView humidity;
    @Bind(R.id.windowDir)
    TextView windowDir;
    @Bind(R.id.windowPower)
    TextView windowPower;
    @Bind(R.id.publishTime)
    TextView publishTime;
    @Bind(R.id.city)
    TextView city;
    @Bind(R.id.weatherImg1)
    ImageView weatherImg1;
    @Bind(R.id.weatherImg2)
    ImageView weatherImg2;
    @Bind(R.id.weatherImg3)
    ImageView weatherImg3;
    @Bind(R.id.weatherImg4)
    ImageView weatherImg4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lbs_weather_activity);
        ButterKnife.bind(this);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getData();
            }
        });
    }

    public void setLiveData(AMapLocalWeatherLive data) {
        city.setText(data.getCity());
        temp.setText(data.getTemperature());
        wearther.setText(data.getWeather());
        humidity.setText(data.getHumidity());
        windowDir.setText(data.getWindDir() + "风");
        windowPower.setText(data.getWindPower() + "级");
        publishTime.setText(data.getReportTime().split(" ")[1].split(":")[0] + "点");
    }

    public void setForecastData(List<AMapLocalDayWeatherForecast> forcasts) {

        for (int i = 0; i < forcasts.size(); i++) {
            AMapLocalDayWeatherForecast forcast = forcasts.get(i);
            switch (i) {
                case 0:
                    tempRange1.setText(forcast.getDayTemp() + "/" + forcast.getNightTemp());
                    getPresenter().matchWeather(forcast.getDayWeather(), weatherImg1);
                    break;
                case 1:
                    tempRange2.setText(forcast.getDayTemp() + "/" + forcast.getNightTemp());
                    getPresenter().matchWeather(forcast.getDayWeather(), weatherImg2);
                    break;
                case 2:
                    tempRange3.setText(forcast.getDayTemp() + "/" + forcast.getNightTemp());
                    getPresenter().matchWeather(forcast.getDayWeather(), weatherImg3);
                    break;
                case 3:
                    tempRange4.setText(forcast.getDayTemp() + "/" + forcast.getNightTemp());
                    getPresenter().matchWeather(forcast.getDayWeather(), weatherImg4);
                    break;
            }
        }
        dismissProgress();
    }


}
