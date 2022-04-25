package lt.codeacademy.vigi8BLog.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name="title")
    private String title;

    @Column(name="post_text")
    private String postText;

    @Column(name="author_id")
    private Long authorId;

    @Column(name="post_time")
    private LocalDateTime postTimeStamp;

    @Column(name="posted_ip")
    private String postIp;

    @Column(name="admin_locked")
    private boolean postLockedByAdmin;

    @Column(name="comments_locked")
    private boolean commentsLocked;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postText='" + postText + '\'' +
                ", authorId=" + authorId +
                ", postIpAddress='" + postIp + '\'' +
                ", postBlockedByAdmin=" + postLockedByAdmin +
                ", postDayTime=" + postTimeStamp +
                ", commentsLocked=" + commentsLocked +
                '}';
    }


}
