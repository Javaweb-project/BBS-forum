package javaweb.forum.controller;

import javaweb.forum.entity.Comment;
import javaweb.forum.entity.CommentKey;
import javaweb.forum.entity.Post;
import javaweb.forum.entity.User;
import javaweb.forum.service.CommentService;
import javaweb.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    
    @RequestMapping("findByPostId")
    public String findByPostId(Model model, HttpServletRequest request) {
//        String post_id = request.getParameter("post_id");
        List<Comment> comments = commentService.findCommentsByPostId("001");
        Boolean hasAccept = commentService.hasAccept("001");
        Post post = postService.findByPostId("001");
        model.addAttribute("comments",comments);
        model.addAttribute("hasAccept",hasAccept);
        model.addAttribute("post",post);
        return "comments";
    }
    
    @RequestMapping("addComment")
    @ResponseBody
    public Map<String,String> addComment(HttpServletRequest request, HttpSession session) {
        String post_id = request.getParameter("post_id");
        String content = request.getParameter("content");
        User user = (User)session.getAttribute("user");
        String user_id = user.getUserId();
        String time = request.getParameter("time");
        Comment comment = new Comment();
        CommentKey key = new CommentKey();
        key.setCommentUserId(Integer.parseInt(user_id));
        key.setCommentTime(time);
        key.setPostId(Integer.valueOf(post_id));
        comment.setId(key);
        comment.setCommentContent(content);
        comment.setCommentAccept(0);
        int count = commentService.insertComment(comment);
        Map<String,String> map = new HashMap<>();
        if(count == 1)
            map.put("res","回复成功");
        else
            map.put("res","回复失败");
        return map; 
    }
    
    @RequestMapping("accept")
    @ResponseBody
    public Map<String,String> accept(HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        String user_id = request.getParameter("user_id");
        String time = request.getParameter("time");
        int res = commentService.updateAccept(1,post_id,user_id,time);
        Map<String,String> map = new HashMap<>();
        if(res == 1)
            map.put("res","采纳成功");
        else
            map.put("res","采纳失败");
        return map;
    }
}
