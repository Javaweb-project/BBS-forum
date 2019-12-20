package javaweb.forum;

import javaweb.forum.service.PostService;
import javaweb.forum.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import javaweb.forum.pageTool.PageHelper;
//import javaweb.forum.pageTool.PageInfo;

@SpringBootTest
class ForumApplicationTests {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    
    @Test
    void contextLoads() {
      
    }

}
