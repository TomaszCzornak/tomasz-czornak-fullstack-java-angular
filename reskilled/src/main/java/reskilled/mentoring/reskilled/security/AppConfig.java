package reskilled.mentoring.reskilled.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import reskilled.mentoring.reskilled.user.service.UsersService;

@RequiredArgsConstructor
public class AppConfig {

    private final UsersService usersService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(usersService);
    }

}
