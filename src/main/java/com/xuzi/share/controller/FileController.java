package com.xuzi.share.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.xuzi.share.utils.CommunityUtil;
import com.xuzi.share.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    @Autowired
    private FileUtil fileUtil;


    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage(Model model) {
        fileUtil.fileup(model);
        return "designer/setting";
    }

//    // 更新头像路径
//    @RequestMapping(path = "/header/url", method = RequestMethod.POST)
//    @ResponseBody
//    public String updateHeaderUrl(String fileName) {
//        if (StringUtils.isBlank(fileName)) {
//            return CommunityUtil.getJSONString(1, "文件名不能为空!");
//        }
//
//        String url = headerBucketUrl + "/" + fileName;
//        //userService.updateHeader(url, hostHolder.getUser().getId());
//
//        return CommunityUtil.getJSONString(0);
//    }
}
