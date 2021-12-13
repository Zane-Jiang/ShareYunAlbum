package com.ServletControl;

import com.Domain.PictureSys.Picture;
import com.Domain.PictureSys.PictureManager;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.List;

@WebServlet(urlPatterns = "/pictureServlet")
public class PictureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("post  =======================");

        //上传时的文件时分段的
        if(ServletFileUpload.isMultipartContent(req)){
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            System.out.println("分段");
            //分段格式时读取参数
            String pic_album = null;
            String pic_description= null;
            String option = null;
            InputStream pic_blob_stream = null;
            try {
                List<FileItem>  list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem:list){
                    if(fileItem.isFormField()){
                        switch (fileItem.getFieldName()){
                            case "option":option = fileItem.getString("UTF-8");break;
                            case "pic_album":pic_album = fileItem.getString("UTF-8");break;
                            case "pic_description":pic_description = fileItem.getString("UTF-8");break;
                        }
                    }else{
                        if(fileItem.getFieldName().equals("pic_blob"));
//                        pic_blob_stream = (FileInputStream) fileItem;
                        pic_blob_stream = fileItem.getInputStream();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            将接收到的inputStream转化为blob
            Blob pic_blob = null;
//            pic_blob = SQLInput

//                分段格式时处理图片上传
            switch (option){
                case "upload":{
                    PrintWriter respWriter = resp.getWriter();


                    Picture picture = PictureManager.upload(pic_album,pic_description,pic_blob);
                    JSONObject data = new JSONObject();
                    data.put("status",picture.getStatus());
                    data.put("pic_createtime",picture.getPic_uploadtime());
                    data.put("pic_id",picture.getPic_id());
                    respWriter.print(data);
                    respWriter.close();
                }break;
            }
        }
        else{
            PrintWriter respWriter = resp.getWriter();
            String option = req.getParameter("option");
            System.out.println("未分段====="+option);
            switch (option){
                case "getPictureID":{
                    String album_id=  req.getParameter("pic_album");
                    System.out.println(album_id);
                    String []  picture_id = PictureManager.getPictureId(album_id);
//                   for(String s:picture_id){
//                       System.out.println(s);
//                   }

                   JSONObject data = new JSONObject();
                    data.put("option",option);
                    data.put("status","100");
                    data.put("pic_id",picture_id);

                    respWriter.print(data);
                    respWriter.close();
                }
                   break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        if (option.equals( "download")){
            String  pic_id = req.getParameter("pic_id");
            InputStream resourceAsStream = PictureManager.download(pic_id);
            OutputStream outputStream = resp.getOutputStream();
            IOUtils.copy(resourceAsStream, outputStream);
            resourceAsStream.close();
            outputStream.close();
        }
    }
}
