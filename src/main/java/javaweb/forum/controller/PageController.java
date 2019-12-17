package javaweb.forum.controller;

import javaweb.forum.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping("system")
    public String system(HttpSession session){
        User user = new User();
        user.setUserId("123");
        user.setUserAdmin(1);
        session.setAttribute("user",user);
        return "system";
    }
}
