package com.vipapp.appmark2.util;

import androidx.annotation.Nullable;

import com.vipapp.appmark2.callback.Mapper;
import com.vipapp.appmark2.callback.Predicate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ArrayUtils {

    public static <StartType, EndType> ArrayList<EndType> map(List<StartType> list, Mapper<StartType, EndType> mapper){
        ArrayList<EndType> arrayList = new ArrayList<>();
        for(StartType elem: list)
            arrayList.add(mapper.map(elem));
        return arrayList;
    }

    public static <StartType, EndType> EndType[] map(StartType[] array, EndType[] result_array, Mapper<StartType, EndType> mapper){
        return map(Arrays.asList(array), mapper).toArray(result_array);
    }

    public static <T> boolean any(List<T> arrayList, Predicate<T> predicate){
        for(T item: arrayList){
            if(predicate.test(item))
                return true;
        }
        return false;
    }
    public static <T> boolean all(List<T> arrayList, Predicate<T> predicate){
        for(T item: arrayList){
            if(!predicate.test(item))
                return false;
        }
        return true;
    }

    public static <T> T[] filter(T[] array, Predicate<T> predicate){
        ArrayList<T> new_list = new ArrayList<>();
        for(T item: array){
            if(predicate.test(item)) new_list.add(item);
        }
        //noinspection unchecked
        return (T[]) new_list.toArray();
    }
    public static <T> ArrayList<T> filter(ArrayList<T> array, Predicate<T> predicate){
        ArrayList<T> new_list = new ArrayList<>();
        for(T item: array){
            if(predicate.test(item)) new_list.add(item);
        }
        return new_list;
    }

    public static <T> boolean in_array(T[] array, T value){
        return Arrays.asList(array).contains(value);
    }
    @Nullable
    public static JSONObject getObjectFromArray(JSONArray array, Mapper<JSONObject, Boolean> mapper){
        try {
            int i = 0;
            while (!array.isNull(i)) {
                JSONObject object = array.getJSONObject(i);
                if (mapper.map(object))
                    return object;
                i++;
            }
        } catch (JSONException ignored){}
        return null;
    }
}
