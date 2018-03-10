package com.sobey.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.dysmsapi.transform.v20170525.SendSmsResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sobey.config.AppConfig;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SmsSendMessage {

    public static SendSmsResponse sendSms(String phone , String param , String templateCode) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AppConfig.AL_SMS_ACCESSKEYID, AppConfig.AL_SMS_ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AppConfig.AL_SMS_PRODUCT, AppConfig.AL_SMS_DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(AppConfig.AL_SMS_SIGN);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(param);
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    public static void main(String[] args) throws ClientException, InterruptedException {
        //发短信
//        SendSmsResponse response = sendSms("13088094976","{\"name\":\"Tom\", \"code\":\"123\"}");
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());
        StringBuffer buffer = new StringBuffer();
        for(int i = 0 ; i < 6 ; i++){
            buffer.append((int) (Math.random() * 10));
        }
        System.out.println(buffer.toString());
    }
}
