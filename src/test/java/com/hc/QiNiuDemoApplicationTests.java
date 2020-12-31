package com.hc;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class QiNiuDemoApplicationTests {

    /**
     * 获取上传token
     */
    @Test
    void getUpToken() {
        String accessKey = "AW8tBsAy-uXXdYCO6E0ZDWMYJhY5HOEBboD_MtcH";
        String secretKey = "kezs7Q_FlHIvwxx6nnslmhoVlh8kqStSarpISjmF";
        String bucket = "hcstore";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
    }

    /**
     * 获取上传凭证
     */
    @Test
    void get() {
        String accessKey = "AW8tBsAy-uXXdYCO6E0ZDWMYJhY5HOEBboD_MtcH";
        String secretKey = "kezs7Q_FlHIvwxx6nnslmhoVlh8kqStSarpISjmF";
        String bucket = "hcstore";
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        System.out.println(upToken);
    }

    @Test
    public void update1() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        String accessKey = "AW8tBsAy-uXXdYCO6E0ZDWMYJhY5HOEBboD_MtcH";
        String secretKey = "kezs7Q_FlHIvwxx6nnslmhoVlh8kqStSarpISjmF";
        String bucket = "hcstore";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            File file = new File("E:\\我的文档\\重要文档\\照片\\lyl.jpg");
            FileInputStream fis = new FileInputStream(file);
            Response response = uploadManager.put(fis, accessKey, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {

        }
    }


}
