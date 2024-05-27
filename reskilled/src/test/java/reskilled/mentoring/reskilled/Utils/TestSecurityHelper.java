package reskilled.mentoring.reskilled.Utils;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import reskilled.mentoring.reskilled.user.model.entity.User;

@Component
public class TestSecurityHelper {

    private static final String USER_NAME = "user01";


    public static User setMockUser(User user) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getEmail(), "password");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return User.builder().email(user.getEmail()).password(user.getPassword()).build();
    }

    @Before(value = "0")
    public void setup() {
        // Optional: Reset SecurityContextHolder for each test
        SecurityContextHolder.clearContext();
    }

    @After(value = "0")
    public void tearDown() {
        // Optional: Reset SecurityContextHolder after each test
        SecurityContextHolder.clearContext();
    }
}
