package advancedweb.project.boardservice.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    private String postNo;
    private PostType postType;
    private String title;
    private String content;
    private String writerNo;
    private LocalDateTime createdAt;

    private Post(PostType postType, String title, String content, String writerNo) {
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.writerNo = writerNo;
        this.createdAt = LocalDateTime.now();
    }

    public static Post create(PostType postType, String title, String content, String writerNo) {
        return new Post(postType, title, content, writerNo);
    }
}
