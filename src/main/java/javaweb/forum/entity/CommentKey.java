package javaweb.forum.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Comment类的复合主键
 */
@Embeddable
public class CommentKey implements Serializable {
    @Column(name = "post_id")
    private int postId;

    @Column(name = "comment_user_id")
    private int commentUserId;

    @Column(name = "comment_time")
    private String commentTime;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
