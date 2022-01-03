package com.Domain.AdministratorSys;

import java.sql.Connection;

public  interface   AdministratorDAO {

//    获取所有用户phone

    Administrator signIn(Connection connection, String admin_id);
}
