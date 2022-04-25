package lt.codeacademy.vigi8BLog.Controller;


import lt.codeacademy.vigi8BLog.Entity.Post;
import lt.codeacademy.vigi8BLog.Exception.PostNotFoundException;
import lt.codeacademy.vigi8BLog.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/posts")
public class PostController {
   private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String getPostList(
           @RequestParam(name="page",defaultValue = "0") int pageNumber,
           Model model
    ){
        Pageable pageable = Pageable
                .ofSize(5)
                .withPage(pageNumber);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent();
        model.addAttribute("posts",posts);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",postPage.getTotalPages());
        return "postList";
    }

    @GetMapping(path = "/{id}")
    public String getPostPage(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "showPost", required = false) boolean showPost,
            Model model
    ) {
        Optional<Post> foundPost = postRepository.findById(id);

        if (foundPost.isEmpty()) {
            throw new PostNotFoundException();
        }

        Post post = foundPost.get();

        model.addAttribute("postId", post.getId());
        model.addAttribute("postTitle", post.getTitle());
        model.addAttribute("postText", post.getPostText());

        return "postpage";
    }

    @GetMapping("/post")
    public String getPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "postForm";
    }

    @PostMapping("/create")
    public String createPost(Post post, Model model) {
        post.setAuthorId(1L);
        post.setPostIp("172.16.24.1");
        post.setPostTimeStamp(LocalDateTime.now());
        Post createdPost = postRepository.save(post);
        model.addAttribute("post", createdPost);
        return "redirect:/posts/" + createdPost.getId();
    }



}
