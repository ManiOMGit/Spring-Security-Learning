package com.myIT.SpringSecurity.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices(){
        return "Here are the notices details from db";
    }
}
