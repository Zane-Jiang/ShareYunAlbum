package com.Domain.ShareSys;
import com.ServiceUtils.BaseDAO;

import com.Domain.PictureSys.Picture;

import java.sql.Connection;
import java.util.List;

public class ShareDAOImpl extends BaseDAO implements ShareDAO{
    @Override
    public List<Picture> getNewPictures(Connection conn)
    {
        String sql = "SELECT * FROM pic";
        return getForList(conn,Picture.class,sql);
    }
}
