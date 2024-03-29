package javaweb.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_point")
    private int postPoint;

    @Column(name = "post_top")
    private int postTop;

    @Column(name = "post_highlight")
    private int postHighLight;

    @Column(name = "post_time")
    private Timestamp postTime;

    @Column(name = "post_view")
    private int postView;

    public int getPostView() {
        return postView;
    }

    public void setPostView(int postView) {
        this.postView = postView;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getPostPoint() {
        return postPoint;
    }

    public void setPostPoint(int postPoint) {
        this.postPoint = postPoint;
    }

    public int getPostTop() {
        return postTop;
    }

    public void setPostTop(int postTop) {
        this.postTop = postTop;
    }

    public int getPostHighLight() {
        return postHighLight;
    }

    public void setPostHighLight(int postHighLight) {
        this.postHighLight = postHighLight;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }
}
