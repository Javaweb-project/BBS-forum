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


    public boolean verifyUser(User user){

        if(dao.findByUserNameAndUserPassword(user.getUserName(),user.getUserPassword()).isEmpty()){
            return  false;
        }
        else return true;
    }
    public boolean registerUser(User user) {
        if (dao.findByUserName(user.getUserName()).isEmpty()) {
            dao.saveUser(user.getUserName(),user.getUserPassword(),user.getUserPhone(),user.getUserWorkPlace(),user.getUserJob(),user.getUserPoint(),user.getUserAdmin());
            return true;
        } else {
            return false;
        }

    }
}
