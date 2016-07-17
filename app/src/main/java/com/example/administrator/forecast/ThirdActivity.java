package com.example.administrator.forecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import Utility.HttpCallBackListener;
import Utility.HttpUtil;
import db.CoolWeatherDB;
import db.SdUtils;
import model.Countryweather;

public class ThirdActivity extends AppCompatActivity {

    private CoolWeatherDB coolWeatherDB;
    private Countryweather countryweather=new Countryweather();
    private TextView tv;
    final String address="http://apis.baidu.com/apistore/weatherservice/cityname?cityname=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        final String getprovince = intent.getStringExtra("extra_data");
        tv= (TextView) findViewById(R.id.text);

        // tv.setText(getprovince);
        coolWeatherDB = CoolWeatherDB.getInstance(this);
        HttpUtil.sendHttpRequest(address, getprovince, new HttpCallBackListener() {
            @Override
            public void onFinish(String response) {

                countryweather = HttpUtil.handleWeatherResponse(response);
                coolWeatherDB.saveWeather(countryweather);
                boolean x = SdUtils.pathSaveSD("/data/data/com.example.administrator.forecast/databases/cool_weather", "weatherforecast.db", "xyq");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(countryweather.getDate());
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                countryweather = coolWeatherDB.show(getprovince);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(countryweather.getDate());
                    }
                });
            }
        });





    }
}
