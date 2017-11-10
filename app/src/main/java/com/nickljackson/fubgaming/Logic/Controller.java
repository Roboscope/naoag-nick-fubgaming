package com.nickljackson.fubgaming.Logic;

import com.nickljackson.fubgaming.ViewInterface;
import com.nickljackson.fubgaming.data.ListItem;
import com.nickljackson.fubgaming.data.ListItemDataSource;

/**
 * Created by Nick on 10.09.2017.
 */

public class Controller {

    private ViewInterface view;
    private ListItemDataSource dataSource;

    public Controller(ViewInterface view, ListItemDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void onListItemClick(ListItem listItem){

        //Write here what should happen after a ListItemOnClick
    }

    public void update(){
        dataSource.refresh();
    }
}

