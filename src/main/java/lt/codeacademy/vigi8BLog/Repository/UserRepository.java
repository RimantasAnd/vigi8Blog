package lt.codeacademy.vigi8BLog.Repository;

import lt.codeacademy.vigi8BLog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("select u from User u where u.id = ?1")
//    Optional<User> findByNickName(String nickName);
//    SELECT * FROM `users` WHERE `nick_name`="Admin";

    @Query("select (count(u) > 0) from User u where u.nickName = ?1")
    boolean existsUserByNickName(String nickName);
    //select (count(nick_name) > 0) from users where `users`.`nick_name` = "Admin";

    @Query("select u from User u where u.nickName = ?1")
    Optional<User> findByNickName(String nickName);



}


