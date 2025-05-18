package advancedweb.project.boardservice.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    private String postNo;
    private String title;
    private String content;
    private String writerNo;
    private LocalDateTime createdAt;

    private Post(String title, String content, String writerNo) {
        this.title = title;
        this.content = content;
        this.writerNo = writerNo;
        this.createdAt = LocalDateTime.now();
    }

    public static Post create(String title, String content, String writerNo) {
        return new Post(title, content, writerNo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post other = (Post) o;
        return Objects.equals(postNo, other.postNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postNo);
    }
}
