package com.Domain.UserSys;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {



    List<User> getAllPhone(Connection conn);

    User getUserByPhone(Connection conn, String phone);


   boolean deleteUserById(Connection conn,String user_id);


    boolean crteateUserByPhone(Connection conn, String phone, User user);

    boolean modifyUserPublicInfo(Connection conn, String user_id, String user_name, String user_desc, String user_sex);


    boolean deleteUserByPhone(Connection conn, String user_phone);

    Long getSum(Connection connection);
}
