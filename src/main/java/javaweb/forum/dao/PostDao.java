package javaweb.forum.dao;

import javaweb.forum.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    
    @Query(value = "select * from post where post_id = ?1",nativeQuery = true)
    Post findByPostId(String post_id);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update post set post_highlight = ?2 where post_id = ?1",nativeQuery = true)
    int updateHighLight(String post_id,int high);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update post set post_top = ?2 where post_id = ?1",nativeQuery = true)
    int updateTop(String post_id,int top);
    
    @Modifying
    @Query(value = "delete from post where post_id = ?1",nativeQuery = true)
    int deleteByPostId(String post_id);
}
