package reskilled.mentoring.reskilled.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.login.model.LoginRequest;
import reskilled.mentoring.reskilled.security.Role;
import reskilled.mentoring.reskilled.user.model.entity.User;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserStub {

    public static LoginRequest createLoginRequest() {
        return LoginRequest.builder()
                .email("testowyLogingEmail@mail.com")
                .password("Komputer37!")
                .build();
    }

    public static User createUser() {
        return User.builder()
                .id(1L)
                .uuid(UUID.randomUUID().toString())
                .createdAt("2022-01-01")
                .updatedAt("2022-01-01")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@mail.com")
                .password("Passwording85!")
                .isLock(false)
                .isEnabled(true)
                .role(Role.USER)
                .build();
    }

    public static User createUserBeforeRegistration() {
        return User.builder()
                .id(1L)
                .uuid(UUID.randomUUID().toString())
                .createdAt("2022-01-01")
                .updatedAt("2022-01-01")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@mail.com")
                .password("Passwording85!")
                .isLock(true)
                .isEnabled(false)
                .role(Role.USER)
                .build();
    }
}
