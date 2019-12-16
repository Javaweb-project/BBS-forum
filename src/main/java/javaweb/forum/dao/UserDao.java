package javaweb.forum.dao;

import javaweb.forum.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    //List<User> allUser();
    //int insertUser(User user);
    //int delUser(int id);
    //int update(User user);
}
