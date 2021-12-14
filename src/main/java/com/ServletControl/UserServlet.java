package com.ServletControl;

import com.Domain.UserSys.User;
import com.Domain.UserSys.UserManager;
import com.ServiceUtils.MessageService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        ;
        resp.setHeader("Access-Control-Allow-Origin", "*"); //设置允许跨域访问
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Headers", "*");
//        if ("OPTIONS".equals(req.getMethod())) {
//            resp.setStatus(HttpStatus.SC_OK);
//        }

        PrintWriter respWriter = resp.getWriter();
        String option = req.getParameter("option");
        switch (option) {
            case "sendMessage": {
                String phone = req.getParameter("phone");
                System.out.println("发送短信到" + phone);
                String status = UserManager.sendMessage(phone);
                JSONObject data = new JSONObject();
                data.put("option", option);
                data.put("status", status);
                respWriter.print(data);
                respWriter.close();
            }
            break;
            case "checkAutoCode": {
//                System.out.println("验证短信验证码");
                String phone = req.getParameter("phone");
                String autoCode = req.getParameter("autoCode");
                String type = req.getParameter("type");
                User user= null;
                String status = "111";
                if(type.equals("signIn")){
                    if(autoCode.equals(MessageService.getParams(phone)[0])){
                        user = UserManager.getUserByPhone(phone);
                        status = "100";
                    }
                }else if(type.equals("signUp")){
                    if(autoCode.equals(MessageService.getParams(phone)[0])){
                        status = "101";
                        user = UserManager.createUserByPhone(phone);
                    }
                }

                JSONObject data = new JSONObject();
                data.put("option", option);
                data.put("autoCode", autoCode);
                System.out.println(status);
                System.out.println("servlet:++++++"+user);
                if(user != null){
                data.put("user_sex",user.getUser_sex());
                data.put("user_description",user.getUser_description());
                data.put("user_name",user.getUser_name());}


                data.put("status", status);
                respWriter.print(data);
                respWriter.close();
            }
            break;
            case "modifyInfo": {
                System.out.println("modifyInfo =========================");
                        System.out.println("modify public info");
                        String userPhone = req.getParameter("user_phone");
                        String userName = req.getParameter("user_name");
                        String userDesc = req.getParameter("user_description");
                        String userSex = req.getParameter("user_sex");

                        String status = UserManager.modifyUserPublicInfo(userPhone, userName, userDesc, userSex);
                        JSONObject data = new JSONObject();
                        data.put("option", option);
                        data.put("status", status);
                        respWriter.print(data);
                        respWriter.close();
                        System.out.println("modify complete=================");
                }
            break;
            case "deleteUser": {
               String phone = req.getParameter("phone");
               String autoCode = req.getParameter("autoCode");
               JSONObject data = new JSONObject();
               data.put("option",option);
               data.put("status",UserManager.deleteUserByPhone(phone,autoCode));
               respWriter.print(data);
               respWriter.close();
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

    }


}
