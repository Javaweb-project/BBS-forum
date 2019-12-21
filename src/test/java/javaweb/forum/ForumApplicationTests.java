package javaweb.forum;

import javaweb.forum.entity.Comment;
import javaweb.forum.service.CommentService;
import javaweb.forum.service.PostService;
import javaweb.forum.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class ForumApplicationTests {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    
    @Test
    void contextLoads() {
        List<Comment> comments = commentService.findCommentsByPostId("001");
        comments.forEach(c->{
            System.out.println(c.getId().getCommentTime());
        });
    }

}
