package com.hc.utils;

import com.hc.config.QiNiuConfig;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QiNiuUtilTest {

    @Test
    void upload1() {
        String res = QiNiuUtil.upload("E:\\我的文档\\重要文档\\照片\\DSC_0645.jpg", "aaaa");
        System.out.println(res);
    }

    @Test
    void upload2() {
        String res = QiNiuUtil.upload("E:\\我的文档\\重要文档\\照片\\DSC_0645.jpg", null);
        System.out.println(res);
    }


    @Test
    void fileUrl() throws UnsupportedEncodingException {
        String url = QiNiuUtil.fileUrl("asdf");
        System.out.println(url);
    }

    @Test
    void uploadMultipartFile() {

    }

    @Test
    void getAuth() {
        Auth auth = QiNiuUtil.getAuth();
        System.out.println(auth);
    }

    @Test
    void getUploadManager() {
        BucketManager bucketManager = QiNiuUtil.getBucketManager();
        System.out.println(bucketManager);
    }

    @Test
    void getFileInfo() {
        FileInfo[] infos = QiNiuUtil.getFileInfo();
        for (FileInfo item : infos) {
            System.out.println(item.key);
            System.out.println(item.hash);
            System.out.println(item.fsize);
            System.out.println(item.mimeType);
            System.out.println(item.putTime);
            System.out.println(item.endUser);
            System.out.println();
        }
    }

    @Test
    void getBucketsInfo() {
        String[] buckets = QiNiuUtil.getBucketsInfo();
        for (String bucket : buckets) {
            System.out.println(bucket);
        }
    }

    @Test
    void deletes() {
        Map<String, String> map = QiNiuUtil.deletes("bbbb", "asdf");
        map.forEach((k, v) -> System.out.println(k + "\t" + v));
    }

    @Test
    void delete() {
        boolean res = QiNiuUtil.delete("FvSQe8DCKNTZcoc-pqmmRRVfn3ba");
        System.out.println(res);
    }
}
