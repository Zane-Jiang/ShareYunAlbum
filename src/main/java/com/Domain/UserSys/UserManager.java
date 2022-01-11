package com.Domain.UserSys;

import com.ServiceUtils.DBConnection;
import com.ServiceUtils.MessageService.MessageService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("get=+++=="+ user);
        return user;
    }

    public static User createUserByPhone(String phone) {
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = new User();
        System.out.println(user);
        try {
            connection =DBConnection.getConnection();
            if(userDAO.crteateUserByPhone(connection,phone,user)){
            user = userDAO.getUserByPhone(connection,phone);}
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        System.out.println("crete=+++=="+ user);
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
            }else if(MessageService.getParams(phone).equals("unavailable")){
                status = "101";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return status;


    }

    public static String getUserSum() {
        Connection connection = null;
       Long sum = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();
            sum =  userDAO.getSum(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return String.valueOf(sum);
    }


    public static ArrayList<String> getUserPhones() {
        ArrayList<String> phone = new ArrayList<String>();
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();

        try {
            connection = DBConnection.getConnection();
            List<User> List = userDAO.getAllPhone(connection);
            int i =  0;
            for (User user :List){
                System.out.println(user.getUser_phone());
                phone.add(user.getUser_phone());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return  phone;
    }

    public static boolean deleteUserByPhoneWithoutCode(String phone) {
        System.out.println("delete : "+phone);
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean flag = false;
        try {
            connection = DBConnection.getConnection();
            flag  = userDAO.deleteUserByPhone(connection,phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
}
