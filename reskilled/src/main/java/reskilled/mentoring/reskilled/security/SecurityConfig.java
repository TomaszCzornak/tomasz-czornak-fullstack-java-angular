package reskilled.mentoring.reskilled.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
                .authorizeHttpRequests()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin(withDefaults())
                .csrf().ignoringRequestMatchers("/h2-console/**")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .build();
    }
    }
