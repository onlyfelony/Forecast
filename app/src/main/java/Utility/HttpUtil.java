package Utility;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import model.Countryweather;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HttpUtil {
    //通过接口获取信息
    public static void sendHttpRequest(final String address,final String mess,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    String cityname = URLEncoder.encode(mess, "UTF-8");
                    URL url=new URL(address+cityname);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("apikey", "02912e7aeb058df39e053d930e9c9c92");
                    // connection.setConnectTimeout(8000);
                    // connection.setReadTimeout(8000);
                    connection.connect();

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in,"UTF-8"));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
// 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e) {

                    if (listener != null) {
// 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }
    //解析json数据
    public static Countryweather handleWeatherResponse(String response){
        Countryweather con=new Countryweather();
        try {

            JSONObject jsonObject = new JSONObject(response);
            String retData=jsonObject.getString("retData");
            JSONObject jsonObject1=new JSONObject(retData);
            String city=jsonObject1.getString("city");
            String date=jsonObject1.getString("date");
            String time=jsonObject1.getString("time");
            String weather=jsonObject1.getString("weather");
            String temp=jsonObject1.getString("temp");
            String l_tmp=jsonObject1.getString("l_tmp");
            String h_tmp=jsonObject1.getString("h_tmp");
            String WD=jsonObject1.getString("WD");
            String WS=jsonObject1.getString("WS");
            String sunrise=jsonObject1.getString("sunrise");
            String sunset=jsonObject1.getString("sunset");
            con=new Countryweather(city,date,time,weather,temp,l_tmp,h_tmp,WD,WS,sunrise,sunset);

        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
