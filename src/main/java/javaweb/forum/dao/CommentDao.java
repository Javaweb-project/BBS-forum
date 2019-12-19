package javaweb.forum.dao;

import javaweb.forum.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment,String> {
    @Query(value = "select * from comment where post_id = ?1 order by comment_time desc",nativeQuery = true)
    List<Comment> findCommentsByPostId(String post_id);

    @Query(value = "select * from comment where comment_accept = 1 and post_id = ?1",nativeQuery = true)
    List<Comment> findOneAccepted(String post_id);
    
    @Transactional
    @Modifying
    @Query(value = "delete from comment where post_id = ?1",nativeQuery = true)
    int deleteCommentsByPostId(String post_id);

    @Transactional
    @Modifying
    @Query(value = "insert into comment values(?1,?2,?3,?4,?5)",nativeQuery = true)
    int insertComment(String post_id,String comment_content,String comment_user_id,String comment_time,int comment_accept);

    @Transactional
    @Modifying
    @Query(value = "update comment set comment_accept = ?1 where post_id = ?2 and comment_user_id = ?3 and comment_time = ?4",nativeQuery = true)
    int updateAccept(int accept,String post_id,String comment_user_id,String comment_time);
}
