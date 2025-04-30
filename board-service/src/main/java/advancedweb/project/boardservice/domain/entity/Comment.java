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
public class Comment {

    @Id
    private String commentNo;
    private String content;
    private String writerNo;
    private String postNo;
    private LocalDateTime createdAt;

    private Comment(String content, String writerNo, String postNo) {
        this.content = content;
        this.writerNo = writerNo;
        this.postNo = postNo;
        this.createdAt = LocalDateTime.now();
    }

    public static Comment create(String content, String writerNo, String postNo) {
        return new Comment(content, writerNo, postNo);
    }
}
