package javaweb.forum.controller;

import javaweb.forum.entity.Post;
import javaweb.forum.entity.User;
import javaweb.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    
    /**
     * 按时间返回所有帖子(包含加精帖、需求帖)
     * @param model
     * @return
     */
    @RequestMapping("postsByTime")
    public String postsByTime(Model model,@RequestParam(name = "page",defaultValue = "1") String page) {
        List<Post> posts = postService.findAllOrderByPostTimeDesc();
        List<Post> topPosts = postService.selectTop(posts);
        /**
         * 将置顶和非置顶的帖子分开
         */
        posts.removeAll(topPosts);
        if(posts.size() != 0)
            model = postService.dividePage(model,posts,page);
        model.addAttribute("topPosts",topPosts);
        return "listPosts";
    }

    /**
     * 按浏览数返回所有帖子(包含加精帖、需求帖)
     * @param model
     * @return
     */
    @RequestMapping("postsByView")
    public String postsByView(Model model,@RequestParam(name = "page",defaultValue = "1") String page) {
        List<Post> posts = postService.findAllOrderByPostViewDesc();
        List<Post> topPosts = postService.selectTop(posts);
        /**
         * 将置顶和非置顶的帖子分开
         */
        posts.removeAll(topPosts);
        if(posts.size() != 0)
            model = postService.dividePage(model,posts,page);
        model.addAttribute("topPosts",topPosts);
        return "listPosts";
    }

    /**
     * 按时间返回加精帖
     * @param model
     * @return
     */
    @RequestMapping("highLightPosts")
    public String highLight(Model model,@RequestParam(name = "page",defaultValue = "1") String page) {
        List<Post> posts = postService.findAllPostHighLightOrderByPostTimeDesc();
        List<Post> topPosts = postService.selectTop(posts);
        posts.removeAll(topPosts);
        if(posts.size() != 0)
            model = postService.dividePage(model,posts,page);
        model.addAttribute("topPosts",topPosts);
        return "listPosts";
    }

    /**
     * 按时间返回需求贴
     */
    @RequestMapping("demandPosts")
    public String demand(Model model,@RequestParam(name = "page",defaultValue = "1") String page) {
        List<Post> posts = postService.findAllDemandOrderByPostTimeDesc();
        List<Post> topPosts = postService.selectTop(posts);
        posts.removeAll(topPosts);
        if(posts.size() != 0)
            model = postService.dividePage(model,posts,page);
        model.addAttribute("topPosts",topPosts);
        return "listPosts";
    }

    /**
     * 按照帖子标题进行模糊查询
     */
    @RequestMapping("query")
    public String findByTitleLike(Model model, HttpServletRequest request) {
        String title = request.getParameter("title");
        List<Post> posts = postService.findByTitleLike(title);
        model.addAttribute("posts",posts);
        return "listPosts";
    }

    /**
     * 改变帖子加精状态
     * @param request
     * @return
     */
    @RequestMapping("updateHigh")
    @ResponseBody
    public Map<String,String> updateHigh(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        Post post = postService.findByPostId(post_id);
        Map<String,String> map = new HashMap<>();
        int res;
        if(post.getPostHighLight() == 0)
            res = postService.updateHighLight(post_id,1);
        else
            res = postService.updateHighLight(post_id,0);
        if(res == 1)
            map.put("res","修改加精成功");
        else
            map.put("res","修改加精失败");
        return map;
    }

    /**
     * 改变帖子置顶状态
     * @param request
     * @return
     */
    @RequestMapping("updateTop")
    @ResponseBody
    public Map<String,String> updateTop(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        Post post = postService.findByPostId(post_id);
        Map<String,String> map = new HashMap<>();
        int res;
        if(post.getPostTop() == 0)
            res = postService.updateTop(post_id,1);
        else
            res = postService.updateTop(post_id,0);
        if(res == 1)
            map.put("res","修改置顶成功");
        else
            map.put("res","修改置顶失败");
        return map;
    }

    /**
     * 根据 post_id删除帖子
     * @param request
     * @return
     */
    @RequestMapping("delPost")
    @ResponseBody
    public Map<String,String> delPost(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        Map<String,String> map = new HashMap<>();
        int res;
        res = postService.deleteByPostId(post_id);
        if(res == 1)
            map.put("res","删除成功");
        else
            map.put("res","删除失败");
        return map;
    }

    /**
     * 发布帖子
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("submitPost")
    @ResponseBody
    public Map<String,String> submitPost(HttpServletRequest request, HttpSession session) {
        String post_title = request.getParameter("title");
        String post_content = request.getParameter("content");
        int post_point = Integer.parseInt(request.getParameter("point"));
        User loginUser = (User) session.getAttribute("user");
        int user_id = loginUser.getUserId();
        int post_top = 0;
        int post_highlight = 0;
        int post_view = 0;
        Map<String,String> map = new HashMap<>();
        int res;
        res= postService.saveSubmitPost(user_id,post_title,post_point,post_content, new Timestamp(System.currentTimeMillis()),post_top,post_highlight, post_view );
        if(res == 1)
            map.put("res","发布成功");
        else
            map.put("res","发布失败");
        return map;
    }

    /**
     * 获取指定帖子的所有信息
     * @param model
     * @return
     * */
    @RequestMapping("findPostByPostId")
    public String findPostByPostId(Model model,HttpServletRequest request){
        String post_id = request.getParameter("post_id");
        postService.updatePostView(post_id);
        Post post = postService.findByPostId(post_id);
        model.addAttribute("post",post);
        return "postContent";
    }
    
    /***
     * 更新帖子的内容
     * @param
     * @return
     */
    @RequestMapping("updatePostContent")
    @ResponseBody
    public Map<String,String> updatePostContent(HttpServletRequest request){
        String post_id = request.getParameter("post_id");
        String post_content = request.getParameter("post_content");
        Map<String,String> map = new HashMap<>();
        int res;
        res = postService.updatePostContent(post_id,post_content);
        if(res == 1)
            map.put("res","更新成功");
        else
            map.put("res","更新失败");
        return map;
    }
    
    @RequestMapping("myPosts")
    public String myPosts(Model model,HttpSession session) {
        User user = (User)session.getAttribute("user");
        int user_id = user.getUserId();
        List<Post> posts = postService.findByUserId(user_id);
        model.addAttribute("posts",posts);
        return "myPost";
    }
}
