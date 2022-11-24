package com.example.backend.security;

import com.example.backend.service.AppUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final AppUserDetailService appUserDetailService;

    public SecurityConfig(AppUserDetailService appUserDetailService) {
        this.appUserDetailService = appUserDetailService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test").hasRole("DEVELOPER")
                .antMatchers("/ciao").hasRole("CUSTOMER")
                .antMatchers("/hello").authenticated()
                .antMatchers("/user/*").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/hola").permitAll()
              //  .antMatchers("/api/ciao").permitAll()
                .and().httpBasic().and().csrf().disable();
    }

    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService);



       /* auth.inMemoryAuthentication()
                .withUser("User123")
                .password(passwordEncoder().encode("ABC123"))
                .roles("CUSTOMER")
                .and()
                .withUser("Developer123")
                .password(passwordEncoder().encode("Chef123"))*/
    }
}
