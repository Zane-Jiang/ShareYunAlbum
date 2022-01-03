package com.ServiceUtils.MessageService;


import com.ServiceUtils.BaseDAO;
import com.ServiceUtils.DBConnection;

import java.sql.Connection;
import java.util.Date;
import java.util.Random;


public  class AutoCodeImpl extends BaseDAO {
    public AutoCode create(String phone){
        Connection connection = null;
        String code = "";
        Random random = new Random();
        for(int i = 0; i < 6 ; i++){
            code+= Integer.toString(random.nextInt(10));
        }
        System.out.println(code);

        AutoCode autoCode = new AutoCode(phone,code,new Date().getTime());

        try {
            connection = DBConnection.getConnection();
            System.out.println(autoCode.getCode());
            String sql = "INSERT INTO autocode (phone,code,createTime,status) VALUES (?,?,?,?)";
         update(connection,sql,phone,autoCode.getCode(),autoCode.getCreateTime(),autoCode.getStatus()) ;
            System.out.println("写入数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeResource(connection,null);
        }
        return  autoCode;
    }

    /**
     * 找到且超时 status = 1
     * autoCode = null  表示没有找到
     * 找到且有效 status = 0
     * @param phone
     * @return
     */
    public  AutoCode find(String phone){
        Connection connection= null;
        Date date = new Date();
        AutoCode autoCode = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * FROM autocode WHERE phone = ? ";
            autoCode = getInstance(connection, AutoCode.class, sql, phone);
            if(autoCode!=null){
            System.out.println("datatime   "+date.toString()+"    "+date.getTime());
            System.out.println("autoCreateTIme  "+autoCode.getCreateTime()+"    "+autoCode.getCreateTime());
            System.out.println("时间差：  "+(date.getTime()-autoCode.getCreateTime()));
            System.out.println("比较值"+1*60*1000);
            if((date.getTime()-autoCode.getCreateTime())>1*60*1000){
                autoCode.setStatus("1");
            }
            String sql1  = "DELETE FROM autocode WHERE phone  = ? ";
            update(connection,sql1,phone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeResource(connection,null);
        }
        return  autoCode;
    }



}







