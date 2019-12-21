package javaweb.forum.service;

import javaweb.forum.dao.UserDao;
import javaweb.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 根据用户 id查找用户
     * @param user_id
     * @return
     */
    public User findByUserId(String user_id) {
        return userDao.findByUserId(user_id);
    }

    public void insert(User user){userDao.insert(user);}

    //用户注册逻辑
    public String register(User user) {
        //判断用户是否存在
        if (userDao.findByUserName(user.getUserName()) == null && user.getUserPassword()!=null) {
            userDao.insert(user);
            return "注册成功";
        }
        else if((userDao.findByUserName(user.getUserName()) != null)){
            return "用户已存在请更换用户名";
        }
        return "注册失败";
    }

    //用户登陆逻辑
    public String login(User user) {
        //通过用户名获取用户
        User dbUser = userDao.findByUserName(user.getUserName());
        //若获取失败
        if (dbUser == null) {
            return "该用户不存在";
        }
        //获取成功后，将获取用户的密码和传入密码对比
        else if (!dbUser.getUserPassword().equals(user.getUserPassword())) {
            return "密码错误";
        } else {
            //若密码也相同则登陆成功
            //让传入用户的属性和数据库保持一致
            user.setUserId(dbUser.getUserId());
            return "登陆成功";
        }
    }


    public boolean verifyUser(User user){

        if(dao.findByUserNameAndUserPassword(user.getUserName(),user.getUserPassword()).isEmpty()){
            return  false;
        }
        else return true;
    }
    public boolean registerUser(User user) {

        if (dao.findByUserName(user.getUserName()).isEmpty()) {
            dao.saveUser(user.getUserName(),user.getUserPassword());
            return true;
        } else {
            return false;
        }

    }
}
