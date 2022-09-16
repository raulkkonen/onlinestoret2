package com.mataases.security.infrastructure.config;



import com.mataases.security.interfaces.rest.auth.JWTAuthenticationFilter;
import com.mataases.security.interfaces.rest.auth.JWTLoginFilter;
import com.mataases.security.application.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisService redisService;

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder()) ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/*").permitAll()
                .antMatchers(HttpMethod.POST, "/security/login").permitAll()
                .antMatchers(HttpMethod.POST, "/security/users").permitAll()
                .anyRequest().authenticated()
                .and()
                // redireccionamos a la cuenta login
                .addFilterBefore(new JWTLoginFilter("/security/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // Filtar las rutas JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(redisService),
                        UsernamePasswordAuthenticationFilter.class);

    }
}