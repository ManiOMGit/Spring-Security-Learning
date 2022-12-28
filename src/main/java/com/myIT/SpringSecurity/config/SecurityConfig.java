package com.myIT.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/api/v1/myAccount","/api/v1/myBalance","/api/v1/myCards","/api/v1/myLoans","/")
                .authenticated()
                .requestMatchers("/api/v1/contact","/api/v1/notices").permitAll();
        httpSecurity.formLogin();
        httpSecurity.httpBasic();
      return httpSecurity.build();
    }
}
