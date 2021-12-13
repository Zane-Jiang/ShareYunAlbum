package com.Domain.UserSys;
import com.ServiceUtils.BaseDAO;

import java.sql.Connection;

public class UserDAOImpl extends BaseDAO implements UserDAO  {
    //这里要先写extends 后写implements




    @Override
    public User getUserByPhone(Connection conn, String phone) {
        String sql = "select * from user where user_phone = ?";
        User user= getInstance(conn,User.class, sql,phone);
        return user;
    }

    @Override
    public boolean deleteUserById(Connection conn, String user_id) {
        return false;
    }


    @Override
    public boolean crteateUserByPhone(Connection conn, String phone,User user) {

        String sql = "INSERT INTO user (user_phone,user_name,user_sex,user_avatar,user_description) VALUES (?, ?, ?, ?,?)";
       if( update(conn,sql,phone,user.getUser_name(),user.getUser_sex(),user.getUser_avatar(),user.getUser_description()) == 1){
           return true;
       }
       return false;
    }


    @Override
    public boolean modifyUserPublicInfo(Connection conn,String user_phone,String user_name,String user_desc,String user_sex)
    {
        String sql = "UPDATE user SET user_name = ? , user_description = ? , user_sex = ? WHERE user_phone = ?";
        if(update(conn,sql,user_name,user_desc,user_sex,user_phone)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserByPhone(Connection conn, String user_phone) {
        String sql = "DELETE FROM user WHERE user_phone = ? ";
        if(update(conn,sql,user_phone) == 1){
            return true;
        }
        return false;
    }


}
