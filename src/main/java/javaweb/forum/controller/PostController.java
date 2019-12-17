package javaweb.forum.controller;

import javaweb.forum.entity.Post;
import javaweb.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
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
    public String postsByTime(Model model) {
        List<Post> posts = postService.findAllOrderByPostTimeDesc();
        List<Post> topPosts = postService.selectTop(posts);
        /**
         * 将置顶和非置顶的帖子分开
         */
        posts.removeAll(topPosts);
        model.addAttribute("posts",posts);
        model.addAttribute("topPosts",topPosts);
        return "system";
    }

    /**
     * 按浏览数返回所有帖子(包含加精帖、需求帖)
     * @param model
     * @return
     */
    @RequestMapping("postsByView")
    public String postsByView(Model model) {
        List<Post> posts = postService.findAllOrderByPostViewDesc();
        List<Post> topPosts = postService.selectTop(posts);
        /**
         * 将置顶和非置顶的帖子分开
         */
        posts.removeAll(topPosts);
        model.addAttribute("posts",posts);
        model.addAttribute("topPosts",topPosts);
        return "system";
    }
    
    /**
     * 按时间返回加精帖
     * @param model
     * @return
     */
    @RequestMapping("highLightPosts")
    public String highLight(Model model) {
        List<Post> posts = postService.findAllPostHighLightOrderByPostTimeDesc();
        List<Post> topPosts = postService.selectTop(posts);
        posts.removeAll(topPosts);
        model.addAttribute("posts",posts);
        model.addAttribute("topPosts",topPosts);
        return "system";
    }

    /**
     * 按时间返回需求贴
     */
    @RequestMapping("demandPosts")
    public String demand(Model model) {
        List<Post> posts = postService.findAllDemandOrderByPostTimeDesc();
        List<Post> topPosts = postService.selectTop(posts);
        posts.removeAll(topPosts);
        model.addAttribute("posts",posts);
        model.addAttribute("topPosts",topPosts);
        return "system";
    }

    /**
     * 按照帖子标题进行模糊查询
     */
    @RequestMapping("query")
    public String findByTitleLike(Model model, HttpServletRequest request) {
        String title = request.getParameter("title");
        List<Post> posts = postService.findByTitleLike(title);
        model.addAttribute("posts",posts);
        return "system";
    }

    /**
     * 改变帖子加精状态
     * @param request
     * @return
     */
    @RequestMapping("updateHigh")
    public String updateHigh() {
//        String post_id = request.getParameter("post_id");
//        Post post = postService.findByPostId(post_id);
//        Map<String,String> map = new HashMap<>();
//        int res;
//        if(post.getPostHighLight() == 0)
//            res = postService.updateHighLight(post_id,1);
//        else
//            res = postService.updateHighLight(post_id,0);
//        if(res == 1)
//            map.put("res","修改成功");
//        else
//            map.put("res","修改失败");
//        return map;
        return "system";
    }

    /**
     * 改变帖子置顶状态
     * @param request
     * @return
     */
    @RequestMapping("updateTop")
    public String updateTop(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        Post post = postService.findByPostId(post_id);
        if(post.getPostTop() == 0)
            postService.updateTop(post_id,1);
        else
            postService.updateTop(post_id,0);
        return "redirect:/postsByTime";
    }
}
