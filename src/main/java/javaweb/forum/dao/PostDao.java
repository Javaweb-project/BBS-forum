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

    @Query(value = "select * from post where post_highlight = 1 order by post_time desc",nativeQuery = true)
    List<Post> findAllPostHighLightOrderByPostTimeDesc();

    @Query(value = "select * from post order by post_view desc ",nativeQuery = true)
    List<Post> findAllOrderByPostViewDesc();
    
    @Query(value = "select * from post where post_point <> 0 order by post_view desc",nativeQuery = true)
    List<Post> findAllDemandOrderByPostTimeDesc();
    
    @Query(value = "select * from post where post_title like %?1% order by post_time desc",nativeQuery = true)
    List<Post> findByTitleLike(String title);
}
