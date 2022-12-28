package com.myIT.SpringSecurity.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalanceDetails(){
        return "Here are the details from db";
    }
}
