package com.osgzz.myweather.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Focus on 2016/8/15.
 */

public class WeatherOpenHelper extends SQLiteOpenHelper {
    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_COUNTY);
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * Province表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province (" + "id integer primary key autoincrement, " + "province_name text, " + "province_code text)";
    /**
     * City表建表语句
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
    public static final String CREATE_CITY = "create table City (" + "id integer primary key autoincrement, " + "city_name text, " + "city_code text, " + "province_id integer)";
    /**
     * County表建表语句
     */
    public static final String CREATE_COUNTY = "create table County (" + "id integer primary key autoincrement, " + "county_name text, " + "county_code text, " + "city_id integer)";


}
