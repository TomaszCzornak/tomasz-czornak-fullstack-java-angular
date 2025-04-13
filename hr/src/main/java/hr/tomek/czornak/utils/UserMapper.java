package hr.tomek.czornak.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import hr.tomek.czornak.registration.model.request.RegistrationRequest;
import hr.tomek.czornak.security.Role;
import hr.tomek.czornak.user.model.dto.UserDto;
import hr.tomek.czornak.user.model.entity.User;
import hr.tomek.czornak.user.model.response.UserResponse;

import java.sql.Timestamp;
import java.util.List;

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

    public static UserDto toUserDtoRecruitment(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();
    }

    public static List<UserResponse> toUserResponses(List<User> userList) {
        return userList.stream()
                .map(UserMapper::toUserResponse)
                .toList();
    }
}
