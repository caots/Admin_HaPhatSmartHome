package com.bksoftware.sellingweb.entities;

import org.springframework.stereotype.Component;

@Component
public class UserMail {

    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
