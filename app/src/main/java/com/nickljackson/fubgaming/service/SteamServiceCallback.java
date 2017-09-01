package com.nickljackson.fubgaming.service;

import java.nio.channels.Channel;

/**
 * Created by Nick on 11.08.2017.
 */

public interface SteamServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception); 
}
