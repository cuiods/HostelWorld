package edu.nju.web.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Hostel world security config
 * @author cuihao
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Resource
    private AuthSecurityService authSecurityService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(authSecurityService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/user/auth").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .antMatchers("/api/admin/**").access("hasRole('admin')")
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .cors()
        ;
    }

}
