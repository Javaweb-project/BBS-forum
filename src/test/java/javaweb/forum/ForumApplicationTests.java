package javaweb.forum;

import javaweb.forum.entity.Comment;
import javaweb.forum.entity.Post;
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
        // 测试评论查找
//        List<Comment> comments = commentService.findCommentsByPostId("001");
//        comments.forEach(c->{
//            System.out.println(c.getId().getCommentTime());
//        });
        // 测试帖子模糊搜索功能
//        List<Post> posts = postService.findByTitleLike("标题1");
//        System.out.println(posts.size());
//        System.out.println(commentService.hasAccept("001"));
        //测试查询所有帖子功能
//        List<Post> posts = postService.findAllOrderByPostTimeDesc();
//        System.out.println(posts.size());
                
    }

}
