package com.myIT.SpringSecurity.config;

import com.myIT.SpringSecurity.model.Authority;
import com.myIT.SpringSecurity.model.Customer;
import com.myIT.SpringSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String pwd=authentication.getCredentials().toString();
        List<Customer> customers=customerRepository.findByEmail(username);
        if(customers.size()>0){
            if(passwordEncoder.matches(pwd,customers.get(0).getPwd())){
                return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthorities(customers.get(0).getAuthorities()));
            }else {
                throw new BadCredentialsException("Invalid password");
            }
        }
        else {
            throw new BadCredentialsException("No user registered with these details");
        }


    }
    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for(Authority authority:authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
