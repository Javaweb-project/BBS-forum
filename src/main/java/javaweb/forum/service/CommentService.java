package javaweb.forum.service;

import javaweb.forum.dao.CommentDao;
import javaweb.forum.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao dao;

    /**
     * 根据 post_id查找评论
     * @param post_id
     * @return
     */
    public List<Comment> findCommentsByPostId(String post_id) {
        return dao.findCommentsByPostId(post_id);    
    }

    /**
     * 根据 post_id删除评论
     * 用在管理员删帖后需要删除对应的全部评论的情况下
     * @param post_id
     * @return
     */
    public int deleteCommentsByPostId(String post_id) {
        return dao.deleteCommentsByPostId(post_id);
    }

    /**
     * 新建评论
     * @param comment
     * @return
     */
    public int insertComment(Comment comment) {
        return dao.insertComment(comment.getId().getPostId(),comment.getCommentContent(),comment.getId().getCommentUserId(),comment.getId().getCommentTime(),comment.getCommentAccept());
    }

    /**
     * 改变评论是否被采纳的状态
     * @param accept
     * @param post_id
     * @param comment_user_id
     * @param comment_time
     * @return
     */
    public int updateAccept(int accept,String post_id,String comment_user_id,String comment_time) {
        return dao.updateAccept(accept,post_id,comment_user_id,comment_time);
    }

    /**
     * 查找某个需求帖子是否已经有采纳的评论
     * @param post_id
     * @return
     */
    public Boolean hasAccept(String post_id) {
        List<Comment> comments = dao.findOneAccepted(post_id);
        if(comments.size() > 0)
            return true;
        else
            return false;
    }
}
