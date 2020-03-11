package com.vipapp.appmark2.server;

import androidx.annotation.NonNull;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.util.Json;
import com.vipapp.appmark2.util.OkHttp;

import java.util.HashMap;

import static com.vipapp.appmark2.util.Const.SERVER_CHANGELOG;
import static com.vipapp.appmark2.util.Const.SERVER_INFO;
import static com.vipapp.appmark2.util.Const.SERVER_STATUS;

@SuppressWarnings("WeakerAccess")
public class Server {

    private static Boolean server_online;
    private static HashMap<String, String> info;
    private static String changelog;

    public static void getServerOnline(@NonNull PushCallback<Boolean> callback){
        if(server_online != null) {
            callback.onComplete(server_online);
        } else {
            OkHttp.get(SERVER_STATUS, result -> {
                if(result != null) {
                    server_online = result.equals("OK");
                }
                callback.onComplete(server_online);
            });
        }
    }

    public static void getAppInfo(@NonNull PushCallback<HashMap<String, String>> callback){
        if(info == null){
            loadAfterCheckServer(SERVER_INFO, result -> {
                if (result != null) {
                    info = Json.stringHashMap(result);
                    getAppInfo(callback);
                } else {
                    callback.onComplete(null);
                }
            });
        } else {
            callback.onComplete(info);
        }
    }
    public static void getActualVersion(@NonNull PushCallback<String> callback){
        getAppInfo(info -> callback.onComplete(info == null? null: info.containsKey("version")? info.get("version"): null));
    }
    public static void getChangelog(@NonNull PushCallback<String> callback){
        if(changelog == null) loadAfterCheckServer(SERVER_CHANGELOG, result -> {
            changelog = result;
            callback.onComplete(changelog);
        }); else {
            callback.onComplete(changelog);
        }
    }

    private static void loadAfterCheckServer(String url, PushCallback<String> callback){
        getServerOnline(result -> {
            if(result) OkHttp.get(url, callback); else {
                callback.onComplete(null);
            }
        });
    }

}
