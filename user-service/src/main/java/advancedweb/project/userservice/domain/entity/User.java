package advancedweb.project.userservice.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String userNo;      // PK
    private String nickname;    // 닉네임
    private String username;    // 로그인 아이디
    private String password;    // 로그인 비밀번호

    private User(String nickname, String username, String password) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
    }

    public static User create(String nickname, String username, String password) {
        return new User(nickname, username, password);
    }
}
