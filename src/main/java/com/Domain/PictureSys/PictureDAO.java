package com.Domain.PictureSys;

import java.sql.Connection;
import java.util.List;

public interface PictureDAO {

    boolean createPicture(Connection connection, Picture picture);
    List<Picture> getPictureByAlbum(Connection conn, String album_id);
    Picture getPictureById(Connection conn, String pic_id);

    boolean delete(Connection conn, String pic_id);

    Long getSum(Connection connection);

    List<Picture> getAllPicture(Connection connection);

    //boolean modifyPicDesc(Connection conn, String pic_id);

    String getUserIdByPicId(Connection connection, String pic_id);
}
