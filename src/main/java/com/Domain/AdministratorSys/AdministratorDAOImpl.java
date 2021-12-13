package com.Domain.AdministratorSys;

import com.Domain.UserSys.User;
import com.ServiceUtils.BaseDAO;

import java.sql.Connection;

public class AdministratorDAOImpl extends BaseDAO implements AdministratorDAO {

    @Override
    public User getAdministratorById(Connection conn, String id) {
        return null;
    }
}
