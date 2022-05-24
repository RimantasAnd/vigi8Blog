package lt.codeacademy.vigi8BLog.controller.dto;

import lombok.Data;
import lt.codeacademy.vigi8BLog.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdatePasswordDTO {
    private  Long id;

    @NotBlank
    @Size(min=6, message="{user.userName.toShort}")
    private String userName;

    @NotBlank
    @Size(min=6, message="{user.password.toShort}")
    private String password;

    @NotBlank
    @Size(min=6, message="{user.password.toShort}")
    private String confirmPassword;

    public static UserUpdatePasswordDTO userDTOFromUser(User user){
        UserUpdatePasswordDTO newUser = new UserUpdatePasswordDTO();
        newUser.setId(user.getId());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setConfirmPassword(user.getConfirmPassword());
        return newUser;
    }

    public static User userFromUserDTO(User newUser,UserUpdatePasswordDTO user){
        newUser.setId(user.getId());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setConfirmPassword(user.getConfirmPassword());
        return newUser;
    }


}
