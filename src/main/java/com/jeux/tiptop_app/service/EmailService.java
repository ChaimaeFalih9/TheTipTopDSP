/**
 * 
 */
package com.jeux.tiptop_app.service;


import com.jeux.tiptop_app.entity.SendMail;

import javax.mail.MessagingException;

public interface EmailService {

	String sendEmail(SendMail user) throws MessagingException;
}
