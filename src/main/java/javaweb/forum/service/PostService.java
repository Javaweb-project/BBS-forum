package javaweb.forum.service;

import javaweb.forum.dao.PostDao;
import javaweb.forum.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDao dao;
    /*
     * 查询所有帖子通过帖子发布时间降序
     * */
    public List<Post> findAllOrderByPostTimeDesc() { return dao.findAllOrderByPostTimeDesc();}
    /*
     * 查询所有置顶帖子通过帖子发布时间降序
     * */
    public List<Post> findAllPostTopOrderByPostTimeDesc() {return dao.findAllPostTopOrderByPostTimeDesc();}
    /*
    * 查询所有精华帖子通过帖子发布时间降序
    * */
    public List<Post> findAllPostHighLightOrOrderByPostTimeDesc(){return dao.findAllPostHighLightOrOrderByPostTimeDesc();}

}
