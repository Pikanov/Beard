package com.beard.util;

import com.beard.entity.User;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

public class MailSender {
    private static final Logger LOGGER = Logger.getLogger(MailSender.class);

    private static final String SEND_FROM = "pikanov.v.s";
    private static final String PASSWORD = "greentea012591";
    private static final String PROTOCOL = "smtps";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static void sendMessageToEmail(User user) {

        System.out.println("Mail send");
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = System.getProperties();
            putProperties(props);

            Session session = Session.getInstance(props, null);

            final MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(SEND_FROM + "@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail(), false));
            msg.setSubject("Hello Andrew");
            msg.setContent("Thank you for visiting our barber. You can write your " +
                              "feedback at this address: http://localhost:8080/comment"
                    , "text/html; charset=utf-8");

            SMTPTransport t = (SMTPTransport)session.getTransport(PROTOCOL);

            t.connect("smtp.gmail.com", SEND_FROM, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            LOGGER.warn("MessagingException while sending test results by email: " + e.getMessage());
        }
    }

    private static void putProperties(Properties props) {
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
    }
}
