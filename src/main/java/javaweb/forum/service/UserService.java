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
        return dao.findByUserId(user_id);
    }
}
