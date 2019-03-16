package com.business.hall.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018/2/27 15:33
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UploadHttpClientUtil {

    public static String post(String serverUrl, String fileParamName, File file, Map<String, String> params)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(serverUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 上传的文件
        builder.addBinaryBody(fileParamName, file);
        // 设置其他参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
        }
        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpPost);
        if (null == response || response.getStatusLine() == null) {
            System.out.println("Post Request For Url[{}] is not ok. Response is null");
            System.out.println(serverUrl);
            return null;
        } else if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            System.out.println("Post Request For Url[{}] is not ok. Response Status Code is {}");
            System.out.println(serverUrl);
            return null;
        }
        return EntityUtils.toString(response.getEntity());
    }

    public static void main(String[] args) {
        String url = "http://58.51.90.231:8085/core-file/api/fileUpload/singleImg";
        String fileUrl = "D:\\upload\\Tulips.jpg";
        Map<String, String> map = new HashMap<String, String>();
        map.put("operatorJobNumber", "102800038");
        map.put("operatorEmail", "pengyuqian@aimsen.com1");
        map.put("realed", "1");
        map.put("resumeEducationBackgroundId", "6045");
        try {
            System.out.println(UploadHttpClientUtil.post(url, "file", new File(fileUrl), map));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}