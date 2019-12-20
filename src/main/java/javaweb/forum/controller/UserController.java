package javaweb.forum.controller;

import javaweb.forum.entity.Post;
import javaweb.forum.entity.User;
import javaweb.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("findNameById")
    @ResponseBody
    public Map<String,String> findNameById(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        User user = userService.findByUserId(user_id);
        String user_name = user.getUserName();
        Map<String,String> map = new HashMap<>();
        map.put("user_name",user_name);
        return map;
    }

    @RequestMapping("myInfo")
    public String userById(Model model, @RequestParam("user_id") String user_id) {
        User user = userService.findByUserId(user_id);
        model.addAttribute("user",user);
        return "userInfo";
    }

    @RequestMapping("updateUser")
    public String updateByUserId(HttpServletRequest request){
        String user_id = request.getParameter("user_id");

        String user_phone = request.getParameter("user_phone");
        String user_workplace = request.getParameter("user_workplace");
        String user_job = request.getParameter("user_job");

        userService.updateByUserId(user_id,user_phone,user_workplace,user_job);

        return "userInfo";
}
}