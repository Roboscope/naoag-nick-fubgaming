package com.nickljackson.fubgaming.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItem {

    private String status;
    private String name;
    private Drawable avatar;

    public ListItem(String status, String name, Drawable avatar) {
        this.status = status;
        this.name = name;
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }
}
