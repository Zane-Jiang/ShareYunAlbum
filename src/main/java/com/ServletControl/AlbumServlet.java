package com.ServletControl;

import com.Domain.AlbumSys.Album;
import com.Domain.AlbumSys.AlbumManager;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/albumServlet")
public class AlbumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin","*");

        PrintWriter respWriter = resp.getWriter();
        String option  =  req.getParameter("option");

        switch (option){
            case "create":{
                System.out.println("create album ===================");
                String album_name = req.getParameter("album_name");
                String album_owner = req.getParameter("album_owner");
                String album_description = req.getParameter("album_description");
                String album_public = req.getParameter("album_public");
                Album album = AlbumManager.createAlbum(album_name,album_owner,
                        album_public,album_description);
                JSONObject data = new JSONObject();
                data.put("option",option);
                data.put("status",album.getStatus());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String createTime =simpleDateFormat.format(album
                .getAlbum_createtime());
                data.put("album_createtime",createTime);
                data.put("album_id",album.getAlbum_id());
                respWriter.print(data);
                respWriter.close();
                System.out.println("create compelte ====================");}
                break;
            case "modify":
                break;
            case "delete":
            {
                System.out.println("delete album ===================");
                String album_id = req.getParameter("album_id");
                String status;
                if(AlbumManager.deleteAlbum(album_id))
                    status="100";
                else
                    status="101";
                JSONObject data = new JSONObject();
                data.put("status", status);
                data.put("option",option);
                respWriter.print(data);
                respWriter.close();
                System.out.println("delete compelte ====================");
                break;
            }
            case "getAlbumId":{
                String user_id = req.getParameter("user_id");
                String [] album_id = AlbumManager.getAlbumId(user_id);
                JSONObject data = new JSONObject();
                data.put("option",option);
                data.put("album_id",album_id);
            }

        }

    }
}
