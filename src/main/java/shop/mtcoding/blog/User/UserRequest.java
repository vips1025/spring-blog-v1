package shop.mtcoding.blog.User;

import lombok.Data;

/**
 * 요청 DTO = Data Transfer Object
 */
public class UserRequest {
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
