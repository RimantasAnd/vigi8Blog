package lt.codeacademy.vigi8BLog.Service;


import lt.codeacademy.vigi8BLog.Entity.Post;
import lt.codeacademy.vigi8BLog.Exception.PostNotFoundException;
import lt.codeacademy.vigi8BLog.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
//        return postRepository.findAll(pageable);
//    }
    public Page<Post> findAllPageable(int pageSize, int pageNumber) {
        Pageable pageable = Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);

        return postRepository.findByPostLockedByAdminFalsePageable(pageable);
    }






    public List<Post> findByPostLockedByAdminTrue() {


        return  postRepository.findByPostLockedByAdminTrue();
    }


}
