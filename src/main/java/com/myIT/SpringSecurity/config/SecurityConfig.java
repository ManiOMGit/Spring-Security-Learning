package com.myIT.SpringSecurity.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity .securityContext().requireExplicitSave(false)
                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()
                .csrf().disable()
                .authorizeHttpRequests()
                /*.requestMatchers("/api/v1/myAccount").hasAuthority("VIEWACCOUNT")
                .requestMatchers("/api/v1/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
                .requestMatchers("/api/v1/myCards").hasAuthority("VIEWCARDS")
                .requestMatchers("/api/v1/myLoans").hasAuthority("VIEWLOANS")*/
                .requestMatchers("/api/v1/myAccount").hasRole("USER")
                .requestMatchers("/api/v1/myBalance").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/v1/myCards").hasRole("USER")
                .requestMatchers("/api/v1/myLoans").hasRole("USER")
                .requestMatchers("/login/user")
                .authenticated()
                .requestMatchers("/api/v1/contact","/api/v1/notices","/login/register").permitAll();
        httpSecurity.formLogin();
        httpSecurity.httpBasic();
      return httpSecurity.build();
    }

   /* @Bean
    public InMemoryUserDetailsManager userDetailServcie(){
        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }*/


    // with password encoder
   /* @Bean
    public InMemoryUserDetailsManager userDetailServcie(){
        UserDetails admin= User.withUsername("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user= User.withUsername("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }
*/
   /* @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
