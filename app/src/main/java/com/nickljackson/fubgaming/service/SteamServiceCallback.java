package com.nickljackson.fubgaming.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.channels.Channel;

/**
 * Created by Nick on 11.08.2017.
 */

public interface SteamServiceCallback {
    void serviceProgress(JSONObject queryResults);
    void serviceFailure(Exception exception);
    void serviceSuccess();
}
