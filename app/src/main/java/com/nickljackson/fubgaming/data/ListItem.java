package com.nickljackson.fubgaming.data;

/**
 * Created by Nick on 10.08.2017.
 */

public class ListItem {

    private String status;
    private String name;
    private String avatarURL;

    public ListItem(String status, String name, String avatarURL) {
        this.status = status;
        this.name = name;
        this.avatarURL = avatarURL;
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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
