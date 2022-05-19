package com.Domain.ShareSys;

import com.Domain.PictureSys.Picture;
import com.ServiceUtils.DBConnection;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShareManager {
    public  static boolean deleteComment(String comment){
        Connection connection=null;
        CommentDAOImpl commentDAO=new CommentDAOImpl();
        boolean ret=false;
        try{
            connection= DBConnection.getConnection();
            ret=commentDAO.deleteComment(connection,comment);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return ret;
    }
    public static  List<Comment> getCommentByPicture(String pic_id){
        Connection connection=null;
        CommentDAOImpl commentDAO=new CommentDAOImpl();
        List<Comment> list=null;
        try{
            connection=DBConnection.getConnection();
            list=commentDAO.getCommentByPicture(connection,pic_id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return list;
    }
    public static List<Comment> getCommentByUser(String user_id){
        Connection connection=null;
        CommentDAOImpl commentDAO=new CommentDAOImpl();
        List<Comment> list=null;
        try{
            connection=DBConnection.getConnection();
            list=commentDAO.getCommentByUser(connection,user_id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return list;
    }
    public  static boolean createComment(String pic,String content,String owner) {
        Connection connection=null;
        CommentDAOImpl commentDAO=new CommentDAOImpl();
        Comment comment=null;
        boolean ret=false;
        String com_id=getCommentId(owner);
        try{
            connection=DBConnection.getConnection();
            comment=commentDAO.createComment(connection,com_id,content,pic,owner);
            if(comment!=null) ret=true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return ret;
    }

    public static String getCommentId(String comment_owner)
    {
        Date time=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return comment_owner+simpleDateFormat.format(time);
    }

    public static List<Picture> getNewPictures(){
        Connection connection=null;
        ShareDAOImpl shareDAO=new ShareDAOImpl();
        List<Picture> list=null;
        try{
            connection=DBConnection.getConnection();
            list=shareDAO.getNewPictures(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return list;
    }

    public static Comment getCommentById(String comment_Id)
    {
        Connection connection=null;
        CommentDAOImpl commentDAO=new CommentDAOImpl();
        Comment comment=null;

        try{
            connection=DBConnection.getConnection();
            comment=commentDAO.getCommentById(connection,comment_Id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return comment;
    }
}
