package com.StudentManageSystem.base;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 短信http接口的java代码调用示例
* 基于Apache HttpClient 4.3
*
* @author songchao
* @since 2015-04-03
*/

public class JavaSmsApi {

    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模板发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    //绑定主叫、被叫关系的接口http地址
    private static String URI_SEND_BIND = "https://call.yunpian.com/v2/call/bind.json";

    //解绑主叫、被叫关系的接口http地址
    private static String URI_SEND_UNBIND = "https://call.yunpian.com/v2/call/unbind.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    public static void sent() throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yunpian.com)登录后获取
        String apikey = "740de1733cf9ec004d490c0ad0ec28cd";

        //修改为您要发送的手机号
        String mobile = "13191933031";

        /**************** 查账户信息调用示例 *****************/
       // System.out.println(JavaSmsApi.getUserInfo(apikey));

        /**************** 使用智能匹配模板接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        String text = " 恭喜您注册成功";
        System.out.println(JavaSmsApi.sendSms(apikey, text, mobile));
        
    }
    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        
        return post(URI_SEND_SMS, params);
    }
   public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
            try {
                HttpPost method = new HttpPost(url);
                if (paramsMap != null) {
                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                        NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                        paramList.add(pair);
                    }
                    method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
                }
                response = client.execute(method);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseText = EntityUtils.toString(entity, ENCODING);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return responseText;
        }
        
}