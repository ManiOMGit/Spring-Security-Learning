package com.myIT.SpringSecurity.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CardsController {

    @GetMapping("/myCards")
    public String getCardDetails(){
        return "Here are the cards details from db";
    }
}
