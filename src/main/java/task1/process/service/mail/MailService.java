package task1.process.service.mail;


import org.apache.log4j.Logger;
import task1.process.service.dataAccess.entity.User;
import task1.process.service.dataAccess.entity.Way;
import task1.process.service.mail.configuration.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailService {
    private static final Logger logger = Logger.getLogger(MailService.class);
    public void sendEmailMessage(User user){
      String email  =  user.getEmail();

        String from = "fromemail@gmail.com";
        final String username = "nadrezim89";
        final String password = "Artuom89!!";

        String host = "relay.jangosmtp.net";


        Session session = Session.getInstance(Configuration.getProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);


            message.setFrom(new InternetAddress(from));


            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));

            message.setSubject("Traffic fare: ");

            StringBuilder builder = new StringBuilder();
            for(Way point: user.getTrip().getWays()){
                builder.append("\nFrom : " + point.getStartPoint() + ", to : " + point.getEndPoint());
            }

            message.setText("Hello, " + user.getEmail() +
                            ". You`re receiving bill for road payment. \n" +
            "You've started at : " + user.getTrip().getEntryTime() + " \n" +
            "And ended at : " + user.getTrip().getExitTime() + ". \n" +
            "Full trip contained :" + builder.toString() + "\n" +
            "Total fare = " + user.getTrip().getFare());

            Transport.send(message);

          logger.info("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
