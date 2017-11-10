package com.nickljackson.fubgaming.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Toast;

import com.nickljackson.fubgaming.ViewInterface;
import com.nickljackson.fubgaming.service.SteamService;
import com.nickljackson.fubgaming.service.SteamServiceCallback;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItemDataSource implements SteamServiceCallback {

    private String[] steamID = {"76561198105737739", "76561198144165775", "76561198264064062", "76561198182175647", "76561198170537355", "76561198171801778", "76561198142027924", "76561198091280667"};
    private ArrayList<ListItem> listOfData = new ArrayList<>();
    private ArrayList<String> listOfGames = new ArrayList<>();


    private SteamService service;
    private ProgressDialog dialog;

    private Context context;

    private ViewInterface viewInterface;

    public ListItemDataSource(Context pContext, ViewInterface viewInterface) {
        context = pContext;
        this.viewInterface = viewInterface;
        service = new SteamService(this);
        getData();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();

    }

    public void refresh(){
        getData();
    }

    private void getData(){
        StringBuilder steamIds = new StringBuilder();
        for (String aSteamID : steamID) {
            steamIds.append(steamIds.length() == 0 ? "" : ",").append(aSteamID);
        }
        listOfData.clear();
        service.refresh(steamIds.toString());
    }

    @Override
    public void refreshServiceProgress(JSONObject queryResults) {

        String avatarURL = queryResults.optString("avatarmedium");
        String name = queryResults.optString("personaname");
        long lastLogOff = queryResults.optLong("lastlogoff");
        int status = queryResults.optInt("personastate");
        String gameID = queryResults.optString("gameid");

        String statusString;
        if(gameID.isEmpty()){
            switch(status){
                case 0:
                    statusString = "Zuletzt online " +logOffToString(lastLogOff);
                    break;
                case 1:
                    statusString = "Online";
                    break;
                case 2:
                    statusString = "Beschäftigt";
                    break;
                case 3:
                    statusString = "Abwesend";
                    break;
                case 4:
                    statusString = "Schlummern";
                    break;
                case 5:
                    statusString = "möchte traden";
                    break;
                case 6:
                    statusString = "möchte spielen";
                    break;
                default:
                    statusString = "Unknown";
            }
        } else {
            statusString = String.valueOf(gameID);
            listOfGames.add(gameID);

        }


        listOfData.add(new ListItem(statusString, name, avatarURL));


    }

    private String logOffToString(long logOffTimestamp) {
        return DateUtils.getRelativeDateTimeString(this.context, logOffTimestamp * 1000L, DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0).toString();
    }

    @Override
    public void refreshServiceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void refreshServiceSuccess() {
        dialog.hide();

        viewInterface.updateAdapterAndView(listOfData);

    }

    public void sortListOfData(){
        ArrayList<ListItem> online = new ArrayList<>();
        ArrayList<ListItem> offline = new ArrayList<>();

        for(int i = 0; i <listOfData.size(); i++) {
        }


                //SORTIEREN!!!
        }
    }

}
