package com.nickljackson.fubgaming.service;

import android.os.AsyncTask;

/**
 * Created by Nick on 11.08.2017.
 */

public class SteamService {
    private SteamServiceCallback callback;
    private String steamID;

    public SteamService(SteamServiceCallback callback) {
        this.callback = callback;
    }

    public String getSteamID() {
        return steamID;
    }

    public void refresh(String steamID){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

        }.execute(steamID);

        }
    }


