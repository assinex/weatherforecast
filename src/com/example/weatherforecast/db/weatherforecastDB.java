package com.example.weatherforecast.db;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherforecast.model.City;
import com.example.weatherforecast.model.Country;
import com.example.weatherforecast.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class weatherforecastDB {

	/**
	 * 数据库名
	 */
	public static final String DB_NAME = "cool_weather";

	/**
	 * 数据库版本
	 */
	public static final int VERSION = 1;

	private static weatherforecastDB weatherforecastDB;

	private SQLiteDatabase db;

	/**
	 * 构造方法私有化
	 */
	private weatherforecastDB(Context context) {
		weatherforecastOpenHelper dbHelper = new weatherforecastOpenHelper(context,
				DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 获取CoolWeatherDB的实例
	 */
	public synchronized static weatherforecastDB getInstance(Context context) {
		if (weatherforecastDB == null) {
			weatherforecastDB = new weatherforecastDB(context);
		}
		return weatherforecastDB;
	}

	/**
	 * 将Province实例存储到数据库
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getprovinceName());
			values.put("province_code", province.getprovinceCode());
			db.insert("Province", null, values);
		}
	}

	/**
	 * 从数据库读取全国所有的省份信息
	 */
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * 将City实例存储到数据库
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getprovinceId());
			db.insert("City", null, values);
		}
	}

	/**
	 * 从数据库读取某省下所有的城市信息
	 */
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setcityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setcityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setprovinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * 将Country实例存储到数据库
	 */
	public void saveCountry(Country country) {
		if (country != null) {
			ContentValues values = new ContentValues();
			values.put("country_name", country.getCountryName());
			values.put("country_code", country.getCountryCode());
			values.put("city_id", country.getcityId());
			db.insert("Country", null, values);
		}
	}

	/**
	 * 从数据库读取某城市下所有的县信息
	 */
	public List<Country> loadCounties(int cityId) {
		List<Country> list = new ArrayList<Country>();
		Cursor cursor = db.query("Country", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Country country = new Country();
				country.setId(cursor.getInt(cursor.getColumnIndex("id")));
				country.setcountryName(cursor.getString(cursor
						.getColumnIndex("country_name")));
				country.setcountryCode(cursor.getString(cursor
						.getColumnIndex("country_code")));
				country.setcityId(cityId);
				list.add(country);
			} while (cursor.moveToNext());
		}
		return list;
	}

}
