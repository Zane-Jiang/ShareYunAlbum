package com.Domain.AlbumSys;

import com.ServiceUtils.DBConnection;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlbumManager {
    public static Album createAlbum(String album_name, String album_owner, String album_public, String album_description) {
        //获取相册创建时间以及id
        Date album_createtime = new Date();
        System.out.println(album_createtime);
        String album_id = getAlbumId(album_owner,album_createtime);
        Album album = new Album(album_id,album_name,album_owner,album_description,album_createtime,album_public);


        Connection connection = null;
        AlbumDAOImpl albumDAO = new AlbumDAOImpl();
        try {
            connection = DBConnection.getConnection();
            if(albumDAO.createAlbum(connection,album)){
                album.setStatus("100");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
    return album;
    }


    public static boolean deleteAlbum(String album_id)
    {
        Connection connection = null;
        AlbumDAOImpl albumDAO = new AlbumDAOImpl();
        boolean ret=false;
        try {
            connection = DBConnection.getConnection();
            if(albumDAO.deleteAlbum(connection,album_id)){
                ret=true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }

        return ret;
    }

    private static String getAlbumId(String album_owner, Date album_createtime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return album_owner+simpleDateFormat.format(album_createtime);
    }

    public static String[] getAlbumId(String user_id) {
        Connection connection = null;
        AlbumDAOImpl albumDAO = new AlbumDAOImpl();
        String [] album_id = null;
        try {
            connection = DBConnection.getConnection();
            List<Album> list = albumDAO.getAlbumByUserId(connection,user_id);
            int i = 0 ;
            for(Album album:list){
                album_id[i++] = album.getAlbum_id();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return  album_id;
    }
}
