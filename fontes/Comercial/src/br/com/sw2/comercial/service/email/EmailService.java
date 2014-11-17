package br.com.sw2.comercial.service.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {
	
	private static final String _465 = "465";
	private static final String SMTP_GMAIL = "smtp.gmail.com";
	private static final String EMAIL_TO = "paciente.odonto@gmail.com";
	private static final String SENHA = "fapesp2014";
	private static final String EMAIL = "odontodo@gmail.com";

	public static void main(String[] args) {
		//sendSimpleMail("Teste", "Isso é um teste!", EMAIL_TO);
		
		sendSimpleMailJavaMail();
	}
	
	public static void sendSimpleMail(String assunto, String mensagem, String destinatario) {

		SimpleEmail email = new SimpleEmail();
		
		email.setDebug(true);
		
		// configuracao para usar gmail
		email.setHostName(SMTP_GMAIL);
		email.setSslSmtpPort(_465);
		email.setSSLOnConnect(true);
		email.setAuthentication(EMAIL, SENHA);

		try {
			email.addTo(destinatario);
			email.setFrom(EMAIL);
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}
	
	
	private static void sendSimpleMailJavaMail() {
		Session session = createAuthenticatorSession();

		Message msg = createEmailBody(session);

		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
				
	}

	/**
	 * Cria uma nova sessão com a autenticação
	 * 
	 * @return {@link Session} contendo a sessão já autenticada
	 */
	private static Session createAuthenticatorSession() {
		Properties properties = createEmailConfigurationProperties();
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL, SENHA);
			}
		};
		Session session = Session.getInstance(properties, auth);
		session.setDebug(true);
		return session;
	}

	/**
	 * Atribui ao properties as configuracoes do {@link EmailAccountConfiguration}
	 * 
	 * @return {@link Properties} contendo as configurações do serviço de emails
	 */
	private static Properties createEmailConfigurationProperties() {
		Properties properties = new Properties();

		properties.put("mail.smtp.host", SMTP_GMAIL);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", _465);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.socketFactory.port", _465);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.user", EMAIL);
		properties.put("mail.password", SENHA);
		return properties;
	}
	
	/**
	 * Cria o corpo do email com Subject e arquivo anexado
	 * 
	 * @param session
	 *            Sessão do serviço de email
	 * @return Mensagem de email
	 */
	private static Message createEmailBody(Session session)  {
		// Cria o e-mail
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(EMAIL));
			InternetAddress[] toAddresses = { new InternetAddress(EMAIL_TO) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);

			msg.setSubject("Teste 1,2,3 Testando...");
			msg.setText("Conteudo");
			msg.setSentDate(new Date());

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
}
