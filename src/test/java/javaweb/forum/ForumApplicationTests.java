package javaweb.forum;

import javaweb.forum.entity.Post;
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
    
    @Test
    void contextLoads() {
//        List<Post> posts = postService.findByTitleLike("标题1");
//        posts.forEach(p->{
//            System.out.println("帖子的id："+p.getPostId());
//        });
//        int res = postService.updateHighLight("003",0);
//        System.out.println(res);
        String name = userService.findByUserId("1").getUserName();
        System.out.println(name);
    }

}
