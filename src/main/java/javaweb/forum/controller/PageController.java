package javaweb.forum.controller;

import javaweb.forum.entity.User;
import javaweb.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @Autowired
    UserService userService;
    
    @RequestMapping("system")
    public String system(HttpSession session){
        /**
         * 模拟登录session存储登陆人信息
         * 与登录界面合并后就可以删掉
         */
        User user = new User();
        user = userService.findByUserId("1");
        session.setAttribute("user",user);
        return "system";
    }
    
}
