package com.Domain.AdministratorSys;

public class Administrator {
    private String admin_id;
    private  String admin_name;
    private  String admin_authentication_string;
    private  String email;
    private  String status;

    public Administrator() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdmin_authentication_string() {
        return admin_authentication_string;
    }
}
