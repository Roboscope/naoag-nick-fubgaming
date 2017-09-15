package com.nickljackson.fubgaming.Logic;

import com.nickljackson.fubgaming.ViewInterface;
import com.nickljackson.fubgaming.data.ListItem;
import com.nickljackson.fubgaming.data.ListItemDataSourceInterface;

/**
 * Created by Nick on 10.09.2017.
 */

public class Controller {

    private ViewInterface view;
    private ListItemDataSourceInterface dataSource;

    public Controller(ViewInterface view, ListItemDataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void getListFromDataSource(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }

    public void onListItemClick(ListItem listItem){

        //Write here what should happenz after a ListItemOnClick
    }
}

