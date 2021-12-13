package com.ServiceUtils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

public class MessageService {

    public static boolean sendMessage(String[] params,String phone){
        int appid = 1400607998;
        String appkey = "c08d46c9b64026edd89765488311ce01";
        int templateId = 1235789;
        String  smsSign = "蒋泽学习资料整理";
        try{
            SmsSingleSender smsSingleSender = new SmsSingleSender(appid,appkey);
            SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86",phone,templateId,params,smsSign,"","");
            System.out.println(smsSingleSenderResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;

    }
}
