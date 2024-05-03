package reskilled.mentoring.reskilled.user.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UsersService usersService;
    @InjectMocks
    private UserController userController;
    @Test
    void getUsersList() {
        //given
        List<UserResponse> expectedUsers = List.of(UserStub.createUserResponse(), UserStub.createUserResponse());
        given(usersService.getAllUsers()).willReturn(expectedUsers);
        //then
        List<UserResponse> usersRetrieved = userController.getUsersList();
        //then
        assertEquals(expectedUsers, usersRetrieved);

    }
}