package lt.codeacademy.vigi8BLog.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.vigi8BLog.entity.Comment;
import lt.codeacademy.vigi8BLog.entity.Post;
import lt.codeacademy.vigi8BLog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Controller
@RequestMapping(path="/posts")
public class PostController {

    private HttpServletRequest request;

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    private  String getClientIp() {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    @GetMapping
    public String getPostList(
           @RequestParam(name="page",defaultValue = "0") int pageNumber,
           Model model
    ){

        Page<Post> postPage = postService.findAllPageable(5,pageNumber);
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
       Post post = postService.findById(id);
        model.addAttribute("postId", post.getId());
        model.addAttribute("postTitle", post.getTitle());
        model.addAttribute("postText", post.getPostText());
        model.addAttribute("postPostIp", post.getPostIp());
        return "postpage";
    }

    @GetMapping("/post")
    public String getPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "postForm";
    }

    @PostMapping("/create")
    public String createPost(Post post, Model model) {
        post.setAuthorId(1L);//shoud be loged user id
        post.setPostIp(getClientIp());
        post.setPostTimeStamp(LocalDateTime.now());
        Post createdPost = postService.create(post);
        model.addAttribute("post", createdPost);
        return "redirect:/posts/" + createdPost.getId();
    }

    @GetMapping("/locked")
    public String lockedPosts( Model model) {
        List<Post> posts = postService.findByPostLockedByAdminTrue();
//        List<Post> posts = postPage.getContent();
        model.addAttribute("posts",posts);
//        dto.addAttribute("postId", post.getId());
//        dto.addAttribute("postTitle", post.getTitle());
//        dto.addAttribute("postText", post.getPostText());
//        dto.addAttribute("postPostIp", post.getPostIp());
        return "lockedPosts";
     }

    @GetMapping("/post/read/{id}")
    public String postGet(@PathVariable(name = "id") Long id, Post post, Model model) {
        post = postService.findById(id);
        model.addAttribute("post", post);
        for (Comment comment:post.getComment()) {
        }
        return "postRead";
    }

}
