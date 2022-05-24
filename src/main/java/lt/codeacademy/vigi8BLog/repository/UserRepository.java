package lt.codeacademy.vigi8BLog.repository;

import lt.codeacademy.vigi8BLog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("select u from User u where u.id = ?1")

//    SELECT * FROM `users` WHERE `user_name`="Admin";


    @Query("select (count(u) > 0) from User u where u.userName = ?1")
    boolean existsUserByUserName(String userName);

    //select (count(nick_name) > 0) from users where `users`.`user_name` = "Admin";

    @Query("select u from User u where u.userName = ?1")
    Optional<User> findByUserName(String userName);

    @Query("select u from User u where u.id = ?1")
    Optional<User> findById(Long id);







//@Override
//    Optional<User> save(User user);

}


