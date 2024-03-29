package javaweb.forum.dao;

import javaweb.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
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
    Post findByPostId(int post_id);
    
    @Query(value = "select * from post where user_id = ?1 order by post_time desc",nativeQuery = true)
    List<Post> findByUserId(int user_id);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update post set post_highlight = ?2 where post_id = ?1",nativeQuery = true)
    int updateHighLight(int post_id,int high);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update post set post_top = ?2 where post_id = ?1",nativeQuery = true)
    int updateTop(int post_id,int top);

    @Transactional
    @Modifying
    @Query(value = "delete from post where post_id = ?1",nativeQuery = true)
    int deleteByPostId(int post_id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into post (user_id,post_title,post_point,post_content,post_time,post_top,post_highlight,post_view) values (?1,?2,?3,?4,?5,?6,?7,?8);",nativeQuery = true)
    int saveSubmitPost(int user_id, String post_title, int post_point, String post_content, Timestamp post_time, int post_top, int post_highlight, int post_view);

    @Transactional
    @Modifying
    @Query(value = "update post set post_content = ?2 where post_id = ?1",nativeQuery = true)
    int updatePostContent(int post_id , String post_content);

    @Transactional
    @Modifying
    @Query(value = "update post set post_view = post_view + 1 where post_id = ?1",nativeQuery = true)
    int updatePostView(int post_id);
}
