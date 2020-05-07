package com.xuzi.share.controller.user;

import com.xuzi.share.entity.Comment;
import com.xuzi.share.entity.Item;
import com.xuzi.share.entity.Page;
import com.xuzi.share.entity.User;
import com.xuzi.share.service.CommentService;
import com.xuzi.share.service.ItemService;
import com.xuzi.share.service.UserService;
import com.xuzi.share.utils.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/comment")
public class CommentController implements CommunityConstant {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;


    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment, HttpSession session) {
        //SpringMVC会自动实例化Model和Page,并将Page注入Model
        Object userId = session.getAttribute("userId");
        comment.setUserId(Integer.parseInt(userId.toString()));
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        return "redirect:/comment/detail/" + discussPostId;
    }

    /**
     * 显示帖子信息
     * @param discussPostId
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page,HttpSession session) {



        // 评论分页信息
        page.setLimit(4);
        page.setPath("/comment/detail/" + discussPostId);
        page.setRows(commentService.findCommentCount(1,discussPostId));

        // 评论列表
        List<Comment> commentList = commentService.findCommentsByEntity(
                ENTITY_TYPE_POST, discussPostId, page.getOffset(), page.getLimit());
        // 评论VO列表
        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                Map<String, Object> commentVo = new HashMap<>();
                commentVo.put("comment", comment);
                commentVo.put("user", userService.findById(comment.getUserId()));

                // 回复列表
                List<Comment> replyList = commentService.findCommentsByEntity(
                        ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                // 回复VO列表
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply : replyList) {
                        Map<String, Object> replyVo = new HashMap<>();
                        replyVo.put("reply", reply);
                        replyVo.put("user", userService.findById(reply.getUserId()));
                        User target = reply.getTargetId() == 0 ? null : userService.findById(reply.getTargetId());
                        replyVo.put("target", target);
                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys", replyVoList);

                // 回复数量
                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("replyCount", replyCount);
                commentVoList.add(commentVo);
            }
        }
        Item itemDetail = itemService.selectById(discussPostId);
        model.addAttribute("item",itemDetail);

        model.addAttribute("comments", commentVoList);
        model.addAttribute("post", itemDetail);
        Object userId = session.getAttribute("userId");
        User user = userService.findById(Integer.parseInt(userId.toString()));
        model.addAttribute("user", user);



        return "user/commentDetail";
    }

}
