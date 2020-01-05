package ru.dnina.server.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.dnina.server.models.Role;
import ru.dnina.server.security.filters.TokenAuthFilter;


@ComponentScan("ru.dnina.server")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/api/signOut").authenticated()
                .antMatchers("/api/users").authenticated()
                .antMatchers("/api/users/{user-id}").authenticated()
                .antMatchers("/api/users/authorized-user").authenticated()
                .antMatchers("/api/users/{user-id}/update").authenticated()
                .antMatchers("/api/users/{user-id}/update-pass").authenticated()
                .antMatchers("/api/users/{user-id}/update-role").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/api/signIn").permitAll()
                .antMatchers("/api/signUp").permitAll()
                  .and()
                .csrf().disable();
    }
}
