package com.nickljackson.fubgaming;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nick on 02.07.2017.
 */

public class SaveSharedPreference {

    static final String USER_NAME = "username";
    static final String STEAM_ID = "000000000";


        static SharedPreferences getSharedPreferences(Context ctx){
            return PreferenceManager.getDefaultSharedPreferences(ctx);
        }

        public static void setUserName(Context ctx, String userName){
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(USER_NAME, userName);
            editor.commit();
        }
        public static void setSteamId(Context ctx, int steamID){
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putInt(STEAM_ID, steamID);
            editor.commit();
        }

        public static String getUserName(Context ctx){
            return getSharedPreferences(ctx).getString(USER_NAME, "");
        }
        public static String getSteamId(Context ctx){
            return getSharedPreferences(ctx).getString(STEAM_ID, "");
        }

        public static void clearSharedPreferences(Context ctx){
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.clear();
            editor.commit();
        }
}
