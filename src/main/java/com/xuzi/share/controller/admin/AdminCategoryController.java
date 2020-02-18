package com.xuzi.share.controller.admin;

import com.xuzi.share.entity.Category;
import com.xuzi.share.service.CategoryService;
import com.xuzi.share.utils.ShareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * AdminCategoryController
 *
 * @author <a href="mailto:zixiang.xu@yunhutech.com">huohe</a>
 * @since 2020/01/02
 * <p>
 * desc：
 */
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有类目
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List <Category> categoryList = categoryService.selectByParentId(0);
        model.addAttribute("categoryList",categoryList);
        return "/admin/category";
    }

    /**
     * 根据id 查类目信息
     * @param model
     * @return
     */
    @RequestMapping("/find_by_id")
    public String findById(Model model,Integer id){
        Category category = categoryService.selectById(id);
        model.addAttribute("category",category);
        return "/admin/login";
    }


    /**
     * 新增
     * @param category
     * @param model
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String addCategory(Category category, Model model){
        categoryService.insert(category);
        return ShareUtil.getJSONString(0);
    }



    @RequestMapping("/update")
    @ResponseBody
    public String update(Category category){
        categoryService.updateById(category);
        return ShareUtil.getJSONString(0);
    }
}
