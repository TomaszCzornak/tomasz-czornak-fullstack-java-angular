package reskilled.mentoring.reskilled.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reskilled.mentoring.reskilled.user.exceptions.EmptyUserListException;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UsersService usersService;

    @RequestMapping("/users")
    @ResponseBody
    public List<User> getUsersList() {
        if (usersService.getAllUsers().isEmpty()) {
            throw new EmptyUserListException();
        }
        return usersService.getAllUsers();
    }
}
