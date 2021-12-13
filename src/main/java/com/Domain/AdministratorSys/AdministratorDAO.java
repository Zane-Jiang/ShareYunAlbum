package com.Domain.AdministratorSys;

import com.Domain.UserSys.User;

import java.sql.Connection;

public  interface   AdministratorDAO {

    User getAdministratorById(Connection conn , String id);
}
