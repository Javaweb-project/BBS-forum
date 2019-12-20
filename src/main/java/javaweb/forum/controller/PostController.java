package javaweb.forum.controller;

import javaweb.forum.entity.Post;
import javaweb.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        Post post = postService.findByPostId(post_id);
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
    * 获取指定帖子的所有信息
     * @param model
     * @return
    * */
    @RequestMapping("findPostByPostId")
    public String findPostByPostId(Model model,@RequestParam(name = "post_id",defaultValue = "001") String post_id,HttpServletRequest request){
//        String post_id = request.getParameter("post_id");
        HttpSession session=request.getSession();
        Post post = postService.findByPostId(post_id);
        model.addAttribute("post",post);
        return "postContent";
    }

}
