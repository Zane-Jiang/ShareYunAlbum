package com.Domain.ShareSys;

import com.ServiceUtils.BaseDAO;

public class CommentDAOImpl  extends BaseDAO implements CommenDAO {
    @Override
    public boolean deleteComment(String comment) {
        return false;
    }

    @Override
    public Comment[] getCommentByPicture(String pic_id) {
        return new Comment[0];
    }

    @Override
    public Comment[] getComentByUser(String user_id) {
        return new Comment[0];
    }

    @Override
    public boolean createComment(String pic, String content, String owner) {
        return false;
    }
}
