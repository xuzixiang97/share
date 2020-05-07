package com.xuzi.share.controller.designer;

import com.alibaba.fastjson.JSON;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Page;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.utils.BeanUtil;
import com.xuzi.share.utils.FileUtil;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/designer/item")
public class DesignerItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public String getPage(Model model, Page page, HttpSession session) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        //设置分页参数信息
        page.setPath("/designer/item/page");
        Object designerId = session.getAttribute("designerId");
        page.setRows(itemService.selectRowsByDesignerId(Integer.parseInt(designerId.toString())));
        List<Item> list = itemService.findPageByDesignerId(page.getOffset(), page.getLimit(),Integer.parseInt(designerId.toString()));
        model.addAttribute("items", list);
        fileUtil.fileupItem(model);
        return "designer/itemlist";
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/add/page")
    public String addPage(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        fileUtil.fileupItem(model);
        return "designer/additem1";
    }

    /**
     * 用户信息页面 展示用户信息
     * @param model
     * @return
     */
    @RequestMapping("/add/page2")
    public String addPage1(Model model) {
        model.addAttribute("hello", "aaaaaaaaaaaa");
        fileUtil.fileupItem(model);
        return "designer/additem2";
    }


    /**
     * 新增作品
     * @param model
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(Model model, Item item, Designer designer,HttpSession session,String key,String key2,String key3,String key4,String key5,String key6,String key7) {
        Object designerId = session.getAttribute("designerId");
        String url = fileUtil.getUrl(key);
        //todo 目前是测试
        item.setDesignerId(Integer.parseInt(designerId.toString()));
        item.setBuyoutUnitprice(item.getAuthorizeUnitprice());
        item.setShowImg(url);
        item.setShowImg2(fileUtil.getUrl(key2));
        item.setShowImg3(fileUtil.getUrl(key3));
        item.setCdrDownload(fileUtil.getUrl(key6));
        item.setEtDownload(fileUtil.getUrl(key7));
        item.setFabircImg(fileUtil.getUrl(key4));
        item.setProductInformationImg(fileUtil.getUrl(key5));
        item.setStatus(1);
        item.setCreateTime(System.currentTimeMillis());
        item.setUpdateTime(System.currentTimeMillis());
        itemService.insert(item);
        return ShareUtil.getJSONString(0);
    }


    /**
     * 作品数据
     * @param model
     * @return
     */
    @RequestMapping("/itemdata")
    @ResponseBody
    public String itemdata(Model model,HttpSession session) {
        Object designerId = session.getAttribute("designerId");
        List<Item> items = itemService.selectByDesignerId(Integer.parseInt(designerId.toString()));
        String s = JSON.toJSONString(items);
        return s;
    }

    /**
     * 修改作品
     * @param model
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(Model model,HttpSession session,Item item,String keyList,String key,String key2,String key3,String key4,String key5,String key6,String key7) {
        String[] strings = keyList.split(",");
        for (String string : strings) {
            if(string.equals("key")){
                String url = fileUtil.getUrl(key);
                item.setShowImg(url);
            }
            if(string.equals("key2")){
                item.setShowImg2(fileUtil.getUrl(key2));
            }
            if(string.equals("key3")){
                item.setShowImg3(fileUtil.getUrl(key3));
            }
            if(string.equals("key4")){
                item.setFabircImg(fileUtil.getUrl(key4));
            }
            if(string.equals("key5")){
                item.setProductInformationImg(fileUtil.getUrl(key5));
            }
            if(string.equals("key6")){
                item.setCdrDownload(fileUtil.getUrl(key6));
            }
            if(string.equals("key7")){
                item.setEtDownload(fileUtil.getUrl(key7));
            }
        }
        itemService.updateByCondition(item);
        return ShareUtil.getJSONString(0);
    }
}
