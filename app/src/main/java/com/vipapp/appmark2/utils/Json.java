package com.vipapp.appmark2.utils;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.HashMap;

public class Json {

    private static com.google.gson.Gson gson = new com.google.gson.Gson();
    private static Gson pretty_gson = new GsonBuilder().setPrettyPrinting().create();

    public static HashMap<String, String> stringHashMap(String json){
        return gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
    }

    public static String toJson(HashMap hashMap){
        return gson.toJson(hashMap, new TypeToken<HashMap>(){}.getType());
    }
    public static String toPrettyJson(HashMap hashMap){
        return pretty_gson.toJson(hashMap, new TypeToken<HashMap>(){}.getType());
    }

    public static HashMap<String, String> hashMapFromXML(String xml){
        String json = toJson(xml);
        return stringHashMap(json);
    }
    @Nullable
    public static JSONObject jsonObjectFromXML(String xml){
        try{
            return XML.toJSONObject(xml);
        } catch (JSONException e) {
            return null;
        }
    }
    public static String toJson(String xml){
        JSONObject jsonObject = jsonObjectFromXML(xml);
        return jsonObject.toString();
    }
    public static JSONArray getJSONArray(JSONObject object, String name) throws JSONException{
        try{
            return object.getJSONArray(name);
        } catch (JSONException exception) {
            JSONArray array = new JSONArray();
            array.put(object.getJSONObject(name));
            return array;
        }
    }

}
