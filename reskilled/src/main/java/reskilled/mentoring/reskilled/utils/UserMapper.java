package reskilled.mentoring.reskilled.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reskilled.mentoring.reskilled.registration.model.request.RegistrationRequest;
import reskilled.mentoring.reskilled.security.Role;
import reskilled.mentoring.reskilled.user.model.dto.UserDto;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
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
                .role(Role.USER)
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toUser(UserDto userDto) {
        return User.builder()
                .createdAt(userDto.getCreatedAt())
                .build();
    }

}
