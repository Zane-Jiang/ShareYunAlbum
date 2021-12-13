package com.Domain.UserSys;

import com.ServiceUtils.DBConnection;
import junit.framework.TestCase;

import java.sql.Connection;

public class UserDAOImplTest extends TestCase {

    public void testGetUserById() {
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();
            User user =  userDAO.getUserById(connection,"123456");
            System.out.print(user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
    }

    public void testCrteateUserById(){
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();
            boolean s =  userDAO.crteateUserById(connection,"456","111");
            System.out.print(s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
    }

}