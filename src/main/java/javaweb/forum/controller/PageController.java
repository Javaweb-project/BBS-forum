package javaweb.forum.controller;

import javaweb.forum.entity.User;
import javaweb.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PageController {
    @Autowired
    UserService userService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public  String login(Model model, @ModelAttribute(value = "user") User user) {
        return "login";
    }

    /**
     * 主页面
     * @return
     */
    @RequestMapping("system")
    public String system(){
        return "system";
    }

    /**
     * 发布帖子
     */
    @RequestMapping("post")
    public String post() {
        return "post";
    }

    /**
     * 个人信息
     * @return
     */
    @RequestMapping("userInfo")
    public String info() {
        return "userInfo";
    }

    /**
     * 个人中心
     * @return
     */
    @RequestMapping("personalCenter")
    public String center() {
        return "personalCenter";
    }

    /**
     * 退出
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping("leave")
    public void leave(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/login");
    }
}
