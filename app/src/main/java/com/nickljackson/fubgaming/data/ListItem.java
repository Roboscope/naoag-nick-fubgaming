package com.nickljackson.fubgaming.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItem {

    private String status;
    private String name;
    private String avatarURL;

    public ListItem(String status, String name, String avatar) {
        this.status = status;
        this.name = name;
        this.avatarURL = avatar;
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

    public String getAvatar() {
        return avatarURL;
    }

    public void setAvatar(String avatar) {
        this.avatarURL = avatar;
    }
}
