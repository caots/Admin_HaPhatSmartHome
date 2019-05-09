package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.commom.Token;
import com.bksoftware.sellingweb.entities.UserMail;
import com.bksoftware.sellingweb.service_impl.SendMailService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/send-email")
public class SendMailController {

    @Autowired
    private SendMailService_Impl sendMailService;

    @Autowired
    private UserMail user;

    @GetMapping
    public ResponseEntity<Object> sendEmail(
            @RequestParam("email") String email,
            @RequestParam("header") String header,
            @RequestParam("content") String content,
            @RequestHeader("adminbksoftwarevn") String tokenHeader
    ) {
        if (tokenHeader.equals(Token.tokenHeader)) {
            user.setEmailAddress(email);
            boolean result = sendMailService.sendMail(user, header, content);
            if (result)
                return new ResponseEntity<>("Congratulations! Your mail has been send to the user.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
