package advancedweb.project.userservice.application.dto.request;

public record SignUpReq(
        String username,
        String password,
        String nickname
) {}
