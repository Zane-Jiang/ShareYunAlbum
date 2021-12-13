package com.Domain.AlbumSys;

import java.sql.Connection;

public interface AlbumDAO {
    public Boolean createAlbum(Connection conn,Album album);
    public Boolean deleteAlbum(Connection conn,String album_id);
}
