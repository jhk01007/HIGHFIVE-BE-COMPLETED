package advancedweb.project.userservice.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    private String userNo;      // PK
    private String nickname;    // 닉네임
    private String username;    // 로그인 아이디
    private String password;    // 로그인 비밀번호

}
