package net.bandoviet.tool;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;


/**
 * Utils pour les controles d'email.
 *
 */
public class EmailUtils {

	private final static String PATTERN_EMAIL_VALIDE = "[\\w-\\.\\+]+@[\\w-\\.]+\\.[a-zA-Z]+";
	private final static Pattern PATTERN_DOMAINE = Pattern.compile("^.+@([\\w-\\.]+\\.[a-zA-Z]+)$");
	private final static Pattern PATTERN_USER = Pattern.compile("^([\\w-\\.\\+]+)@.+$");

	private EmailUtils(){}

	/**
	 * Joindre plusieurs fichiers à un message à envoyer
	 * 
	 * @param multipart
	 * @param filename
	 */
	public static boolean ajouterAttachement(Multipart multipart, String filename) {
		DataSource source = new FileDataSource(filename);
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Vérifie la validité d'un email
	 *
	 * @param email
	 * @return
	 */
	public static boolean isValide(String email){
		if (StringUtils.isBlank(email)) {
			return false;
		}
		if (!email.matches(PATTERN_EMAIL_VALIDE)) {
			return false;
		}
		try {
			new InternetAddress(email, true);
		} catch (AddressException e) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne le domaine de l'email
	 *
	 * @param email
	 * @return
	 */
	public static String extractDomain(String email) {
		if (isValide(email) == false) {
			return null;
		}
		Matcher matcher = PATTERN_DOMAINE.matcher(email);
		if (matcher.find() == false) {
			return null;
		}
		return matcher.group(1).toLowerCase();
	}

	/**
	 * Retourne l'utilisateur de l'email
	 *
	 * @param email
	 * @return
	 */
	public static String extractUser(String email) {
		if (isValide(email) == false) {
			return null;
		}
		Matcher matcher = PATTERN_USER.matcher(email);
		if (matcher.find() == false) {
			return null;
		}
		return matcher.group(1);
	}
}
