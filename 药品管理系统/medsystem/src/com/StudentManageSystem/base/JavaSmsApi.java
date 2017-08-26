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
* ����http�ӿڵ�java�������ʾ��
* ����Apache HttpClient 4.3
*
* @author songchao
* @since 2015-04-03
*/

public class JavaSmsApi {

    //���˻���Ϣ��http��ַ
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //����ƥ��ģ�巢�ͽӿڵ�http��ַ
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //ģ�巢�ͽӿڵ�http��ַ
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //����������֤��ӿڵ�http��ַ
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    //�����С����й�ϵ�Ľӿ�http��ַ
    private static String URI_SEND_BIND = "https://call.yunpian.com/v2/call/bind.json";

    //������С����й�ϵ�Ľӿ�http��ַ
    private static String URI_SEND_UNBIND = "https://call.yunpian.com/v2/call/unbind.json";

    //�����ʽ�����ͱ����ʽͳһ��UTF-8
    private static String ENCODING = "UTF-8";

    public static void sent() throws IOException, URISyntaxException {

        //�޸�Ϊ����apikey.apikey���ڹ�����http://www.yunpian.com)��¼���ȡ
        String apikey = "740de1733cf9ec004d490c0ad0ec28cd";

        //�޸�Ϊ��Ҫ���͵��ֻ���
        String mobile = "13191933031";

        /**************** ���˻���Ϣ����ʾ�� *****************/
       // System.out.println(JavaSmsApi.getUserInfo(apikey));

        /**************** ʹ������ƥ��ģ��ӿڷ�����(�Ƽ�) *****************/
        //������Ҫ���͵�����(���ݱ����ĳ��ģ��ƥ�䡣��������ƥ�����ϵͳ�ṩ��1��ģ�壩
        String text = " ��ϲ��ע��ɹ�";
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