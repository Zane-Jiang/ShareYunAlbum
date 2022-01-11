package com.Domain.PictureSys;

import com.ServiceUtils.BaseDAO;

import java.sql.Connection;
import java.util.List;

public class PictureDAOImpl extends BaseDAO implements PictureDAO {

    @Override
    public boolean createPictrue(Connection connection, Picture picture) {
        String sql = "INSERT INTO pic (pic_id, pic_album, pic_uploadtime, pic_description, pic_blob) VALUES (?, ?, ?, ?, ?)";
        if(update(connection,sql,picture.getPic_id(),picture.getPic_album(),picture.getPic_uploadtime(), picture.getPic_description(),picture.getPic_blob()) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Picture> getPictureByAlbum(Connection conn, String album_id) {
        String sql = "SELECT * FROM pic WHERE pic_album = ?";
        System.out.println("DAO    +++"+album_id);
        return getForList(conn,Picture.class,sql,album_id);
    }

    @Override
    public Picture getPictureById(Connection conn, String pic_id) {
        String sql = "SELECT * FROM pic WHERE pic_id = ?";
        return  getInstance(conn,Picture.class,sql,pic_id);
    }

    @Override
    public boolean delete(Connection conn ,String pic_id) {
        String sql = "DELETE FROM pic WHERE pic_id = ? ";
        if(update(conn,sql,pic_id) == 1){
            return true;
        }
        return false;
    }
    @Override
    public Long getSum(Connection connection){
        String sql = "select count(pic_id) from  pic ";
        return getValue(connection,sql);
    }

    @Override
    public List<Picture> getAllPicture(Connection connection){
        String sql = "select * from  pic";
        return getForList(connection,Picture.class,sql);
    }
}
