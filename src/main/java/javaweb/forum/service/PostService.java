package javaweb.forum.service;

import javaweb.forum.dao.PostDao;
import javaweb.forum.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDao dao;

    /**
     * 查询所有帖子通过帖子发布时间降序
     * @return
     */
    public List<Post> findAllOrderByPostTimeDesc() { 
        return dao.findAllOrderByPostTimeDesc();
    }

    /**
     * 从传入的 posts列表中挑选出所有的置顶帖
     * @return
     */
    public List<Post> selectTop(List<Post> posts) {
        List<Post> topPosts = new ArrayList<>();
        posts.forEach(p->{
            if(p.getPostTop() == 1)
                topPosts.add(p);
        });
        return topPosts;
    }

    /**
     * 查询所有精华帖子通过帖子发布时间降序
     * @return
     */
    public List<Post> findAllPostHighLightOrderByPostTimeDesc(){
        return dao.findAllPostHighLightOrderByPostTimeDesc();
    }

    /**
     * 查询所有帖子，按照浏览量高低返回
     * @return
     */
    public List<Post> findAllOrderByPostViewDesc(){
        return dao.findAllOrderByPostViewDesc();
    }

    /**
     * 查询所有的需求贴，按时间返回 
     */
    public List<Post> findAllDemandOrderByPostTimeDesc() {
        return dao.findAllDemandOrderByPostTimeDesc();
    }
}
