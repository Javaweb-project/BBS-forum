package javaweb.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "post_id")
    private String postId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_user_id")
    private String commentUserId;

    @Column(name = "comment_time")
    private String commentTime;

    @Column(name = "comment_accept")
    private int commentAccept;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    public int getCommentAccept() {
        return commentAccept;
    }

    public void setCommentAccept(int commentAccept) {
        this.commentAccept = commentAccept;
    }
}
