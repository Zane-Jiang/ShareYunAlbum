package com.Domain.ShareSys;

import com.ServiceUtils.BaseDAO;

import java.sql.Connection;
import java.util.List;

public class CommentDAOImpl  extends BaseDAO implements CommentDAO {
    @Override
    public boolean deleteComment(Connection conn, String comment_id) {
        String sql="DELETE FROM comment WHERE comment_id = ?";
        if(update(conn,sql,comment_id)==1){
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> getCommentByPicture(Connection conn, String pic_id) {
        String sql="SELECT * FROM comment WHERE comment_pic = ?";
        return getForList(conn, Comment.class,sql,pic_id);
    }

    @Override
    public List<Comment> getCommentByUser(Connection conn,String user_id) {
        String sql="SELECT * FROM comment WHERE comment_owner = ?";
        return getForList(conn,Comment.class,sql,user_id);
    }

    @Override
    public Comment createComment(Connection conn,String comment_id, String comment_content, String comment_pic, String comment_owner) {
        Comment comment=new Comment(comment_id,comment_content,comment_pic,comment_owner);
        String sql="INSERT INTO comment (comment_id , comment_content, comment_pic, comment_owner) VALUES (?,?,?,?)";
        if(update(conn,sql,comment_id,comment_content,comment_pic,comment_owner)==1){
            return comment;
        }
        return null;
    }

    @Override
    public Comment getCommentById(Connection conn,String comment_id)
    {
        String sql="SELECT * FROM comment WHERE comment_id = ?";
        List<Comment> list= getForList(conn,Comment.class,sql,comment_id);
        if(list.size()==0) return null;
        else return list.get(0);
    }
}
