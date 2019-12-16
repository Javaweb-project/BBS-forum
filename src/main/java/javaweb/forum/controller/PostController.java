package javaweb.forum.controller;

import javaweb.forum.entity.Post;
import javaweb.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/post")
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
}
