package com.xuzi.share.controller.admin;

import com.xuzi.share.entity.Category;
import com.xuzi.share.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public String allCategory(Model model){
        categoryService.
        List<Category> categoryList = categoryService.selectbyCondition();
        model.addAttribute("categoryList",categoryList);
        return "/admin/category";
    }
}
