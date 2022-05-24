package lt.codeacademy.vigi8BLog.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.vigi8BLog.controller.dto.UserUpdateContactsDTO;
import lt.codeacademy.vigi8BLog.controller.dto.UserUpdatePasswordDTO;
import lt.codeacademy.vigi8BLog.entity.User;
import lt.codeacademy.vigi8BLog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;


@Slf4j
@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

//    private HttpServletRequest request;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping(path = "/{id}")
    public String getUserPage(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "showUser", required = false) boolean showUser,
            Model model
    ) {
        User user = userService.findById(id);
        model.addAttribute("userId", user.getId());
        model.addAttribute("userNickName", user.getUserName());
        model.addAttribute("userPassword", user.getPassword());
        return "userPage";
    }

    @GetMapping("/user")
    public String getUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userCreateForm";
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, BindingResult errors, Model model) {
        boolean isUserNameExist = this.userService.checkUserByUserName(user.getUserName());
        if (isUserNameExist) {
            errors.rejectValue("userName", "userNameAlreadyExist", " The user name already exists ");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "passwordNotMatch", " Password not match ");
        }
        if (errors.hasErrors()) {
            return "userCreateForm";
        }
        User createdUser = userService.create(user);
        model.addAttribute("user", createdUser);
        return "redirect:/users/" + createdUser.getId();
    }

    @PostMapping("/update/{id}")
    public String updateUserPost(@PathVariable(name = "id") Long id,@Valid User user,BindingResult errors, Model model) {
        user.setId(id);
        log.info(user.toString());
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "passwordNotMatch", " Password not match ");
        }
        if (errors.hasErrors()) {
            log.info("yra klaidu");
            log.info(errors.toString());
            return "userUpdateForm";
        }
        user.setAdminLock(false);
        User createdUser = new User();
        log.info(createdUser.toString());
       createdUser = userService.update(user);

        model.addAttribute("user", createdUser);
        return "redirect:/users/" + createdUser.getId();
    }

    @GetMapping("/update/{id}")
    public String updateUserGet(@PathVariable(name = "id") Long id, User user,Model model) {
        user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getId());
        return "userUpdateForm";
    }

//    @PostMapping("/updateContacts/{userName}")
//    public String updateUserPost(@PathVariable(name = "userName") String userName
//            ,@Valid User user
//            ,BindingResult returnedErrors
//            , Model model) {
//
//        user.setUserName(userName);
//
//        List<ObjectError> errors = returnedErrors.getAllErrors();
//
//        boolean hasIgnorableErrorsOnly = true;
//        List<String> ignorableErrorsPaterns =
//                new ArrayList<String>(List.of(
//                        "NotBlank.user.password"
////suitable for other form validation
////                        ,"Size.user.email"
////                        ,"NotBlank.user.surname"
////                        ,"NotBlank.user.name"
//                ));
//
//        for (ObjectError error : errors) {
//            boolean errorIsIgnorable = false;
//            for (String errPatern : ignorableErrorsPaterns) {
//                boolean errorInPatern = Arrays.stream(error.getCodes()).anyMatch(element -> element.equals(errPatern));
//                if (errorInPatern) {
//                    errorIsIgnorable = true;
//                }
//            }
//            if (!errorIsIgnorable) {
//                hasIgnorableErrorsOnly = false;
//            }
//        }//in case of any error from this list mark hasIgnorableErrorsOnly = false;
//        if (returnedErrors.hasErrors() & !hasIgnorableErrorsOnly) {
//            return "userUpdateContacts";
//        }

    @PostMapping("/updateContacts/{userName}")
    public String updateUserContactsPost(@PathVariable(name = "userName") String userName
            , @Valid UserUpdateContactsDTO user
            , BindingResult errors
            , Model model) {
//        user.setUserName(userName);
        if (errors.hasErrors()) {
            return "userUpdateContacts";
        }
        User createdUser = new User();
        createdUser = userService.updateContacts(UserUpdateContactsDTO.userFromUserDTO(createdUser, user));
        model.addAttribute("user", createdUser);
        return "redirect:/users/" + createdUser.getId();
    }//Post mapping

    @GetMapping("/updateContacts/{userName}")
    public String updateUserContactsGet(@PathVariable(name = "userName") String userName, Model model) {
        UserUpdateContactsDTO reducedUser = UserUpdateContactsDTO.userDTOFromUser(userService.findByUserName(userName));
        model.addAttribute("user", reducedUser);
        model.addAttribute("userName", reducedUser.getUserName());
        return "userUpdateContacts";
    }

    @GetMapping("/updatePassword/{userName}")
    public String updateUserPasswordGet(@PathVariable(name = "userName") String userName, Model model) {
        UserUpdatePasswordDTO userDTO = UserUpdatePasswordDTO.userDTOFromUser(userService.findByUserName(userName));
        model.addAttribute("userDTO", userDTO);
        return "userUpdatePassword";
    }

    @PostMapping("/updatePassword/{userName}")
    public String updateUserPasswordPost(@PathVariable(name = "userName") String userName
            , @Valid @ModelAttribute("userDTO") UserUpdatePasswordDTO userDTO
            , BindingResult errors
            , Model model) {

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "passwordNotMatch", " Password not match ");
        }

        if (errors.hasErrors()) {
            return "userUpdatePassword";
        }

        User createdUser = new User();
        createdUser = userService.updatePassword(UserUpdatePasswordDTO.userFromUserDTO(createdUser, userDTO));
        model.addAttribute("userDTO", createdUser);
        return "redirect:/users/" + createdUser.getId();
    }//Post mapping

}
