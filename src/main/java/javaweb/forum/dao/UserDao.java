package javaweb.forum.dao;

import javaweb.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    @Query(value = "select * from user where user_id = ?1",nativeQuery = true)
    User findByUserId(String user_id);
    @Query(value = "select * from user where user_name = ?1 and user_password = ?2",nativeQuery = true)
    List<User> findByUserNameAndUserPassword(String name,String password);
    @Query(value = "select * from user where user_name = ?1",nativeQuery = true)
    List<User> findByUserName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into user (user_name,user_password)values (?1, ?2)",nativeQuery = true)
    int saveUser(String name,String password);
}
