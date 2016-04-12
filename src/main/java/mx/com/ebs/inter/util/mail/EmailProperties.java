package mx.com.ebs.inter.util.mail;

public class EmailProperties {

	/**
	 * Is the port used to send an email
	 */
	public static final String PORT = "mail.port";
	/**
	 * Is the email server ip or name
	 */
	public static final String HOST = "mail.host";
	/**
	 * The user that will be used to connect to the email server
	 */
	public static final String USER = "mail.user";
	/**
	 * Is the pass of user
	 */
	public static final String PASSWORD = "mail.password";
	/**
	 * Is a flag that indicates if authentication will be needed to connect to
	 * the email server. true or false
	 */
	public static final String USING_AUTH = "mail.authentication";

	/**
	 * Is the address that will be used to send the email
	 */
	public static final String FROM = "mail.from";
	/**
	 * Is the flag that indicates id TLS will be used
	 */
	public static final String USE_TLS = "mail.tls";
	/**
	 * Is the sucject of the email
	 */
	public static final String SUBJECT = "mail.subject";
	/**
	 * Is a flag that indicates if debugging is enabled
	 */
	public static final String DEBUG = "mail.debug";
	/**
	 * Is the email addresses to be copied
	 */
	public static final String EMAIL_CC = "mail.cc";

    public static final String EMAIL_PASSWORD_CONTENT = "mail.sendPassword.content";

}
