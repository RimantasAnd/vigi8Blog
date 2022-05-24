package lt.codeacademy.vigi8BLog.service;

import lt.codeacademy.vigi8BLog.entity.Post;
import lt.codeacademy.vigi8BLog.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


//    public Long countCommentByPostId(Long postId) {
//        return commentRepository.countFindAllByParentPostId(postId);
//    }

//    public List<Long> getcountersList(){
//
//    }
}
