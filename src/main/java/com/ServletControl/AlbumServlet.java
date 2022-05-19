package com.ServletControl;

import com.Domain.AlbumSys.Album;
import com.Domain.AlbumSys.AlbumManager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "/albumServlet")
public class AlbumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin","*");

        PrintWriter respWriter = resp.getWriter();
        String option  =  req.getParameter("option");
        System.out.println(option);

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
            System.out.println("create compelte ====================");

            }break;

            case "modify":{
                String status;
                System.out.println("modify album ===================");
                String album_id = req.getParameter("album_id");
                String album_name = req.getParameter("album_name");
                String album_description = req.getParameter("album_description");
                String album_public = req.getParameter("album_public");
                if(AlbumManager.modifyAlbum(album_id,album_name,album_description,album_public))
                    status="100";
                else
                    status="101";
                JSONObject data = new JSONObject();
                data.put("option",option);
                data.put("status",status);
                respWriter.print(data);
                respWriter.close();
                System.out.println("modify complete ====================");
            }break;

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
            }break;
            case "getAlbumId":{
                String user_phone = req.getParameter("user_phone");
                List<Album> album_id = AlbumManager.getAlbumIdByUserID(user_phone);
                JSONArray array = new JSONArray();
                JSONObject obj= new JSONObject();

                for(Album album:album_id)
                {
                    obj= new JSONObject();
                    System.out.println("   aaaa"+album_id);
                    obj.put("album_id",album.getAlbum_id());
                    obj.put("album_name",album.getAlbum_name());
                    obj.put("album_owner",album.getAlbum_owner());
                    obj.put("album_description",album.getAlbum_description());
                    obj.put("album_createtime",album.getAlbum_createtime());
                    obj.put("album_public",album.getAlbum_public());
                    array.add(obj);
                }

                System.out.println(array.toString()+"aaa");
                respWriter.print(array);
                respWriter.close();

            }break;
            //前端发相册ID，查询得到相册信息，然后再进行修改
            case "getAlbumInfo":{
                String album_id = req.getParameter("album_id");
                Album album=AlbumManager.getAlbumById(album_id);
                JSONObject data=new JSONObject();
                //前端先检查相册状态，101表示不存在相册
                if(album==null)
                {
                    data.put("album_status","101");
                    break;
                }
                data.put("album_status","100");
                data.put("album_id",album.getAlbum_id());
                data.put("album_name",album.getAlbum_name());
                data.put("album_owner",album.getAlbum_owner());
                data.put("album_description",album.getAlbum_description());
                data.put("album_createtime",album.getAlbum_createtime());
                data.put("album_public",album.getAlbum_public());

                respWriter.print(data);
                respWriter.close();
                break;
            }
            default:
                System.out.println("option error");
        }

    }
}
