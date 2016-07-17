package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.Countryweather;

/**
 * Created by Administrator on 2016/7/16.
 */
public class CoolWeatherDB {

    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    private CoolWeatherDB(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    //将countryweather的实例存到数据库中,如果之前存在就更新
    public void saveWeather(Countryweather countryweather){
        if(countryweather!=null) {
            Cursor cursor = db.query("weather", null, "city=?", new String[]{countryweather.getCity()}, null, null, null);
            if (cursor.moveToFirst()) {
                ContentValues values=new ContentValues();
                values.put("date", countryweather.getDate());
                values.put("time", countryweather.getTime());
                values.put("weather", countryweather.getWeather());
                values.put("temp", countryweather.getTemp());
                values.put("l_tmp", countryweather.getL_tmp());
                values.put("h_tmp", countryweather.getH_tmp());
                values.put("WD", countryweather.getWD());
                values.put("WS", countryweather.getWS());
                values.put("sunrise", countryweather.getSunrise());
                values.put("sunset", countryweather.getSunset());
                db.update("weather",values,"city=?",new String[]{countryweather.getCity()});


            } else {
                ContentValues values = new ContentValues();
                values.put("city", countryweather.getCity());
                values.put("date", countryweather.getDate());
                values.put("time", countryweather.getTime());
                values.put("weather", countryweather.getWeather());
                values.put("temp", countryweather.getTemp());
                values.put("l_tmp", countryweather.getL_tmp());
                values.put("h_tmp", countryweather.getH_tmp());
                values.put("WD", countryweather.getWD());
                values.put("WS", countryweather.getWS());
                values.put("sunrise", countryweather.getSunrise());
                values.put("sunset", countryweather.getSunset());
                db.insert("weather", null, values);
            }

        }
    }
    //将数据库中的数据取出来
    public Countryweather show(String county){
        Cursor cursor=db.query("weather",null,"city=?",new String[]{county},null,null,null);
        Countryweather countryweather=new Countryweather();
        if (cursor.moveToFirst()){
            do{
                countryweather.setCity(cursor.getString(cursor.getColumnIndex("city")));
                countryweather.setDate(cursor.getString(cursor.getColumnIndex("date")));
                countryweather.setTime(cursor.getString(cursor.getColumnIndex("time")));
                countryweather.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
                countryweather.setTemp(cursor.getString(cursor.getColumnIndex("temp")));
                countryweather.setL_tmp(cursor.getString(cursor.getColumnIndex("l_tmp")));
                countryweather.setH_tmp(cursor.getString(cursor.getColumnIndex("h_tmp")));
                countryweather.setWD(cursor.getString(cursor.getColumnIndex("WD")));
                countryweather.setWS(cursor.getString(cursor.getColumnIndex("WS")));
                countryweather.setSunrise(cursor.getString(cursor.getColumnIndex("sunrise")));
                countryweather.setSunset(cursor.getString(cursor.getColumnIndex("sunset")));
            }while (cursor.moveToNext());
        }
        return countryweather;
    }

    //把countryname存到数据库中
    public void save (String cn){
        Cursor cursor = db.query("countname", null, "counn=?", new String[]{cn}, null, null, null);
        if(cursor.moveToFirst()){
            ContentValues values=new ContentValues();
            values.put("counn",cn);
            db.update("countname",values,"counn",new String[]{cn});

        }else {
            ContentValues values=new ContentValues();
            values.put("counn",cn);
            db.insert("countname",null,values);

        }

    }
    //把countryname从数据库中取出
    public String[] showw(String [] count){
        Cursor cursor=db.query("countname",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            int i = 0;
            do {
                count[i] = cursor.getString(cursor.getColumnIndex("counn"));
                i++;
            }while (cursor.moveToNext());
        }

    return count;
    }




//更新数据库的方法
}
