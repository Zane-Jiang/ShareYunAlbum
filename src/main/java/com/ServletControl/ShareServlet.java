package com.ServletControl;

import com.Domain.PictureSys.Picture;
import com.Domain.PictureSys.PictureManager;
import com.Domain.ShareSys.Comment;
import com.Domain.ShareSys.ShareManager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/shareServlet")
public class ShareServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Origin", "*"); //设置允许跨域访问
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        //分支之前的一大堆处理。。

        PrintWriter respWriter = resp.getWriter();
        String option  =  req.getParameter("option");
        //分支
        switch(option){
            case "create":{
                System.out.println("create comment ===================");
                String comment_content=req.getParameter("comment_content");
                String comment_pic=req.getParameter("comment_pic");
                String comment_owner=req.getParameter("comment_owner");
                boolean success= ShareManager.createComment(comment_pic,comment_content,comment_owner);
                JSONObject data = new JSONObject();
                String status;
                if(success) status="100";
                else status="101";
                data.put("status",status);
                data.put("option",option);
                respWriter.print(data);
                respWriter.close();
                System.out.println("create complete ====================");
                break;
            }
            case "delete":{
                System.out.println("delete comment ===================");
                String comment_id=req.getParameter("comment_id");
                String status;
                if(ShareManager.deleteComment(comment_id))
                    status="100";
                else
                    status="101";

                JSONObject data = new JSONObject();
                data.put("status",status);
                data.put("option",option);
                respWriter.print(data);
                respWriter.close();
                System.out.println("delete complete ====================");
                break;
            }

            case "getCommentById":{
                //String comment
            }

            case "getCommentByPicture":{
                String pic_id=req.getParameter("pic_id");
                List<Comment> list=ShareManager.getCommentByPicture(pic_id);
                JSONArray array = new JSONArray();
                JSONObject obj= new JSONObject();

                obj.put("option",option);
                array.add(obj);

                for(Comment comment:list)
                {
                    obj=new JSONObject();
                    obj.put("comment_id",comment.getID());
                    obj.put("comment_content",comment.getContent());
                    obj.put("comment_pic",comment.getPic());
                    obj.put("comment_owner",comment.getOwner());
                    array.add(obj);
                }

                respWriter.print(array);
                respWriter.close();
            }
            case "getCommentByUser":{
                String user_id=req.getParameter("user_id");
                List<Comment> list=ShareManager.getCommentByUser(user_id);
                JSONArray array = new JSONArray();
                JSONObject obj= new JSONObject();

                obj.put("option",option);
                array.add(obj);

                for(Comment comment:list)
                {
                    obj=new JSONObject();
                    obj.put("comment_id",comment.getID());
                    obj.put("comment_content",comment.getContent());
                    obj.put("comment_pic",comment.getPic());
                    obj.put("comment_owner",comment.getOwner());
                    array.add(obj);

                }

                respWriter.print(array);
                respWriter.close();
            }break;
            case "refresh":{
                List<Picture> list=ShareManager.getNewPictures();
                JSONArray array = new JSONArray();
                JSONObject obj= new JSONObject();

                //需要传图片ID和用户ID
                for(Picture picture:list)
                {
                    String pic_id=picture.getPic_id();
                    obj=new JSONObject();
                    obj.put("pic_id",pic_id);
                    obj.put("pic_user", PictureManager.getPic_user(pic_id));
                    array.add(obj);
                }

                System.out.println(array);
                respWriter.print(array);
                respWriter.close();
                break;
            }

        }
    }
}
