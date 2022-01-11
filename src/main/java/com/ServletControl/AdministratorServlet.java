package com.ServletControl;

import com.Domain.AdministratorSys.AdministratorMagager;
import com.Domain.AlbumSys.AlbumManager;
import com.Domain.PictureSys.PictureManager;
import com.Domain.UserSys.User;
import com.Domain.UserSys.UserManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

@WebServlet(urlPatterns = "/administratorServlet")
public class AdministratorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Origin", "*"); //设置允许跨域访问
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        PrintWriter respWriter = resp.getWriter();

        String option = req.getParameter("option");
        switch (option) {
            case "signIn": {
                String admin_id = req.getParameter("admin_id");
                String password = req.getParameter("password");
                System.out.println(" admin_id" + "   " + admin_id);
                System.out.println("password     " + password);
                String status = AdministratorMagager.signInBypassword(admin_id, password);
                JSONObject data = new JSONObject();
                System.out.println("status     " + status);
                data.put("option", "signIn");
                data.put("status", status);
                respWriter.print(data);
                respWriter.close();
            }
            break;
            case "showSum": {
                System.out.println("showSum");
                JSONObject date = new JSONObject();
                date.put("pictureSum", PictureManager.getPicteureSum());
                date.put("albumSum", AlbumManager.getAlbumSum());
                date.put("userSum", UserManager.getUserSum());
                respWriter.print(date);
                respWriter.close();

            }
            break;


            case "showAllUser": {
                System.out.println("showAllUser");
                JSONObject date = new JSONObject();
                date.put("userPhones", UserManager.getUserPhones());
                respWriter.print(date);
                respWriter.close();
            }
            break;
            case "getUserInfo": {
                String phone = req.getParameter("phone");
                User user= UserManager.getUserByPhone(phone);
                JSONObject date = new JSONObject();
                date = (JSONObject) JSON.toJSON(user);
                respWriter.print(date);
                respWriter.close();
            }


            break;
//            totest
            case "deleteUser": {
                String phone = req.getParameter("phone");
                System.out.println("deleteUser");
                JSONObject date = new JSONObject();
//                不考虑并发问题，比如，之前是有的，但是某一瞬间用户删掉了，这是会造成系统崩溃




                if (UserManager.deleteUserByPhoneWithoutCode(phone))
                    date.put("status", "100");
                else
                    date.put("status", "101");
                respWriter.print(date);
                respWriter.close();
            }
            break;
//            picture
            case "getAllPictureId": {
                System.out.println("getAllPicture");
                JSONObject date = new JSONObject();
                date.put("userPhones", PictureManager.getAllPicture());
                respWriter.print(date);
                respWriter.close();
            }
            break;

            case "getPictureInfo": {
                System.out.println("getPictureInfo");
                JSONObject date = new JSONObject();
                date.put("userPhones", PictureManager.getAllPicture());
                respWriter.print(date);
                respWriter.close();
            }
            break;

            case "autoReviewPicture": {
                String pic_id = req.getParameter("pic_id");
                respWriter.print(AdministratorMagager.autoReviewPicture(pic_id));
                respWriter.close();
            }
            break;

            case "download": {

            }
            break;
            case "deletePicture": {
                String pic_id = req.getParameter("pic_id");
                JSONObject date = new JSONObject();
                if (PictureManager.deletePicture(pic_id)) {
                    date.put("status", "100");
                } else {
                    date.put("status", "101");
                }
                respWriter.print(date);
                respWriter.close();
            }
            break;

            default:
                System.out.println("option error!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Content-Disposition", "attachment;fileName=1.png");
        resp.setHeader("Access-Control-Allow-Origin", "*"); //设置允许跨域访问
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Headers", "*");
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
