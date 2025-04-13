package hr.tomek.czornak.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hr.tomek.czornak.user.exceptions.EmptyUserListException;
import hr.tomek.czornak.user.model.response.UserResponse;
import hr.tomek.czornak.user.service.UsersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UsersService usersService;

    @RequestMapping("/users")
    public List<UserResponse> getUsersList() {
        if (usersService.getAllUsers().isEmpty()) {
            throw new EmptyUserListException();
        }
        return usersService.getAllUsers();
    }
}
