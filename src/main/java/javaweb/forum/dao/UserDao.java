package javaweb.forum.dao;

import javaweb.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    @Query(value = "select * from user where user_id = ?1",nativeQuery = true)
    User findByUserId(String user_id);

    @Transactional
    @Modifying
    @Query(value = "update user set user_point = user_point + ?2 where  user_id = ?1",nativeQuery = true)
    int addUserPoint(String user_id,int addPoint);
}
