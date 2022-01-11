package com.Domain.AlbumSys;

import java.sql.Connection;
import java.util.List;

public interface AlbumDAO {
    public Boolean createAlbum(Connection conn,Album album);
    public Boolean deleteAlbum(Connection conn,String album_id);

    List<Album> getAlbumByUserId(Connection conn, String user_id);

    Long getSum(Connection connection);
}
