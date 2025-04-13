package hr.tomek.czornak.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import hr.tomek.czornak.user.service.UsersService;

@RequiredArgsConstructor
public class AppConfig {

    private final UsersService usersService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(usersService);
    }

}
