package com.Domain.UserSys;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class User {
    private String user_id;
    private  String user_name;
    private String user_authentication_string;
    private  String user_email;
    private String user_description;
    private String user_sex;
    private InputStream user_avatar;
    private String user_phone;
    private Date user_registertime;

    private  String status;
    public User() {
        this.user_id = "user_id-1-1-1-1-1-1-1-1-1";//
        this.user_name = "user_name";
        this.user_authentication_string = "authentication_string";
        this.user_email = "user_email";
        this.user_description =" user_description";
        this.user_sex =" user_sex";
        this.user_avatar = null;
        this.user_phone =" user_phone";
        this.user_registertime =null;
        this.status = "100";
    }
    public User(String user_id, String user_name, String authentication_string, String user_email, String user_description, String user_sex, FileInputStream user_avatar, String user_phone, Date user_registertime) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_authentication_string = authentication_string;
        this.user_email = user_email;
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
    public String getUser_authentication_string() {
        return user_authentication_string;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
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
        String str = "[user: "+"user_id: "+user_id+"  user_name: " +user_name+"  authentication_string: "+user_authentication_string+
                "  user_email:  "+user_email+"]";
        return str;
    }
}
