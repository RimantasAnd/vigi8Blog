package lt.codeacademy.vigi8BLog.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
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

//    @Transient
//    private Long commentsCount= Long.valueOf(0);


    @OneToMany(mappedBy="post",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)

    private Set<Comment> comment;


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postText='" + postText + '\'' +
                ", authorId=" + authorId +
                ", postTimeStamp=" + postTimeStamp +
                ", postIp='" + postIp + '\'' +
                ", postLockedByAdmin=" + postLockedByAdmin +
                ", commentsLocked=" + commentsLocked +
//                ", commentsCount=" + commentsCount +
                '}';
    }



}
