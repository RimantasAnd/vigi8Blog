package lt.codeacademy.vigi8BLog.controller;

import lt.codeacademy.vigi8BLog.entity.Post;
import lt.codeacademy.vigi8BLog.entity.User;
import lt.codeacademy.vigi8BLog.service.CommentService;
import lt.codeacademy.vigi8BLog.service.PostService;
import lt.codeacademy.vigi8BLog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path="/")
public class MainController {

    private PostService postService;

//    private UserService userService;

    private CommentService commentService;


    public MainController(PostService postService,CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }




    @GetMapping
    public String getPostList(
            @RequestParam(name="page",defaultValue = "0") int pageNumber,
            Model model
    ){

        Integer itemsPerPage=12;
        Page<Post> postPage = postService.findAllPageable(itemsPerPage,pageNumber);
        List<Post> posts = postPage.getContent();
        model.addAttribute("posts",posts);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",postPage.getTotalPages());
        model.addAttribute("itemsPerPage",itemsPerPage);
//        for (Post post:posts) {
//int count=post.getComment().size();
//        }
        return "index";
    }



}
