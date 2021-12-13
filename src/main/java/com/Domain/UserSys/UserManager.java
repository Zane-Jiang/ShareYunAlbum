package com.Domain.UserSys;

import com.ServiceUtils.DBConnection;
import com.ServiceUtils.MessageService;

import java.sql.Connection;

public class UserManager {
    public static String modifyUserPublicInfo(String userPhone, String userName, String userDesc, String userSex) {
        String status = "101";
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();
            if (userDAO.modifyUserPublicInfo(connection, userPhone, userName, userDesc, userSex)) {
                status = "100";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return status;
    }



    public static String sendMessage(String phone) {


        String status = "";
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();

            //首先判断是不是新用户
            if (userDAO.getUserByPhone(connection, phone) != null) {
                if(MessageService.sendMessage(phone)){
                    status="100";
//                    不是新用户，且信息发送成功
                }else{
                    status= "101";
                }
            } else {
                if (MessageService.sendMessage(phone)) {
                    status = "200";
//                    新用户，且短信发送成功
                } else {
                    status = "201";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static User getUserByPhone(String phone) {
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = new User();
        try {
            connection =DBConnection.getConnection();
             user = userDAO.getUserByPhone(connection,phone);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return user;
    }

    public static User createUserByPhone(String phone) {
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = new User();
        try {
            connection =DBConnection.getConnection();
            if(userDAO.crteateUserByPhone(connection,phone)){
            user = userDAO.getUserByPhone(connection,phone);}
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return user;
    }

    public static String deleteUserByPhone(String phone, String autoCode) {

        String status = "102";
        UserDAOImpl userDAO = new UserDAOImpl();
        Connection connection = null;

        try {
            connection =DBConnection.getConnection();
            if(autoCode.equals(MessageService.getParams(phone)[0])){
                if(userDAO.deleteUserByPhone(connection,phone)){
                    status = "100";
                }
            }else {
                status = "101";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return status;


    }
}
