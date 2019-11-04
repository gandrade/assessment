package com.mytaxi.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Configure basic authentication for accessing API endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;

    @Value("${security.roles}")
    private String[] roles;


    @Bean
    public UserDetailsService userDetailsService()
    {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        inMemoryUserDetailsManager.createUser(User.withUsername(username)
            .password(encoder.encode(password))
            .roles(roles)
            .build());
        return inMemoryUserDetailsManager;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .mvcMatchers("/").permitAll()
            .mvcMatchers("/v1/**").authenticated()
            .and()
            .httpBasic()
            .and()
            .formLogin();

        allowH2Console(http);
    }


    private void allowH2Console(HttpSecurity http) throws Exception
    {
        http.csrf()
            .disable()
            .headers()
            .frameOptions().disable();
    }
}