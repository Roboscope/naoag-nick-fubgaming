package com.nickljackson.fubgaming.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItem implements Serializable{

    private String status;
    private String name;
    private String avatarURL;
    private Image avatar;

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
