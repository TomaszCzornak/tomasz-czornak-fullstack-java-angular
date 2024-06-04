package reskilled.mentoring.reskilled.user.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reskilled.mentoring.reskilled.Utils.UserStub;
import reskilled.mentoring.reskilled.user.model.response.UserResponse;
import reskilled.mentoring.reskilled.user.service.UsersService;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UsersService usersService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        openMocks(usersService);
    }

    @Test
    void getUsersList() throws Exception {
        //given
        List<UserResponse> expectedUsers = List.of(UserStub.createUserResponse(), UserStub.createUserResponse());
        //then
        given(usersService.getAllUsers()).willReturn(expectedUsers);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", Matchers.equalTo(expectedUsers.size())))
                .andExpect(jsonPath("$[0].email", Matchers.equalTo(expectedUsers.getFirst().getEmail())))
                .andDo(print());
    }
}