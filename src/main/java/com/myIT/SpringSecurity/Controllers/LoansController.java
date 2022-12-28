package com.myIT.SpringSecurity.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoansController {
    @GetMapping("/myLoans")
    public String getLoanDetails(){
        return "Here are the details of Loans from db";
    }
}
