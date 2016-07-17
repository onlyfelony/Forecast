package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/16.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public  static final String COUNTRY_WEATHER="create table weather(" +
            "id integer primary key autoincrement," +
            "city text," +
            "date text," +
            "time text," +
            "weather text," +
            "temp text," +
            "l_tmp text," +
            "h_tmp text," +
            "WD text," +
            "WS text," +
            "sunrise text," +
            "sunset text)";

    public static final String COUNTRY_NAME="create table countname(" +
            "id integer primary key autoincrement ,"+
            "counn text )";



    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COUNTRY_WEATHER);
        db.execSQL(COUNTRY_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

