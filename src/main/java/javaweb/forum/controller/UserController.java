package javaweb.forum.controller;

import javaweb.forum.entity.User;
import javaweb.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @RequestMapping("updateUser")
    public String updateByUserId(HttpServletRequest request,HttpSession session){
        User user = (User)session.getAttribute("user");
        int user_id = user.getUserId();
        /**
         * 直接从session获得user_id
         */
        String user_phone = request.getParameter("user_phone");
        String user_workplace = request.getParameter("user_workplace");
        String user_job = request.getParameter("user_job");

        userService.updateByUserId(user_id,user_phone,user_workplace,user_job);
        return "userInfo";
    }


    @RequestMapping("/notVerify")
    @ResponseBody
    public String notVerify() {
        return "notVerify";
    }

    /**
     * 用户登录检验 
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public void userLogin(User user, HttpSession session, HttpServletResponse response) throws IOException {

        List<User> currUser = userService.verifyUser(user);
        if (currUser.size() != 0) {
            session.setAttribute("user",currUser.get(0));
            response.sendRedirect("/system");
        } else {
            response.sendRedirect("register");
        }
    }
    
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping("/registerUser")
    public String registerUser(User user){
        user.setUserAdmin(0);
        user.setUserPoint(10);
        if (userService.registerUser(user)){
            return "login";
        }
        else{
            return "notVerify";
        }
    }
}