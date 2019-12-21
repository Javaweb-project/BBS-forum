package javaweb.forum.service;

import javaweb.forum.dao.UserDao;
import javaweb.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    /**
     * 根据用户 id查找用户
     * @param user_id
     * @return
     */
    public User findByUserId(String user_id) {
        return dao.findByUserId(Integer.parseInt(user_id));
    }

    /**
     * 增加用户的积分
     * @param user_id
     * @param addPoint
     * @return
     */
    public int addUserPoint(String user_id,int addPoint) {
        return dao.addUserPoint(Integer.parseInt(user_id),addPoint); 
    }

    /**
     * 更新用户信息
     * @param user_id
     * @param user_phone
     * @param user_workplace
     * @param user_job
     * @return
     */
    public int updateByUserId(int user_id, String user_phone, String user_workplace, String user_job){
        return dao.updateByUserId(user_id, user_phone, user_workplace, user_job);

    }
}
