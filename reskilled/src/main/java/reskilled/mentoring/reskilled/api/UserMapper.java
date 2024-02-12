package reskilled.mentoring.reskilled.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.model.RegistrationRequest;
import reskilled.mentoring.reskilled.model.User;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .updatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();
    }

    public static User toUser(RegistrationRequest registrationRequest) {
        return User.builder()
                .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .updatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .build();
    }
}
