package com.xuzi.share.utils;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class FileUtil {
    @Value("${qiniu.key.access}")
    private String accessKey;

    @Value("${qiniu.key.secret}")
    private String secretKey;

    @Value("${qiniu.bucket.file.name}")
    private String headerBucketName;

    @Value("${quniu.bucket.file.url}")
    private String headerBucketUrl;
    public Model fileup(Model model){
        // 上传文件名称
        String fileName = ShareUtil.generateUUID();
        String fileName2 = ShareUtil.generateUUID();
        String fileName3 = ShareUtil.generateUUID();
        String fileName4 = ShareUtil.generateUUID();
        String fileName5 = ShareUtil.generateUUID();
        String fileName6 = ShareUtil.generateUUID();
        String fileName7 = ShareUtil.generateUUID();
        // 设置响应信息
        StringMap policy = new StringMap();
        policy.put("returnBody", ShareUtil.getJSONString(0));
        // 生成上传凭证
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(headerBucketName, fileName, 3600, policy);
        String uploadToken2 = auth.uploadToken(headerBucketName, fileName2, 3600, policy);
        String uploadToken3 = auth.uploadToken(headerBucketName, fileName3, 3600, policy);
        String uploadToken4 = auth.uploadToken(headerBucketName, fileName4, 3600, policy);
        String uploadToken5 = auth.uploadToken(headerBucketName, fileName5, 3600, policy);
        String uploadToken6 = auth.uploadToken(headerBucketName, fileName6, 3600, policy);
        String uploadToken7 = auth.uploadToken(headerBucketName, fileName7, 3600, policy);

        model.addAttribute("uploadToken", uploadToken);
        model.addAttribute("fileName", fileName);
        model.addAttribute("uploadToken2", uploadToken2);
        model.addAttribute("fileName2", fileName2);
        model.addAttribute("uploadToken3", uploadToken3);
        model.addAttribute("fileName3", fileName3);

        model.addAttribute("uploadToken4", uploadToken3);
        model.addAttribute("fileName4", fileName3);
        model.addAttribute("uploadToken5", uploadToken3);
        model.addAttribute("fileName5", fileName3);
        model.addAttribute("uploadToken6", uploadToken3);
        model.addAttribute("fileName6", fileName3);
        model.addAttribute("uploadToken7", uploadToken3);
        model.addAttribute("fileName7", fileName3);
        return model;
    }


    public Model fileupItem(Model model){
        // 上传文件名称
        String fileName = ShareUtil.generateUUID();
        String fileName2 = ShareUtil.generateUUID();
        String fileName3 = ShareUtil.generateUUID();
        String fileName4 = ShareUtil.generateUUID()+".zip";
        String fileName5 = ShareUtil.generateUUID()+".zip";
        String fileName6 = ShareUtil.generateUUID()+".zip";
        String fileName7 = ShareUtil.generateUUID()+".zip";
        // 设置响应信息
        StringMap policy = new StringMap();
        policy.put("returnBody", ShareUtil.getJSONString(0));
        // 生成上传凭证
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(headerBucketName, fileName, 3600, policy);
        String uploadToken2 = auth.uploadToken(headerBucketName, fileName2, 3600, policy);
        String uploadToken3 = auth.uploadToken(headerBucketName, fileName3, 3600, policy);
        String uploadToken4 = auth.uploadToken(headerBucketName, fileName4, 3600, policy);
        String uploadToken5 = auth.uploadToken(headerBucketName, fileName5, 3600, policy);
        String uploadToken6 = auth.uploadToken(headerBucketName, fileName6, 3600, policy);
        String uploadToken7 = auth.uploadToken(headerBucketName, fileName7, 3600, policy);

        model.addAttribute("uploadToken", uploadToken);
        model.addAttribute("fileName", fileName);
        model.addAttribute("uploadToken2", uploadToken2);
        model.addAttribute("fileName2", fileName2);
        model.addAttribute("uploadToken3", uploadToken3);
        model.addAttribute("fileName3", fileName3);
        model.addAttribute("uploadToken4", uploadToken4);
        model.addAttribute("fileName4", fileName4);
        model.addAttribute("uploadToken5", uploadToken5);
        model.addAttribute("fileName5", fileName5);
        model.addAttribute("uploadToken6", uploadToken6);
        model.addAttribute("fileName6", fileName6);
        model.addAttribute("uploadToken7", uploadToken7);
        model.addAttribute("fileName7", fileName7);

        return model;
    }

    public String getUrl(String fileName){
        String url = headerBucketUrl + "/" + fileName;
        return  url;
    }
}
