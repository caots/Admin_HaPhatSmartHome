package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.UserMail;

public interface SendMailService {

     boolean sendMail(UserMail user, String header, String content);
}
