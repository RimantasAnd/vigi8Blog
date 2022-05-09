package lt.codeacademy.vigi8BLog.Controller;




import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.vigi8BLog.Entity.User;
import lt.codeacademy.vigi8BLog.Exception.UserNotFoundException;
import lt.codeacademy.vigi8BLog.Service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;



@Slf4j
@Controller
@RequestMapping(path="/users")
public class UserController {
    private UserService userService;
    private HttpServletRequest request;


    public UserController(UserService userService) {
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

    @GetMapping(path = "/{id}")
    public String getUserPage(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "showUser", required = false) boolean showUser,
            Model model
    ) {
        User user = userService.findById(id);
        model.addAttribute("userId", user.getId());
        model.addAttribute("userNickName", user.getNickName());
        model.addAttribute("userPassword", user.getPassword());
        return "userPage";
    }
    @GetMapping("/user")
    public String getUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userCreateForm";
    }

    @PostMapping("/create")
    public String createPost(@Valid User user, BindingResult errors, Model model) {
        if (errors.hasErrors()){
            return "userCreateForm";
        }
        user.setLastIp(getClientIp());
        user.setLastLoginTime(LocalDateTime.now());
        user.setAdminLock(false);
        User createdUser = userService.create(user);
        model.addAttribute("user", createdUser);
        log.trace("***********************************Cia is kontrolerio   "+user.toString());
        return "redirect:/users/" + createdUser.getId();
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class,})
    public String handleContentNotAllowedException(SQLIntegrityConstraintViolationException sqlicve, Model model)
//@ExceptionHandler(DataIntegrityViolationException.class)
//public String handleContentNotAllowedException(DataIntegrityViolationException sqlicve, Model model)
    {
        log.trace("******   SQLIntegrityConstraintViolationException *****************");
        log.trace(sqlicve.toString());
        log.trace("******   SQLIntegrityConstraintViolationException *****************");

        List<String> errors = Collections.singletonList(sqlicve.getMessage());
        log.trace("*******  Errors *************");
        log.trace(errors.toString());
        log.trace("*******  Errors *************");

        model.addAttribute("errors", errors);
        log.trace("*******  Models Errors *************");
        log.trace((Marker) model,"errors");
        log.trace("*******  Models Errors *************");
      return "user";
    }
}
