package com.Domain.PictureSys;

import java.util.Date;

public class Picture {
    private  String pic_id;
    private String pic_album;
    private Date pic_uploadtime;
    private String  pic_description;
//    private Blob pic_blob;

    private byte[] pic_blob;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Picture(String pic_id, String pic_album, Date pic_uploadtime, String pic_description, byte[]  pic_blob) {
        this.pic_id = pic_id;
        this.pic_album = pic_album;
        this.pic_uploadtime = pic_uploadtime;
        this.pic_description = pic_description;
        this.pic_blob = pic_blob;
        status = "101";
    }


    public Picture() {
        super();
    }

    public Picture(String pic_id, String pic_album, Date pic_uploadtime, String pic_description) {
        this.pic_id = pic_id;
        this.pic_album = pic_album;
        this.pic_uploadtime = pic_uploadtime;
        this.pic_description = pic_description;
        pic_blob = null;
    }

    public String getPic_id() {
        return pic_id;
    }

    public String getPic_album() {
        return pic_album;
    }

    public Date getPic_uploadtime() {
        return pic_uploadtime;
    }

    public String getPic_description() {
        return pic_description;
    }

//    public Blob getPic_blob() {
//       return pic_blob;
//    }

    public byte[] getPic_blob() {
        return pic_blob;
    }
}
