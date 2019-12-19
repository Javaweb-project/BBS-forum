package javaweb.forum.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    /**
     * 复合主键
     */
    @EmbeddedId
    private CommentKey id;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_accept")
    private int commentAccept;

    public CommentKey getId() {
        return id;
    }

    public void setId(CommentKey id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentAccept() {
        return commentAccept;
    }

    public void setCommentAccept(int commentAccept) {
        this.commentAccept = commentAccept;
    }
}
