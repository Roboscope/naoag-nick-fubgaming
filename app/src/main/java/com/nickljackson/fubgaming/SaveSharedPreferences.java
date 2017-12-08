package com.nickljackson.fubgaming;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.nickljackson.fubgaming.data.ListItem;

import java.util.ArrayList;

/**
 * Created by Nick on 02.07.2017.
 */

public class SaveSharedPreferences {

    private static final String USER_NAME = "userName";
    private static final String STEAM_ID = "steamID";
    private static final String MEMBER_LIST = "memberList";


        private static SharedPreferences getSharedPreferences(Context ctx){
            return PreferenceManager.getDefaultSharedPreferences(ctx);
        }

        public static void setUserName(Context ctx, String userName){
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(USER_NAME, userName);
            editor.apply();
        }
        public static void setSteamId(Context ctx, long steamID){
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putLong(STEAM_ID, steamID);
            editor.apply();
        }

        public static void setMemberList(Context ctx, ArrayList<ListItem> memberList) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            Gson gson = new Gson();
            String json = gson.toJson(memberList);
            editor.putString(MEMBER_LIST, json);
            editor.apply();
        }

        public static String getUserName(Context ctx){
            return getSharedPreferences(ctx).getString(USER_NAME, "");
        }
        public static long getSteamId(Context ctx){return getSharedPreferences(ctx).getLong(STEAM_ID, 0);}
        public static ArrayList<ListItem> getMemberList(Context ctx){
            SharedPreferences sharedPreferences = getSharedPreferences(ctx);
            Gson gson =  new Gson();
            String json = sharedPreferences.getString(MEMBER_LIST, "");
            return gson.fromJson(json, ArrayList.class);
        }


        public static void clearSharedPreferences(Context ctx){
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.clear();
            editor.apply();
        }


}
