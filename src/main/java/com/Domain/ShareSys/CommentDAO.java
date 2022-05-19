package com.Domain.ShareSys;

import java.sql.Connection;
import java.util.List;

public interface CommentDAO {
    public   boolean deleteComment(Connection conn,String comment_id);
    public List<Comment> getCommentByPicture(Connection conn, String pic_id);
    public List<Comment> getCommentByUser(Connection conn,String user_id);
    public Comment getCommentById(Connection conn,String comment_id);
    public  Comment createComment(Connection conn,String id,String pic,String content,String owner);
}
