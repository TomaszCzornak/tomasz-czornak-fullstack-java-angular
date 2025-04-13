package hr.tomek.czornak.Utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import hr.tomek.czornak.user.model.entity.User;
import hr.tomek.czornak.user.repository.UserRepository;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserContextHelper {

    private final UserRepository users;

    public static User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.equals(authentication.getPrincipal(), "anonymousUser")) {
            return User.builder().build();
        } else {
            return (User) authentication.getPrincipal();
        }
    }

}
