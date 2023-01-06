package com.myIT.SpringSecurity.Controllers;

import com.myIT.SpringSecurity.model.Customer;
import com.myIT.SpringSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer=null;
        ResponseEntity response=null;
        try{
           String hashPwd= passwordEncoder.encode(customer.getPwd());
           customer.setPwd(hashPwd);
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedCustomer=customerRepository.save(customer);
            if(savedCustomer.getId()>0){
                response=ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user Details are successfully registered");
            }
        }
        catch (Exception exp){
            response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception Occured due to: "+exp.getMessage());
        }
        return response;
    }
    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }

}
