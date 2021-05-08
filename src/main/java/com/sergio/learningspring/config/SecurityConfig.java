package com.sergio.learningspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 1. Configure which routes are going to be authorized and how are they going to be authorized
                // Yes the `permitAll` and `.anyRequest().authenticated()` are confusing that but is how it is.
                // This means that these routes are reachable but only if you are authenticated.
                .antMatchers("/", "/api/*").permitAll()
                .anyRequest().authenticated()
                .and()
                // 2. Configure form login and whitelist the login page as public
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                // 3. Configure logout as public
                .logout()
                .permitAll();
    }

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure with in memory authentication. DON'T DO THIS FOR PROD.
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user").password("password").roles("USER");
    }
}
