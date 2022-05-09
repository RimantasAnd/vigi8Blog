package lt.codeacademy.vigi8BLog.Controller;

import lt.codeacademy.vigi8BLog.Entity.Post;
import lt.codeacademy.vigi8BLog.Service.PostService;
import lt.codeacademy.vigi8BLog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path="/")
public class MainController {

    private HttpServletRequest request;

    private PostService postService;

    private UserService userService;


    public MainController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
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
        Integer itemsPerPage=12;
        Page<Post> postPage = postService.findAllPageable(itemsPerPage,pageNumber);
        List<Post> posts = postPage.getContent();
        model.addAttribute("posts",posts);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",postPage.getTotalPages());
        model.addAttribute("itemsPerPage",itemsPerPage);
        return "index";
    }

}
