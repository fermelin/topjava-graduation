package com.github.fermelin.restaurants.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fermelin.restaurants.repository.UserRepository;
import com.github.fermelin.restaurants.model.Role;
import com.github.fermelin.restaurants.model.User;
import com.github.fermelin.restaurants.util.JsonUtil;
import com.github.fermelin.restaurants.util.UserUtil;
import com.github.fermelin.restaurants.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Autowired
    private void setMapper(ObjectMapper objectMapper) {
        JsonUtil.setMapper(objectMapper);
    }

    @Bean
    @Override
    // https://stackoverflow.com/a/70176629/548473
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> {
            log.debug("Authenticating '{}'", email);
            Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
            return new AuthUser(optionalUser.orElseThrow(() -> new UsernameNotFoundException("User '" + email + "' was not found")));
        }).passwordEncoder(UserUtil.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/admin/**")
            .hasRole(Role.ADMIN.name())
            .antMatchers(HttpMethod.POST, "/api/profile")
            .anonymous()
            .antMatchers("/api/**")
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf()
            .disable();
    }
}