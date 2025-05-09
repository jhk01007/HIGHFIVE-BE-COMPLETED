package advancedweb.project.userservice.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String userNo;      // PK
    private String username;    // 로그인 아이디
    private String password;    // 로그인 비밀번호

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User create(String username, String password) {
        return new User(username, password);
    }
}
