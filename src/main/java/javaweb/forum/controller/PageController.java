package javaweb.forum.controller;

import javaweb.forum.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("system")
    public String system(){
        return "system";
    }

    @RequestMapping("postContent")
    public String postContent() { return "postContent";}


    @RequestMapping("showPost")
    public String showPost() {
        return "showPost";
    }
}
