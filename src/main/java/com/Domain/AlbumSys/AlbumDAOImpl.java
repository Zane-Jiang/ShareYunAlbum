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
        String sql = "DELETE FROM album WHERE album_id = ?";
        if(update(conn,sql,album_id)==1)
        {
            return true;
        }
        return false;
    }

    public List<Album> getAlbumByUserId(Connection conn, String user_id){
        String sql = "SELECT * FROM album WHERE album_owner = ?";
        return getForList(conn, Album.class,sql,user_id);
    }
}
