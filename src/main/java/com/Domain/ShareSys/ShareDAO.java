package com.Domain.ShareSys;

import com.Domain.PictureSys.Picture;

import java.sql.Connection;
import java.util.List;

public interface ShareDAO {
    public List<Picture> getNewPictures(Connection conn);
}
