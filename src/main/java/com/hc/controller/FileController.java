package com.hc.controller;

import com.hc.utils.QiNiuUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileController")
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        String res = QiNiuUtil.uploadMultipartFile(multipartFile, null);
        return res;
    }

}
