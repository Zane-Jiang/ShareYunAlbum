package com.ServiceUtils.MessageService;

import java.util.Date;

public class AutoCode {
    private  String code;
    private String phone;
    private long createTime;
    private  String status;

    public AutoCode() {
        this.phone = "11111111";
        this.code = "aaaaaaaaa";
        this.createTime = new Date().getTime();
        this.status = "11";
    }

    public AutoCode(String phone, String code, long date) {
        this.phone = phone;
        this.code = code;
        this.createTime = date;
        this.status = "0";
    }

    public String getCode() {
        return code;
    }

    public String getPhone() {
        return phone;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
