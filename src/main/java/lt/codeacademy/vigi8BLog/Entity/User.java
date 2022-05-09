package lt.codeacademy.vigi8BLog.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    @Size(min=6, message="{user.nickName.toShort}")
//    @Column(name="nick_name")
    @Column(name="nick_name",unique = true)
    private String nickName;

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


//    @Column(name="passwd_hashed")
//    private boolean passwdHashed;
//
//    public boolean isPasswdHashed() {
//        return passwdHashed;
//    }
}
