package com.example.pc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/showCreate", "/savePc").hasAnyAuthority("ADMIN", "AGENT")
                .antMatchers("/modifierPc", "/updatePc", "/supprimerPc").hasAuthority("ADMIN")
                .antMatchers("/ListePcs", "/listePcs").hasAnyAuthority("ADMIN", "AGENT", "USER")
                .antMatchers("/login", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
        
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}