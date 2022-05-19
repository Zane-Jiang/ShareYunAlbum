package com.Domain.AlbumSys;

import com.ServiceUtils.BaseDAO;

import java.sql.Connection;
import java.util.List;

public class AlbumDAOImpl extends BaseDAO implements AlbumDAO  {

    @Override
    public Boolean createAlbum(Connection conn, Album album) {
        String sql = "INSERT INTO album (album_id, album_name, album_owner, album_description, album_createtime, album_public) VALUES (?, ?, ?, ?, ?, ?)";
        if(update(conn,sql,album.getAlbum_id(),album.getAlbum_name(),album.getAlbum_owner(),album.getAlbum_description(),album.getAlbum_createtime(),album.getAlbum_public() )== 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAlbum(Connection conn, String album_id) {
        //先删除图片
        String sql1="DELETE FROM pic WHERE pic_album = ?";
        //再删除相册
        String sql2= "DELETE FROM album WHERE album_id = ?";
        int up1=update(conn,sql1,album_id);
        int up2=update(conn,sql2,album_id);
        if(up2==1) return true;
        return false;
    }

    @Override
    public List<Album> getAlbumByUserId(Connection conn, String user_id){
        String sql = "SELECT * FROM album WHERE album_owner = ?";
        return getForList(conn, Album.class,sql,user_id);
    }

    @Override
    public Album getAlbumById(Connection conn, String album_id)
    {
        String sql = "SELECT * FROM album WHERE album_id = ?";
        List<Album> list=getForList(conn, Album.class, sql, album_id);
        if(list.size()==0) return null;
        else return list.get(0);
    }

    @Override
    public Long getSum(Connection connection){
        String sql = "select count(album_id) from  album ";
        return getValue(connection,sql);
    }

    @Override
    public Boolean modifyAlbum(Connection conn, String album_id,String album_name, String album_description, String album_public)
    {
        String sql="UPDATE album SET album_name = ? , album_description = ?, album_public = ? WHERE album_id = ?";
        if(update(conn,sql,album_name,album_description,album_public,album_id)==1)
        {
            return true;
        }
        return false;
    }
}
