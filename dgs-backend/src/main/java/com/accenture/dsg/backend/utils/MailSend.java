package com.accenture.dsg.backend.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailSend {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String Body,String subject,String Bcc,String Cc,String To) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(To);
        msg.setCc(Cc);
        msg.setBcc(Bcc);
        msg.setSubject(subject);
        msg.setText(Body);
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

}

