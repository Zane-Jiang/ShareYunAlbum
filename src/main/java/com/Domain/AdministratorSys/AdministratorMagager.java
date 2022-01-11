package com.Domain.AdministratorSys;

import com.Domain.PictureSys.PictureManager;
import com.ServiceUtils.ContentReview.PictureReview;
import com.ServiceUtils.DBConnection;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public class AdministratorMagager {

    public static String signInBypassword(String admin_id, String password){
        Connection connection = null;
        AdministratorDAOImpl administratorDAO = new AdministratorDAOImpl();
        String status = null;
        Administrator administrator = new Administrator();
        try {
            connection = DBConnection.getConnection();
         if((administrator = administratorDAO.signIn(connection,admin_id)) != null){
             status = administrator.getAdmin_authentication_string().equals(password)?"100":"102";
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

    public static boolean deleteUserById(){
        return  true;
    }

    /**
     * 返回对应日期之后的图片id
     * @param startDate
     * @return
     */
    public static String[] getPictureID(String startDate){
        return null;
    }

    /**
     * 返回对应起始时间后的评论ID
     * @param startDate
     * @return
     */
    public static String[] getCommentID(String startDate){
        return null;
    }


    public static JSONObject autoReviewPicture(String pic_id) {
        JSONObject date = null;
        InputStream pic = PictureManager.download(pic_id);
        try {
            byte[] picByte = IOUtils.toByteArray(pic);
           date = (JSONObject) PictureReview.Review(picByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return date;
    }
}
