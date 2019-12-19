package javaweb.forum.controller;

import javaweb.forum.entity.Comment;
import javaweb.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    
    @RequestMapping("findByPostId")
    public String findByPostId(Model model, HttpServletRequest request) {
        String post_id = request.getParameter("post_id");
        List<Comment> comments = commentService.findCommentsByPostId(post_id);
        model.addAttribute("comments",comments);
        return "showPost::comment_refresh";
    }
}
