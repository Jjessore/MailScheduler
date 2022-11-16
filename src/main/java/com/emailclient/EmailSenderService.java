package com.emailclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String toEmail,
                               String mailBody,
                               String ccids,
                               String mailSubject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("samplemail.sendfrom@gmail.com");
        message.setTo(toEmail);
        message.setText(mailBody);
        message.setCc(ccids);
        message.setSubject(mailSubject);

        mailSender.send(message);
        System.out.println("Mail sent");

    }

    public void sendAttachmentMail(String toEmail,
                                   String mailBody,
                                   String mailSubject,
                                   String ccids,
                                   String attachmentPath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setTo(toEmail);
        messageHelper.setText(mailBody);
        messageHelper.setCc(ccids);
        messageHelper.setSubject(mailSubject);

        FileSystemResource filesystem = new FileSystemResource(new File(attachmentPath));
        messageHelper.addAttachment(filesystem.getFilename(), filesystem);

        mailSender.send(mimeMessage);
        System.out.println("Mail Attachment Sent..");
    }


}



