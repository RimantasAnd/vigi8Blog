package lt.codeacademy.vigi8BLog.Repository;

import lt.codeacademy.vigi8BLog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();


    Optional<Post> findById(long id);
}