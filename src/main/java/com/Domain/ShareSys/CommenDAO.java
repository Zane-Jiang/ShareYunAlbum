package com.Domain.ShareSys;

public interface CommenDAO {
    public   boolean deleteComment(String comment);
    public  Comment[] getCommentByPicture(String pic_id);
    public Comment[] getComentByUser(String user_id);
    public  boolean createComment(String pic,String content,String owner) ;



}
