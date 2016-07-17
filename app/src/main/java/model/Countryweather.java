package model;

/**
 * Created by Administrator on 2016/7/16.
 */
public class Countryweather {
    private String city;//城市
    private String date;//日期
    private String time;//发布时间
    private String weather;//天气情况
    private String temp;//气温
    private String l_tmp;//最低气温
    private String h_tmp;//最高气温
    private String WD;//风向
    private String WS;//风力
    private String sunrise;//日出时间
    private String sunset;//日落时间

    public Countryweather(){}
    public Countryweather(String city, String date, String time, String weather, String temp, String l_tmp, String h_tmp, String wd, String ws, String sunrise, String sunset) {
        this.city = city;
        this.date = date;
        this.time = time;
        this.weather = weather;
        this.temp = temp;
        this.l_tmp = l_tmp;
        this.h_tmp = h_tmp;
        WD = wd;
        WS = ws;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getH_tmp() {
        return h_tmp;
    }

    public String getL_tmp() {
        return l_tmp;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTemp() {
        return temp;
    }

    public String getTime() {
        return time;
    }

    public String getWD() {
        return WD;
    }

    public String getWeather() {
        return weather;
    }

    public String getWS() {
        return WS;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setH_tmp(String h_tmp) {
        this.h_tmp = h_tmp;
    }

    public void setL_tmp(String l_tmp) {
        this.l_tmp = l_tmp;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }
}
