package com.Domain.UserSys;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class User {
    private String user_phone;
    private  String user_name;

    private String user_description;
    private String user_sex;
    private InputStream user_avatar;
    private Date user_registertime;

    private  String status;
    public User() {
        this.user_name = "default user";
        this.user_description =" default description";
        this.user_sex =" men";
        this.user_avatar = null;
        this.user_phone =" user_phone";
        this.user_registertime = new Date();
        this.status = "100";
    }
    public User(String user_name,  String user_email, String user_description, String user_sex, FileInputStream user_avatar, String user_phone, Date user_registertime) {
        this.user_name = user_name;


        this.user_description = user_description;
        this.user_sex = user_sex;
        this.user_avatar = user_avatar;
        this.user_phone = user_phone;
        this.user_registertime = user_registertime;
    }

    /**
     * 参数初始值为false ，用户名为无效状态
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    public String isStatus() {
        return status;
    }

//获取对象属性方法：

    public String getUser_name() {
        return user_name;
    }


    public String getUser_description() {
        return user_description;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public InputStream getUser_avatar() {
        return user_avatar;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public Date getUser_registertime() {
        return user_registertime;
    }

    public String getStatus() {
        return status;
    }




    @Override
    public String toString() {
        String str = "[user: "+"  user_name: " +user_name+
                "phone"+user_phone+"]";
        return str;
    }
}
