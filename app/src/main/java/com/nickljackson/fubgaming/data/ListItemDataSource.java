package com.nickljackson.fubgaming.data;

import android.content.Context;
import android.widget.Toast;
import com.nickljackson.fubgaming.service.SteamServiceCallback;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItemDataSource implements ListItemDataSourceInterface, SteamServiceCallback {

    private Context context;
    private static final String[] STEAMIDS = {"76561198142027924", "76561198170537355", "76561198264064062",
    "76561198091280667", "76561198182175647", "76561198105737739", "76561198171801778"};

    @Override
    public List<ListItem> getListOfData() {
        ArrayList<ListItem> listOfData = new ArrayList<>();

        return null;
    }

    public ListItemDataSource(Context pContext) {
        context = pContext;
    }

    public void setData(){
        for(int i = 0; i< STEAMIDS.length; i++){

        }
    }

    @Override
    public void serviceSuccess(Channel channel) {

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
