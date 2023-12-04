package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.SendMail;
import com.jeux.tiptop_app.entity.TargetEmail;
import com.jeux.tiptop_app.repository.SendMailRepository;
import com.jeux.tiptop_app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.*;

@Controller
public class SendMailController {

    @Autowired
    SendMailRepository sendMailRepo;
    @Autowired
    EmailService emailService;



    @RequestMapping(value="/send", method= RequestMethod.POST)
    public void sendEmail(@ModelAttribute SendMail sendmail, HttpServletResponse response) throws javax.mail.MessagingException, MessagingException, IOException {

        try{
            sendmail.setSubject("Votre inscription chez ThéTipTop");
            sendmail.setMessage("ppp");
            sendmail(sendmail);
        }catch (Exception e){

        }

        sendMailRepo.save(sendmail);
        String redirectURL = "/index?user="+sendmail.getUser();

        response.sendRedirect(redirectURL);
    }


    private @ResponseBody void sendmail(SendMail sendMail) throws AddressException, MessagingException, IOException, javax.mail.MessagingException {

        Map<String, Object> map = new HashMap<>();
        List<TargetEmail> targetEmails = new ArrayList<>();
        TargetEmail targetEmail = new TargetEmail();

        targetEmail.setEmail(sendMail.getToemail());
        targetEmails.add(targetEmail);


        map.put("message", emailService.sendEmail(sendMail));
    }


}
