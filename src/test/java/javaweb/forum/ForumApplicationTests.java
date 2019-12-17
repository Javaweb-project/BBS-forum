package javaweb.forum;

import javaweb.forum.entity.Post;
import javaweb.forum.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ForumApplicationTests {

    @Autowired
    PostService postService;
    
    @Test
    void contextLoads() {
//        List<Post> posts = postService.findByTitleLike("标题1");
//        posts.forEach(p->{
//            System.out.println("帖子的id："+p.getPostId());
//        });
        int res = postService.updateHighLight("003",0);
        System.out.println(res);
    }

}
