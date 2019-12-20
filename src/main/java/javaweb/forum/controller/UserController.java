package javaweb.forum.controller;

import javaweb.forum.entity.User;
import javaweb.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    
    @RequestMapping("addPoint")
    @ResponseBody
    public Map<String,String> addUserPoint(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        int point = Integer.parseInt(request.getParameter("point"));
        int res = userService.addUserPoint(user_id,point);
        Map<String,String> map = new HashMap<>();
        if(res == 1)
            map.put("res","ID为"+user_id+"的用户增加积分"+point+"成功");
        else
            map.put("res","ID为"+user_id+"的用户增加积分"+point+"失败");
        return map;
    }
}