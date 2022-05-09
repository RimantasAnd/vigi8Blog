package lt.codeacademy.vigi8BLog.Repository;

import lt.codeacademy.vigi8BLog.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p")
    List<Post> findAll();


    @Query("select p from Post p where p.postLockedByAdmin = true")
    List<Post> findByPostLockedByAdminTrue();

//    @Query("select p from Post p where p.postLockedByAdmin = false")
//    List<Post> findByPostLockedByAdminFalse();

    @Query("select p from Post p where p.postLockedByAdmin = false")
    Page<Post> findByPostLockedByAdminFalsePageable(Pageable pageable);

//    Optional<Post> findById(long id);
}