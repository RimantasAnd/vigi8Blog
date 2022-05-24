package lt.codeacademy.vigi8BLog.service;


import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.vigi8BLog.entity.Post;
import lt.codeacademy.vigi8BLog.exception.PostNotFoundException;
import lt.codeacademy.vigi8BLog.repository.CommentRepository;
import lt.codeacademy.vigi8BLog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Post create(Post post){
        return postRepository.save(post);
    }

    public Post findById(Long id){
        return postRepository
                .findById(id)
                .orElseThrow(PostNotFoundException::new);
    }


//    public Page<Post> findAllPageable(int pageSize, int pageNumber) {
//        Pageable pageable = Pageable
//                .ofSize(pageSize)
//                .withPage(pageNumber);
//
//        return postRepository.findByPostLockedByAdminFalsePageable(pageable);
//    }


    public Page<Post> findAllPageable(int pageSize, int pageNumber) {
        Page<Post> posts;
        Pageable pageable = Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);
        posts = postRepository.findByPostLockedByAdminFalsePageable(pageable);
//        for (Post post:posts) {
//            post.setCommentsCount(commentRepository.countFindAllByParentPostId(post.getId()));
//        }
        return posts;
    }





    public List<Post> findByPostLockedByAdminTrue() {


        return  postRepository.findByPostLockedByAdminTrue();
    }


}
