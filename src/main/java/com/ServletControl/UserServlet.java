package com.ServletControl;

import com.Domain.UserSys.User;
import com.Domain.UserSys.UserManager;
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
            case "signIn": {
                System.out.print("signin  ");
                String type = req.getParameter("type");
                switch (type) {
                    //通过账号密码登录

                    case "authenticateByPassword": {
                        System.out.println("authenticateByPassword");
                        String userID = req.getParameter("userID");
                        String password = req.getParameter("password");

                        User user = UserManager.signInByPassword(userID, password);
                        JSONObject data = new JSONObject();
                        data.put("option", option);
                        data.put("type", type);

                        data.put("status", user.getStatus());
                        if (user.isStatus().equals("100")) {
                            data.put("user_id", user.getUser_id());
                            data.put("user_name", user.getUser_name());
                            data.put("user_email", user.getUser_email());
                        }
                        respWriter.print(data);
                        respWriter.close();
                    }


                    break;
                    case "sendMessage": {
                        String phone = req.getParameter("phone");
                        System.out.println("发送短信到"+phone);
                        String status = UserManager.sendMessage(phone);
                        JSONObject data = new JSONObject();
                        data.put("option", option);
                        data.put("type", type);
                        data.put("status",status);
                        respWriter.print(data);
                        respWriter.close();
                    }
                    break;
                    case "checkAotuCode":
                        System.out.println("验证短信验证码");
                        break;
                    default:
                        break;
                }
            }
            break;
            case "signUp": {
                System.out.print("signup   ");
                String type = req.getParameter("type");
                switch (type) {
                    //通过账号密码注册账户
                    case "authenticateByPassword":
                        System.out.println("authenticateByPassword");
                        String userID = req.getParameter("userID");
                        String password = req.getParameter("password");
                        String status = UserManager.signUpByPassword(userID, password);
                        JSONObject data = new JSONObject();
                        data.put("option", option);
                        data.put("type", type);
                        data.put("status", status);
                        respWriter.print(data);
                        respWriter.close();
                        break;
                    case "sendMessage":
                        System.out.println("申请发送短信");
                        break;
                    case "checkAotuCode":
                        System.out.println("验证短信验证码");
                        break;
                }
            }
            break;
            case "modifyUser": {
                System.out.println("modifyUser =========================");
                String type = req.getParameter("type");
                switch (type) {
                    //非必须信息
                    case "1": {
                        System.out.println("modify public info");
                        String userID = req.getParameter("userID");
                        String userName = req.getParameter("UserName");
                        String userDesc = req.getParameter("UserDescription");
                        String userSex = req.getParameter("UserSex");
                        String status = UserManager.modifyUserPublicInfo(userID, userName, userDesc, userSex);
                        JSONObject data = new JSONObject();
                        data.put("option", option);
                        data.put("type", type);
                        data.put("status", status);
                        respWriter.print(data);
                        respWriter.close();
                        System.out.println("modify complete=================");
                    }
                    //修改需要验证的信息
                    case "2": {
                        break;
                    }
                    //头像
                    case "3": {
                        break;
                    }
                }
            }
            break;
            case "deleteUser": {
                System.out.println("deleteUser =========================");
                String type = req.getParameter("type");
                switch (type) {
                    case "authenticateByPassword": {
                        System.out.println("authenticateByPassword");

                        String userID = req.getParameter("userID");
                        String password = req.getParameter("password");
                        String status = UserManager.deleteUserByPassword(userID, password);
                        JSONObject data = new JSONObject();
                        data.put("option", option);
                        data.put("type", type);
                        data.put("status", status);
                        respWriter.print(data);
                        respWriter.close();
                        System.out.println("delete complete=================");
                    }
                }

            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

    }


}
