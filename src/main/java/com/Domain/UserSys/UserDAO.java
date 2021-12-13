package com.Domain.UserSys;

import java.sql.Connection;

public interface UserDAO {


   User getUserById(Connection conn , String id);

    User getUserByPhone(Connection conn, String phone);

    boolean crteateUserById(Connection conn, String user_id, String user_authentication_string);

   boolean deleteUserById(Connection conn,String user_id);

   boolean modifyUserPublicInfo(Connection conn,String user_id,String user_name,String user_desc,String user_sex);




}
