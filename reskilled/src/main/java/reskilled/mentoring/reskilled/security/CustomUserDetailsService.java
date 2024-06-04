package reskilled.mentoring.reskilled.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reskilled.mentoring.reskilled.user.model.entity.User;
import reskilled.mentoring.reskilled.user.service.UsersService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersService usersService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = usersService.getUsersByEmail(email);
        return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found with email " + email));
    }
}
