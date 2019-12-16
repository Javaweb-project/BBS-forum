package javaweb.forum.dao;

import javaweb.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post,String> {
    @Query(value = "select * from post order by post_time desc",nativeQuery = true)
    List<Post> findAllOrderByPostTimeDesc();

    @Query(value = "select * from post where post_top = 1 order by post_time desc",nativeQuery = true)
    List<Post> findAllPostTopOrderByPostTimeDesc();

    @Query(value = "select * from post where post_highlight = 1 order by post_time desc",nativeQuery = true)
    List<Post> findAllPostHighLightOrOrderByPostTimeDesc();
}
