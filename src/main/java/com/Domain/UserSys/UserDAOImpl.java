package com.Domain.UserSys;
import com.ServiceUtils.BaseDAO;

import java.sql.Connection;

public class UserDAOImpl extends BaseDAO implements UserDAO  {
    //这里要先写extends 后写implements

    /**
     * 根据ID返回用户对象
      * @param conn
     * @param userID
     * @return
     * 若无ID对应的对象，则返回的对象是null
     */
    @Override
    public User getUserById(Connection conn, String userID) {

        String sql = "select user_id,user_name,user_authentication_string from user where user_id = ?";
        User user= getInstance(conn,User.class, sql,userID);
        return user;
    }

    @Override
    public User getUserByPhone(Connection conn, String phone) {
        String sql = "select user_id,user_name,user_authentication_string from user where user_phone = ?";
        User user= getInstance(conn,User.class, sql,phone);
        return user;
    }


    @Override
    public boolean crteateUserById(Connection conn, String user_id, String user_authentication_string) {

        String sql = "INSERT INTO user (user_id, user_name, user_authentication_string, user_email, user_sex, user_phone, user_avatar) VALUES (?, null, ?, null, null, null, null)";
       if( update(conn,sql,user_id,user_authentication_string) == 1){
           return true;
       }
       return false;
    }


    @Override
    public boolean modifyUserPublicInfo(Connection conn,String user_id,String user_name,String user_desc,String user_sex)
    {
        String sql = "UPDATE user SET user_name = ? , user_description = ? , user_sex = ? WHERE user_id = ?";
        if(update(conn,sql,user_name,user_desc,user_sex,user_id)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Connection conn, String user_id) {
        String sql = "DELETE FROM user WHERE user_id = ? ";
        if(update(conn,sql,user_id) == 1){
            return true;
        }
        return false;
    }


}
