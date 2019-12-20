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
        String fileName = CommunityUtil.generateUUID();
        String fileName2 = CommunityUtil.generateUUID();
        String fileName3 = CommunityUtil.generateUUID();
        // 设置响应信息
        StringMap policy = new StringMap();
        policy.put("returnBody", CommunityUtil.getJSONString(0));
        // 生成上传凭证
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(headerBucketName, fileName, 3600, policy);
        String uploadToken2 = auth.uploadToken(headerBucketName, fileName2, 3600, policy);
        String uploadToken3 = auth.uploadToken(headerBucketName, fileName3, 3600, policy);

        model.addAttribute("uploadToken", uploadToken);
        model.addAttribute("fileName", fileName);
        model.addAttribute("uploadToken2", uploadToken2);
        model.addAttribute("fileName2", fileName2);
        model.addAttribute("uploadToken3", uploadToken3);
        model.addAttribute("fileName3", fileName3);
        return model;
    }

    public String getUrl(String fileName){
        String url = headerBucketUrl + "/" + fileName;
        return  url;
    }
}
