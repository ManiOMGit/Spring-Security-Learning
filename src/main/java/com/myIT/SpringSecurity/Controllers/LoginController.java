package com.myIT.SpringSecurity.Controllers;

import com.myIT.SpringSecurity.model.Customer;
import com.myIT.SpringSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    CustomerRepository customerRepository;

    @Autowired
    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer=null;
        ResponseEntity response=null;
        try{
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

}
