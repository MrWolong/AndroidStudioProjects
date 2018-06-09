package com.example.thomas.myweather.util;

import android.text.TextUtils;

import com.example.thomas.myweather.db.City;
import com.example.thomas.myweather.db.County;
import com.example.thomas.myweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Thomas on 2017/12/5.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
           try{
               JSONArray allProvince = new JSONArray(response);
               for(int i = 0;i<allProvince.length();i++) {
                   JSONObject provinceObject = allProvince.getJSONObject(i);
                   Province province = new Province();
                   province.setName(provinceObject.getString("name"));
                   province.setCode(provinceObject.getInt("id"));
                   province.save();
               }
               return true;
           }catch (Exception e) {
               e.printStackTrace();
           }
        }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCity = new JSONArray(response);
                for(int i = 0;i<allCity.length();i++) {
                    JSONObject cityObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setName(cityObject.getString("name"));
                    city.setCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for(int i = 0;i<allCounties.length();i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
