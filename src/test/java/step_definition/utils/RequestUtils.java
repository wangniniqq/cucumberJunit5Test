package step_definition.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static run.RunCucumberTest.*;

public  class RequestUtils {

//    public static String  server = "http://basex.uino.com";
//    public static String  authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.NTI5MDE2ZmY1OTc0NGZlMGM4YzQxY2NiYTliNTk1NmY.9_a6kwQAtD24tDKTW10wouO7KCUKSUkAD2wGg83wg6zJWsPar5eW-nX7tzIG6JVCxIMh1vkFu6-pb0WrOvLawA";
//    public static String  tenant = "QJDJXXV";
//    public static String  type = "multipart/form-data";

    public static JSONObject get(String path){
        String url =  server+path;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String reponse = EntityUtils.toString(entity);

//            System.out.println("===getReponse=="+reponse);
            JSONObject jsonObject = new JSONObject(reponse);
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static JSONObject getWithHeader(String path){
        String url =  server+path;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization",authorization);
        httpGet.setHeader("Tenant",tenant);


        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String reponse = EntityUtils.toString(entity);

//            System.out.println("===getReponse=="+reponse);
            JSONObject jsonObject = new JSONObject(reponse);
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject post(String path,JSONObject param){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url =  server+path;
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Authorization",authorization);
        httpPost.setHeader("Tenant",tenant);
        httpPost.setHeader("Content-Type",type);
//        httpPost.setHeader("Host","basex.uino.com");

        StringEntity paramEntity =  new StringEntity(param.toString(),"UTF-8");
        httpPost.setEntity(paramEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            String s = EntityUtils.toString(resEntity);
//            System.out.println("===postReponse=="+s);
            JSONObject res =  new JSONObject(s);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject uploadFile(String urlPath ,String filePath,ContentType contentType,JSONObject param){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url =server + urlPath;
        HttpPost httpPost = new HttpPost(url);
        File  file = new File(filePath);

        //设置请求头
        httpPost.setHeader("Authorization",authorization);
        httpPost.setHeader("Tenant",tenant);

        //设置请求体
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addBinaryBody("file",file, ContentType.IMAGE_PNG,file.getName());
//        builder.addTextBody("bucketName","图例文件",ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8));
        builder.addBinaryBody("file",file, contentType,file.getName());
        for (String key : param.keySet()){
            String val =  param.getString(key);
            builder.addTextBody(key,val,ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8));
        }

        //将请求体转化为HttpEntity
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);

        JSONObject response;
        try {
            //发送请求
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            //解析结果httpEntity->String
            HttpEntity respEntity = execute.getEntity();
            String resStr = EntityUtils.toString(respEntity);
            response =  new JSONObject(resStr);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


//    public static void main(String[] args) {
//    }
}
