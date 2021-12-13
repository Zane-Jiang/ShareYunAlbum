package com.Domain.AlbumSys;

import java.util.Date;

public class Album {
    private String album_id;

    private String album_name;
    private String album_owner;
    private String album_description;
    private Date album_createtime;
    private String album_public;
    private  String status;

    public Album() {
    }

    public Album(String album_id, String album_name, String album_owner, String album_description, Date album_createtime, String album_public) {
        this.status = "101";
        this.album_id = album_id;
        this.album_name = album_name;
        this.album_owner = album_owner;
        this.album_description = album_description;
        this.album_createtime = album_createtime;
        this.album_public = album_public;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getAlbum_owner() {
        return album_owner;
    }

    public String getAlbum_description() {
        return album_description;
    }

    public Date getAlbum_createtime() {
        return album_createtime;
    }

    public String getAlbum_public() {
        return album_public;
    }
}
