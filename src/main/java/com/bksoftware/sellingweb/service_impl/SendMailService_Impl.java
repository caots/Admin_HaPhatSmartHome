package com.bksoftware.sellingweb.service_impl;


import com.bksoftware.sellingweb.entities.UserMail;
import com.bksoftware.sellingweb.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SendMailService_Impl implements SendMailService {

    /*
     * Trên thực tế, Java cung cấp hai lớp để gửi thư. Nếu bạn muốn gửi thư đơn giản mà không có tệp đính kèm thì đối
     * tượng của SimpleMailMessage được sử dụng
     * nếu không sử dụng Đối tượng của MimeMessage .
     * */

    private static final Logger LOGGER = Logger.getLogger(SendMailService_Impl.class.getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String emailAdminAddress;

    @Override
    public boolean sendMail(UserMail user, String header, String content) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmailAddress());
            mail.setFrom(emailAdminAddress);
            mail.setSubject(header);
            mail.setText(content);
            javaMailSender.send(mail);
            return true;
        } catch (MailException ex) {
            LOGGER.log(Level.SEVERE, "send-mail-error : {0}", ex.getMessage());
        }
        return false;

    }

    //Gửi mail với tệp đính kèm
/*
    public void sendEmailWithAttachment(UserMail user) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(user.getEmailAddress());
            helper.setSubject("Testing Mail API with Attachment");
            helper.setText("Please find the attached document below.");

            FileSystemResource file = new FileSystemResource("/home/caots/Desktop/abc.pdf");
            helper.addAttachment(file.getFilename(), file);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            LOGGER.log(Level.SEVERE, "send-mail-with-attachment-error : {0}", ex.getMessage());
        }

    }
*/

}
