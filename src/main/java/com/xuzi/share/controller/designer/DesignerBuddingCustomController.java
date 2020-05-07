package com.xuzi.share.controller.designer;

import com.xuzi.share.entity.BiddingCustom;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Page;
import com.xuzi.share.service.BiddingCustomService;
import com.xuzi.share.service.ItemService;
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
@RequestMapping("/designer/bidding")
public class DesignerBuddingCustomController {

    @Autowired
    private BiddingCustomService biddingCustomService;

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
        page.setPath("/designer/bidding/page");
        page.setRows(biddingCustomService.selectRows());
        List<BiddingCustom> customList = biddingCustomService.findPage(page.getOffset(), page.getLimit());
        model.addAttribute("customList", customList);
        return "designer/bidding";
    }


    /**
     * 参加竞标
     * @param model
     * @return
     */
    @RequestMapping("/bidding")
    @ResponseBody
    public String add(Model model,  Integer id,HttpSession session,String key) {
        Object designerId = session.getAttribute("designerId");
        BiddingCustom custom = biddingCustomService.findById(id);
        if(custom.getDesignerIds()==null){
            custom.setDesignerIds(designerId.toString());
        }else{
            custom.setDesignerIds(custom.getDesignerIds()+","+designerId.toString());
        }
        biddingCustomService.update(custom);
        return ShareUtil.getJSONString(0);
    }

}
