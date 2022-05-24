package lt.codeacademy.vigi8BLog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    @Size(min=6, message="{user.userName.toShort}")
    @Column(name="user_name",unique = true)
    private String userName;

    @NotBlank
    @Size(min=6, message="{user.password.toShort}")
    @Column(name="password")
    private String password;

    @Column(name="last_ip")
    private String lastIp;

    @Column(name="admin_lock")
    private Boolean adminLock;

    @Column(name="last_login_time")
    private LocalDateTime lastLoginTime;

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

    @Column(name="hostname")
    private String hostName;

    @Transient
    private String confirmPassword;

    @Transient
    private boolean passwdIsHashed=false;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", adminLock=" + adminLock +
                ", lastLoginTime=" + lastLoginTime +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
//                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }




}
