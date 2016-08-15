package com.osgzz.myweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.osgzz.myweather.model.City;
import com.osgzz.myweather.model.Country;
import com.osgzz.myweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Focus on 2016/8/15.
 */

public class WeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */
    private WeatherDB(Context context) {
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /*获取实例*/
    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * 将Province实例存储到数据库。
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据库读取全国所有的省份信息。
     */
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }


    /**
     * 将City实例存储到数据库。
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    /**
     * 从数据库读取某省下所有的城市信息。
     */
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 将County实例存储到数据库。
     */
    public void saveCounty(Country country) {
        if (country != null) {
            ContentValues values = new ContentValues();
            values.put("country_name", country.getCountyName());
            values.put("country_code", country.getCountyCode());
            values.put("city_id", country.getCityId());
            db.insert("Country", null, values);
        }
    }

    /**
     * 从数据库读取某城市下所有的县信息。
     */
    public List<Country> loadCounties(int cityId) {
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("Country", null, "city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                country.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                country.setCityId(cityId);
                list.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

}