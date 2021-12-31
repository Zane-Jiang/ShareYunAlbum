package com.Domain.PictureSys;

import java.sql.Connection;
import java.util.List;

public interface PictureDAO {

    boolean createPictrue(Connection connection, Picture picture);
    List<Picture> getPictureByAlbum(Connection conn, String album_id);
    Picture getPictureById(Connection conn, String pic_id);


    boolean delete(Connection conn, String pic_id);
}
