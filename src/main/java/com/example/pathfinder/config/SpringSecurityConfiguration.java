package com.example.pathfinder.config;

import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.CurrentUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                    authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                    antMatchers("/", "/login", "/register", "/about", "/api/**").permitAll().
                    anyRequest().authenticated().
                and().
                    formLogin().loginPage("/login").
                    usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                    passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                    defaultSuccessUrl("/").
                    failureForwardUrl("/login-error").
                and().
                    logout().
                    logoutUrl("/logout").
                    logoutSuccessUrl("/login").
                    invalidateHttpSession(true).
                deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new CurrentUserDetailsService(userRepository);
    }

//    @Bean
//    public AuthenticationManager authManager(UserDetailsService userDetailsService){
//        var authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        return new ProviderManager(authProvider);
//    }
}
