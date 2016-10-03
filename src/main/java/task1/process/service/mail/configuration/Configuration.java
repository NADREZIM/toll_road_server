package task1.process.service.mail.configuration;

import java.util.Properties;

/**
 * Created by User on 03.10.2016.
 */
public class Configuration {
    private static Properties props = new Properties();
    static {
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "relay.jangosmtp.net");
        props.put("mail.smtp.port", 2525);
        props.put("mail.smtp.auth", "true");
    }
    public static Properties getProperties(){
        return props;
    }
}
