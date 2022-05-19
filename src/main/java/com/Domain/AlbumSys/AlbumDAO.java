package com.Domain.AlbumSys;

import java.sql.Connection;
import java.util.List;

public interface AlbumDAO {
    //PS:图片的添加和删除在图片模块

    //创建，删除
    public Boolean createAlbum(Connection conn,Album album);
    //删除的时候级联
    public Boolean deleteAlbum(Connection conn,String album_id);
    //查找某个用户的所有相册
    public List<Album> getAlbumByUserId(Connection conn, String user_id);
    //查找某个相册
    public Album getAlbumById(Connection conn, String album_id);
    //相册总数
    Long getSum(Connection connection);
    //修改相册(前端在修改页面自动填入原来的数据)
    public Boolean modifyAlbum(Connection conn, String album_id,String album_name, String album_description, String album_public);
}
