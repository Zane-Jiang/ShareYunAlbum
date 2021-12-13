package com.ServiceUtils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;


/**
 * 此类中可以维护一张存在时效性的验证码表，此处简化，简化验证码的生成为固定验证码
 */
public class MessageService {

    public static boolean sendMessage(String phone){
        boolean success = false;
        String[] params = getParams(phone);
        int appid = 1400607998;
        String appkey = "c08d46c9b64026edd89765488311ce01";
        int templateId = 1235789;
        String  smsSign = "蒋泽学习资料整理";
        try{
            SmsSingleSender smsSingleSender = new SmsSingleSender(appid,appkey);
            SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86",phone,templateId,params,smsSign,"","");
            System.out.println(smsSingleSenderResult);
            if(smsSingleSenderResult.result==0){
                success = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }




    private  static String[] getParams(String phone){
        String [] params = {""};
        String Code =null;
//         Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        Code = simpleDateFormat.format(date).substring(8,11);
        Code ="784"+phone.substring(6,9);
        String  effectiveTime = "3";
        params[0] = Code;
//        params[1] = effectiveTime;
        return  params;
    }
}
