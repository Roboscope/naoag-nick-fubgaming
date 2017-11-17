package com.nickljackson.fubgaming.service;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by Nick on 11.08.2017.
 */

public class SteamService {
    private SteamServiceCallback callback;
    private Exception error;
    public JSONArray queryResults;

    public SteamService(SteamServiceCallback callback) {
        this.callback = callback;
    }

    public void refresh(final String steamID){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String endpoint = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=DE3840B166C98E213D1866CCB6188AF4&steamids=" + steamID;
                try{
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }

                    return result.toString();
                } catch (Exception e){
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if( s == null && error != null){
                    callback.refreshServiceFailure(error);
                    return;
                }

                try{
                    JSONObject data = new JSONObject(s);

                    queryResults = data.optJSONObject("response").optJSONArray("players");
                    for(int i = 0; i < queryResults.length(); i++){
                        callback.refreshServiceProgress(queryResults.getJSONObject(i));

                    }

                    callback.refreshServiceSuccess();
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }.execute(steamID);

    }


}


