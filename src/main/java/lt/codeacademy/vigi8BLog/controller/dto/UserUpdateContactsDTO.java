package lt.codeacademy.vigi8BLog.controller.dto;

import lombok.Data;
import lt.codeacademy.vigi8BLog.entity.User;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class UserUpdateContactsDTO {

    private  Long id;

    @NotBlank
    @Size(min=6, message="{user.userName.toShort}")
    private String userName;

    @NotBlank
    @Column(name="name")
    private String name;

    @NotBlank
    @Column(name="surname")
    private String surname;

    @Email
    @Size(min=6, message="{user.email.toShort}")
    @Column(name="email")
    private String email;

    public static UserUpdateContactsDTO userDTOFromUser(User user){
        UserUpdateContactsDTO newUser = new UserUpdateContactsDTO();
        newUser.setId(user.getId());
        newUser.setUserName(user.getUserName());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        return newUser;
    }

    public static User userFromUserDTO(User newUser,UserUpdateContactsDTO user){
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setUserName(user.getUserName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        return newUser;
    }

}
