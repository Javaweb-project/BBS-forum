package javaweb.forum.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Comment类的符合主键
 */
@Embeddable
public class CommentKey implements Serializable {
    @Column(name = "post_id")
    private String postId;

    @Column(name = "comment_user_id")
    private String commentUserId;

    @Column(name = "comment_time")
    private String commentTime;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
