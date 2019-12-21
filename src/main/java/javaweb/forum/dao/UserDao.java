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
    User findByUserId(int user_id);

    @Transactional
    @Modifying
    @Query(value = "update user set user_point = user_point + ?2 where  user_id = ?1",nativeQuery = true)
    int addUserPoint(int user_id,int addPoint);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update user set user_phone = ?2,user_workplace =?3,user_job=?4 where user_id = ?1",nativeQuery = true)
    int updateByUserId(int user_id,String user_phone,String user_workplace,String user_job);
}
