package reskilled.mentoring.reskilled.Utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reskilled.mentoring.reskilled.registration.model.request.RegistrationRequest;

import java.util.Collection;
import java.util.Collections;

@UtilityClass
public class RegistrationStub {

    public static RegistrationRequest createRegistrationRequest() {
        return RegistrationRequest.builder()
                .email("registration@mail.com")
                .firstName("Testowy")
                .lastName("Ziomek")
                .password("Dupsko37!")
                .passwordRepeat("Dupsko37!")
                .build();
    }

    public static UserDetails createUserDetails() {
        UserDetails userDetails = new UserDetails() {
            @Override
            public String getUsername() {
                return "john.doe@gmail.com";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public String getPassword() {
                return "password"; // You can use a password encoder if needed
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            }

        };
        return userDetails;
    }
}


