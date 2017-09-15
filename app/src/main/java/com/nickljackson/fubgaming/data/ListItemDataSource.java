package com.nickljackson.fubgaming.data;

import com.nickljackson.fubgaming.service.SteamService;
import com.nickljackson.fubgaming.service.SteamServiceCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItemDataSource implements ListItemDataSourceInterface, SteamServiceCallback {

    private String[] steamID = {"76561197960435530", "76561198264064062"};
    private int[] lastLogOff = new int[steamID.length];
    private int[] status = new int[steamID.length];
    private String[] gameID = new String[steamID.length];
    private String[] name = new String[steamID.length];
    private String[] avatarURL = new String[steamID.length];

    private SteamService service;

    int i;

    @Override
    public List<ListItem> getListOfData() {
        ArrayList<ListItem> listOfData = new ArrayList<>();
        for(int v = 0; v < steamID.length; v++){
            if(status[v] == 0){
                listOfData.add(new ListItem(String.valueOf(lastLogOff[v]), 0, name[v], avatarURL[v]));
            } else if(status[v] == 1){
                listOfData.add(new ListItem(String.valueOf(status[v]), 1, name[v], avatarURL[v]));
            } else{
                listOfData.add(new ListItem(String.valueOf(gameID), 2, name[v], avatarURL[v]));
            }
        }
        return null;
    }

    public ListItemDataSource() {
        service = new SteamService(this);
        getData();
    }


    private void getData(){
        for(i = 0; i < steamID.length; i++){
            service.refresh(steamID[i]);
        }
    }

    @Override
    public void serviceSuccess(JSONObject queryResults) {
        avatarURL[i] = queryResults.optString("avatarmedium");
        name[i] = queryResults.optString("personaname");
        lastLogOff[i] = queryResults.optInt("lastlogoff");
        status[i] = queryResults.optInt("personastate");
        gameID[i] = queryResults.optString("gameid");

    }

    @Override
    public void serviceFailure(Exception exception) {

    }
}
