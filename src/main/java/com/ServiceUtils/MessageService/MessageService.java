package com.ServiceUtils.MessageService;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;


/**
 * 此类中可以维护一张存在时效性的验证码表，此处简化，简化验证码的生成为固定验证码
 */
public class MessageService {

    public static boolean sendMessage(String phone) {
        boolean success = false;
        String[] params = createParams(phone);
        int appid = 1400607998;
        String appkey = "c08d46c9b64026edd89765488311ce01";
        int templateId = 1235789;
        String smsSign = "蒋泽学习资料整理";
        try {
            SmsSingleSender smsSingleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
            System.out.println(smsSingleSenderResult);
            if (smsSingleSenderResult.result == 0) {
                success = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public static String[] createParams(String phone) {
        String[] params = {""};
        AutoCodeImpl autoCodeImpl = new AutoCodeImpl();
        AutoCode autoCode = autoCodeImpl.create(phone);
        params[0] = autoCode.getCode();
        return params;
    }


    public static String[] getParams(String phone) {
        String[] params = {""};
        AutoCode autoCode = null;
        AutoCodeImpl autoCodeImpl = new AutoCodeImpl();
        autoCode = autoCodeImpl.find(phone);
        if (autoCode != null) {
            if (autoCode.getStatus().equals("0")) {
                params[0] = autoCode.getCode();
//            找到且有效
            } else {
//            找到且超时
                params[0] = "timeout";
            }
        } else {
//            等于null，没有找到对应的手机号，验证码或者手机号错误
            params[0] = "inavailable";
        }
        return params;
    }
}
