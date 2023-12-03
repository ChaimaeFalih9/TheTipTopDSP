package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.SendMail;
import com.jeux.tiptop_app.repository.SendMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.mail.MessagingException;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Controller
public class SendMailController {



    @Value("${gmail.username}")
    private String username;
    @Value("${gmail.password}")
    private String password;
    @Autowired
    SendMailRepository sendMailRepo;



    @RequestMapping(value="/send", method= RequestMethod.POST)
    public void sendEmail(@ModelAttribute SendMail sendmail, HttpServletResponse response) throws javax.mail.MessagingException, MessagingException, IOException {

        try{
            sendmail.setSubject("Votre inscription chez ThéTipTop");
            sendmail.setMessage("Cher Client/ Cliente,\n" +
                    "Merci d'avoir rejoint notre communauté ! Votre compte est désormais actif, et vous faites officiellement partie de la famille LIBERTEA. Explorez nos fonctionnalités en ligne ou visitez nos boutiques pour participer pleinement. Bonne chance !\n" +
                    "Cordialement, \n" +
                    "L'équipe service client Thétiptop.\n" +
                    "SA au capitale Social de 150 000€\n" +
                    "Siège Social: 18 rue Léon Frot, 75011 Paris");
            sendmail(sendmail);
        }catch (Exception e){

        }

        sendMailRepo.save(sendmail);
        String redirectURL = "/index?user="+sendmail.getUser();

        response.sendRedirect(redirectURL);
    }


    private @ResponseBody void sendmail(SendMail sendMail) throws AddressException, MessagingException, IOException, javax.mail.MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username, false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendMail.getToemail()));
        msg.setSubject(sendMail.getSubject());
        msg.setContent(sendMail.getMessage(), "text/html");
        msg.setSentDate(new Date());

   /*     MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(sendMail.getMessage(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile("C:\\talk2amareswaran-downloads\\mysql2.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);*/


        // sends the e-mail
        Transport.send(msg);

    }


}
