package com.Domain.PictureSys;

import com.ServiceUtils.DBConnection;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PictureManager {
    public static Picture upload(String pic_album, String pic_description, byte[] pic_blob) {
        Date pic_createtime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String pic_id = pic_album + simpleDateFormat.format(pic_createtime);
        Picture picture = new Picture(pic_id, pic_album, pic_createtime, pic_description, pic_blob);

        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();

        try {
            connection = DBConnection.getConnection();
            if (pictureDAO.createPicture(connection, picture)) {
                picture.setStatus("100");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return picture;
    }


    public static ArrayList<String> getPictureId(String album_id) {
        ArrayList<String> picture_id = new ArrayList<String>();
//        String[] picture_id = null;
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();

        try {
            connection = DBConnection.getConnection();
            List<Picture> List = pictureDAO.getPictureByAlbum(connection, album_id);
            int i = 0;
            for (Picture pic : List) {
                System.out.println(pic.getPic_id());
                picture_id.add(pic.getPic_id());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return picture_id;
    }

    public static InputStream download(String pic_id) {
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();
        InputStream pic = null;

        try {
            connection = DBConnection.getConnection();
            Picture picture = pictureDAO.getPictureById(connection, pic_id);
            if (picture.getPic_blob() != null)
                pic = new ByteArrayInputStream(picture.getPic_blob());
//            System.out.println(" =========pic Stream "+new String(String.valueOf(pic)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return pic;
    }

    public static Picture gitPictureInfoById(String pic_id) {
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();
        Picture picture = null;
        try {
            connection = DBConnection.getConnection();
            picture = pictureDAO.getPictureById(connection, pic_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return picture;
    }

    public static boolean deletePicture(String pic_id) {
        Connection connection = null;
        boolean flag = true;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();
        try {
            connection = DBConnection.getConnection();
            flag = pictureDAO.delete(connection, pic_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return flag;
    }

    public static String getPicteureSum() {
        Connection connection = null;
        Long sum = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();
        try {
            connection = DBConnection.getConnection();
            sum = pictureDAO.getSum(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return Long.toString(sum);
    }

    public static ArrayList<String> getAllPicture() {
        ArrayList<String> picture_id = new ArrayList<String>();
//        String[] picture_id = null;
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();

        try {
            connection = DBConnection.getConnection();
            List<Picture> List = pictureDAO.getAllPicture(connection);
            int i = 0;
            for (Picture pic : List) {
                System.out.println(pic.getPic_id());
                picture_id.add(pic.getPic_id());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return picture_id;
    }

    public static String getPic_user(String pic_id)
    {
        String user_id=null;
        Connection connection = null;
        PictureDAOImpl pictureDAO = new PictureDAOImpl();

        try{
            connection = DBConnection.getConnection();
            user_id=pictureDAO.getUserIdByPicId(connection,pic_id);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResource(connection, null);
        }
        return user_id;
    }
}
