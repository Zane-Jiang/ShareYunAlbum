package com.ServletControl;

import com.Domain.AdministratorSys.AdministratorMagager;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/AdministratorServlet")
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
        switch (option){
            case "signIn":{
                String admin_id = req.getParameter("admin_id");
                String password = req.getParameter("password");
                String status = AdministratorMagager.signInBypassword(admin_id,password);
                JSONObject data = new JSONObject();
                data.put("option","signIn");
                data.put("status",status);
                respWriter.print(data);
                respWriter.close();
            }break;
            case "":
                

        }


    }
}
