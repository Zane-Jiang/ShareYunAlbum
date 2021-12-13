package com.Domain.PictureSys;

import com.ServiceUtils.DBConnection;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PictureManager {
    public static Picture upload(String pic_album, String pic_description, Blob pic_blob) {
        Date pic_createtime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String pic_id = pic_album+simpleDateFormat.format(pic_createtime);
        Picture picture = new Picture(pic_id,pic_album,pic_createtime,pic_description,pic_blob);

        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();

        try {
            connection = DBConnection.getConnection();
            if(pictureDAO.createPictrue(connection,picture)){
                picture.setStatus("100");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return  picture;
    }


    public static String[] getPictureId(String album_id){
        String[] picture_id = null;
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();

        try {
            connection = DBConnection.getConnection();
            List<Picture> List = pictureDAO.getPictureByAlbum(connection,album_id);
            int i =  0;
            for (Picture pic :List){
                picture_id[i++] = pic.getPic_id();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return  picture_id;


    }

    public static InputStream download(String pic_id) {
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();
        InputStream pic = null;

        try {
            connection = DBConnection.getConnection();
            Picture picture = pictureDAO.getPictureById(connection,pic_id);
            pic = picture.getPic_blob();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeResource(connection,null);
        }
        return pic;
    }
}
