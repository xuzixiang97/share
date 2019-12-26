package com.xuzi.share.controller.interceptor;

import com.xuzi.share.dao.DesignerMapper;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.utils.CookieUtil;
import com.xuzi.share.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {
    @Autowired
    private DesignerMapper designerMapper;
    //获取ticket来查找User

    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String designerId = CookieUtil.getValue(request,"designerId");
        if (designerId!=null){
            Designer designer = designerMapper.selectByPrimaryKey(Integer.parseInt(designerId));
            //在本次请求当中是持有用户，保存下来.在多线程之间隔离对象ThreadLocal
            hostHolder.setDesigner(designer);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //先从hostholder里得到user
        Designer designer = hostHolder.getDesigner();
        if (designer!=null && modelAndView !=null){
            modelAndView.addObject("designer",designer);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clearDesigner();
    }
}
