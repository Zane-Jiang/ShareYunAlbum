package com.Domain.AdministratorSys;

import com.ServiceUtils.BaseDAO;

import java.sql.Connection;

public class AdministratorDAOImpl extends BaseDAO implements AdministratorDAO {


    @Override
    public Administrator signIn(Connection connection, String admin_id) {
        Administrator administrator = null;
        String sql = "select * from admin where admin_id = ?";
        administrator = getInstance(connection,Administrator.class,sql,admin_id);
        return administrator;
    }
}
