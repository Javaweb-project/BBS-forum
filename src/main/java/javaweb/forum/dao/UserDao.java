package javaweb.forum.dao;

import javaweb.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    @Query(value = "select * from user where user_id = ?1",nativeQuery = true)
    User findByUserId(String user_id);

    @Query(value = "insert into user (user_id,user_name,user_password,user_phone,user_workplace,user_job,user_admin,user_point) values (#{userId},#{userName},#{userPassword},#{userPhone},#{userWorkPlace},#{userJob},#{userAdmin},#{userPoint})",nativeQuery = true)
    void insert(User user);

}
