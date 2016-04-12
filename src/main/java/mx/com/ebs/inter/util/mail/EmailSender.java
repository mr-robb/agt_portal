package mx.com.ebs.inter.util.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static mx.com.ebs.inter.util.mail.EmailProperties.*;

public class EmailSender {

	private static final Logger LOGGER = Logger.getLogger(EmailSender.class);
	public static Properties properties;

    static {
        properties = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("mail", new Locale("sp", "MX"));
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, rb.getString(key));
        }
    }

	public static void sendSimple(final String content, final String emailDestination) throws EmailException {
		LOGGER.info("Sending email to " + emailDestination);
		HtmlEmail email = getEmail();
        email.setHtmlMsg(content);
		setDestination(email, emailDestination, false);
		email.send();
	}


	private static HtmlEmail getEmail() throws EmailException {
		HtmlEmail email = new org.apache.commons.mail.HtmlEmail();
		email.setDebug("true".equalsIgnoreCase(properties.getProperty(DEBUG)));
		email.setHostName(properties.getProperty(HOST));
		email.setSmtpPort(Integer.parseInt(properties.getProperty(PORT)));

		if ("true".equalsIgnoreCase(properties.getProperty(USE_TLS))) {
			email.setAuthentication(properties.getProperty(USER), properties.getProperty(PASSWORD));
			email.setStartTLSEnabled(true);
		}
		email.setFrom(properties.getProperty(USER), properties.getProperty(FROM));
		email.setSubject(properties.getProperty(SUBJECT));
		setDestination(email, properties.getProperty(EMAIL_CC), true);
		return email;

	}

	private static void setDestination(final HtmlEmail email, String address, boolean isCC) throws EmailException {
		if (address != null && !address.trim().equals("")) {
			if (address.contains(";")) {
				String list[] = address.split(";");
				for (String item : list) {
					if (isCC) {
						email.addCc(item);
					} else {
						email.addTo(item);
					}
				}
			} else {
				if (isCC) {
					email.addCc(address);
				} else {
					email.addTo(address);
				}
			}
		}
	}

}