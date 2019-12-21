package javaweb.forum.controller;

import javaweb.forum.entity.User;
import javaweb.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private Map<String, Object> result = new HashMap<>();
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
    //跳转链接，跳转到主页
    @RequestMapping("")
    public String index(HttpServletResponse response) {
        //重定向到 /index
        return response.encodeRedirectURL("/index");
    }

    //真正主页，用户在访问 XXXXX/index就会跳转该方法
    @RequestMapping("/index")
    public String home(Model model) {
        //对应到templates文件夹下面的index
        return "index";
    }

    //进入注册页面，使用Get请求
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return "register";
    }

    //注册用户，使用POST，传输数据
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(Model model,
                               //和模板中的th:object="${user}"对应起来
                               @ModelAttribute(value = "user") User user,
                               HttpServletResponse response) {
        //使用userService处理业务
        String result = userService.register(user);       //通过register将模板中的password和username放入数据库中然后返回一个字符串
        //将结果放入model中，在模板中可以取到model中的值
        model.addAttribute("result", result);          //将字符串放入模板中
        return response.encodeRedirectURL("/index");  //跳转到其它页面并且传递参数
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Model model,
                            @ModelAttribute(value = "user") User user,
                            HttpServletResponse response,
                            HttpSession session) {
        String result = userService.login(user);
        if (result.equals("登陆成功")) {
            //添加到session中，session是一次会话，在本次会话中都可以取到session中的值
            //若是session中有用户存在则会覆盖原来的user，当session中的user存在时判定用户存在
            session.setAttribute("user",user);  //将user对象放入session中，可以在相应的HTML页面中调用session中的user对象下的属性显示到HTML页面。
        }
        model.addAttribute("result", result);
        return response.encodeRedirectURL("/index");
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        //从session中删除user属性，用户退出登录
        session.removeAttribute("user");
        return "index";
    }
}
