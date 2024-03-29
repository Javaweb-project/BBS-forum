package javaweb.forum.service;

import javaweb.forum.dao.PostDao;
import javaweb.forum.entity.Post;
import javaweb.forum.pageTool.PageHelper;
import javaweb.forum.pageTool.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDao dao;

    /**
     * 查询所有帖子通过帖子发布时间降序
     * @return
     */
    public List<Post> findAllOrderByPostTimeDesc() {
        return dao.findAllOrderByPostTimeDesc();
    }

    /**
     * 从传入的 posts列表中挑选出所有的置顶帖
     * @param posts
     * @return
     */
    public List<Post> selectTop(List<Post> posts) {
        List<Post> topPosts = new ArrayList<>();
        posts.forEach(p->{
            if(p.getPostTop() == 1)
                topPosts.add(p);
        });
        return topPosts;
    }

    /**
     * 查询所有精华帖子通过帖子发布时间降序
     * @return
     */
    public List<Post> findAllPostHighLightOrderByPostTimeDesc(){
        return dao.findAllPostHighLightOrderByPostTimeDesc();
    }

    /**
     * 查询所有帖子，按照浏览量高低返回
     * @return
     */
    public List<Post> findAllOrderByPostViewDesc(){
        return dao.findAllOrderByPostViewDesc();
    }

    /**
     * 查询所有的需求贴，按时间返回
     */
    public List<Post> findAllDemandOrderByPostTimeDesc() {
        return dao.findAllDemandOrderByPostTimeDesc();
    }

    /**
     * 按照帖子标题进行模糊查询
     * @param title
     * @return
     */
    public List<Post> findByTitleLike(String title) {
        return dao.findByTitleLike(title);
    }

    /**
     * 根据帖子 id返回帖子信息
     * @param post_id
     * @return
     */
    public Post findByPostId(String post_id) {
        return dao.findByPostId(Integer.parseInt(post_id));
    }

    /**
     * 根据用户 id 返回帖子
     * @param user_id
     * @return
     */
    public List<Post> findByUserId(int user_id) {
        return dao.findByUserId(user_id);
    }
    
    /**
     * 更新帖子的加精状态
     * @param post_id
     * @param high
     * @return
     */
    public int updateHighLight(String post_id,int high) {
        return dao.updateHighLight(Integer.parseInt(post_id),high);
    }

    /**
     * 更新帖子的置顶状态
     * @param post_id
     * @param top
     * @return
     */
    public int updateTop(String post_id,int top) {
        return dao.updateTop(Integer.parseInt(post_id),top);
    }

    /**
     * 根据帖子 id删除帖子
     * @param post_id
     * @return
     */
    public int deleteByPostId(String post_id) {
        return dao.deleteByPostId(Integer.parseInt(post_id));
    }

    /**
     * 完成分页功能
     * @param model 放在 model中返回
     * @param posts 需要分页的列表
     * @param page 需要的某一页的内容
     * @return
     */
    public Model dividePage(Model model,List<Post> posts,String page) {
        PageHelper pageHelper = new PageHelper();
        List<PageInfo> pageInfos = pageHelper.SetStartPage(posts,Integer.parseInt(page),2);
        model.addAttribute("posts",pageInfos.get(0).getList());
        model.addAttribute("totalPage",pageInfos.get(0).getTotalPage());
        model.addAttribute("pageNow",pageInfos.get(0).getPageNow());
        model.addAttribute("firstPage",pageInfos.get(0).isFirstPage());
        model.addAttribute("lastPage",pageInfos.get(0).isLastPage());
        return model;
    }

    /**
     * 上传用户发布的帖子内容
     * @param user_id
     * @return
     */
    public int saveSubmitPost(int user_id, String post_title, int post_point, String post_content, Timestamp post_time, int post_top, int post_highlight,int post_view) {
        return dao.saveSubmitPost(user_id,post_title,post_point,post_content,post_time,post_top,post_highlight,post_view);
    }

    /***
     * 更新内容
     * @param
     * @param
     * @return
     */
    public int updatePostContent(String post_id , String post_content){
        return dao.updatePostContent(Integer.parseInt(post_id ),post_content);
    }

    /**
     * 增加帖子的浏览数
     * @param post_id
     * @return
     */
    public int updatePostView(String post_id) {
        return dao.updatePostView(Integer.parseInt(post_id));    
    }
}