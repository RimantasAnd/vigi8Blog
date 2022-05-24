package lt.codeacademy.vigi8BLog.repository;

import lt.codeacademy.vigi8BLog.entity.Comment;
import lt.codeacademy.vigi8BLog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();

//    @Query("select c from Comment c where c.parentPostId = ?1")
//    List<Comment> findAllByParentPostId(Long postId);

//    @Query("select count(c) from Comment c where c.parentPostId = ?1")
////    SELECT COUNT(*) from comments WHERE parent_post_id = 1;
//    Long countFindAllByParentPostId(Long postId);

}
