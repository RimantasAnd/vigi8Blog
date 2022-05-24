package lt.codeacademy.vigi8BLog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name="temporary_nic")
    private String temporaryNic;

    @Column(name="user_id")
    private Long userId;

//    @Column(name="parent_post_id")
//    private Long parentPostId;

    @Column(name="comment_time")
    private LocalDateTime commentTimeStamp;

    @Column(name="comment_text")
    private String commentText;


    @Column(name="commented_ip")
    private String commentIp;

    @Column(name="admin_locked")
    private boolean commentLockedByAdmin;

    @Column(name="propose_to_delete")
    private boolean propToDelete;

    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="parent_post_id", nullable=false)
    private Post post;

}
