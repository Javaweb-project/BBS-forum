package javaweb.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "post_id")
    private String postId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_point")
    private int postPoint;

    @Column(name = "post_top")
    private int postTop;

    @Column(name = "post_highlight")
    private int postHighLight;

    @Column(name = "post_time")
    private String postTime;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }
}
