package com.Domain.UserSys;

import com.ServiceUtils.DBConnection;

import java.sql.Connection;

public class UserManager {

    /**
     * 密码登录
     * @param userID
     * @param password
     * @return
     */
    public static User signInByPassword(String userID, String password) {
        //System.out.println("UserManager:userID=>"+userID);
        User user = null;
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();
            user =  userDAO.getUserById(connection,userID);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }

        if(user == null){
            System.out.println("用户名不存在");
            user = new User();
            user.setStatus("101");//设置用户无效
        }else{
            if(!password.equals(user.getUser_authentication_string())){
                System.out.println("密码错误");
                user.setStatus("102");
            }
        }
        return user;
    }

    public static String signUpByPassword(String userID,String password){
        String status = "100";
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = null;
        try {
            connection = DBConnection.getConnection();
            user = userDAO.getUserById(connection,userID);
            if(user != null){
                //账号已注册，失败
                status = "101";
            }else{
                if(!userDAO.crteateUserById(connection,userID,password))
                    //注册时发生的未知错误
                    status = "102";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return status;
    }

    public static String modifyUserPublicInfo(String userID,String userName,String userDesc,String userSex)
    {
        String status="101";
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            connection = DBConnection.getConnection();
            if(userDAO.modifyUserPublicInfo(connection,userID,userName,userDesc,userSex))
            {
                status="100";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return status;
    }

    public static String deleteUserByPassword(String userID,String password){
        String status = "102";
        Connection connection = null;
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = null;
        try {
            connection = DBConnection.getConnection();
            user = userDAO.getUserById(connection,userID);
//            此时传输过来的账号密码一定是存在的，因为他是登陆状态的，所以user！=null
            if(user.getUser_authentication_string().equals(password)){
//                如果密码正确，则删除
                if(userDAO.deleteUserById(connection,userID)){
                    status = "100";
                }
            }else {
//                密码错误
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
