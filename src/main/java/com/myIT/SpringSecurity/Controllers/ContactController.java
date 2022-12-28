package com.myIT.SpringSecurity.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ContactController {

    @GetMapping("/contact")
    public String saveContactInquiryDetails(){
        return "Inquiry details saved to db";
    }
}
