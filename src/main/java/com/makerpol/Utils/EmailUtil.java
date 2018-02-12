package com.makerpol.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.makerpol.common.Common;

public class EmailUtil {
	private static Properties props = null;
	static {
		props = new Properties();
		props.setProperty("mail.transport.protocol", Common.EMAIL_PROTOCOL);
		props.setProperty("mail.smtp.host", Common.EMAIL_HOST);
		props.setProperty("mail.smtp.port", Common.EMAIL_PORT);
		props.setProperty("mail.smtp.timeout", Common.EMAIL_TIMEOUT);
		props.setProperty("mail.smtp.starttls.enable", "true");
	}
	
	private static Session createMailSender() {
		Session session = Session.getDefaultInstance(props);
		return session;
	}
	
	private static MimeMessage createMessage(Session session, String to,String sub, String content) throws MessagingException {
		String mailHTML = getMailHTML(to, content);
		MimeMessage message = new MimeMessage(session);
		message.setSubject(sub);
		message.setFrom(new InternetAddress(Common.EMAIL_USERNAME));
		message.setSentDate(new Date());
		message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
		message.setContent(mailHTML,"text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
	
	private static String getMailHTML(String to, String content) {
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("username", to);
		model.put("VerifyCode", content);
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();
		String mailHTML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"src/main/java/com/makerpol/Utils/email_tempalte.vm", "UTF-8", model);
		return mailHTML;
	}
	
	public static void mailSend(String to,String sub, String content) throws AddressException, MessagingException {
		Session session = createMailSender();
		MimeMessage message = createMessage(session, to, sub, content);
		
		Transport ts= session.getTransport();
		ts.connect(Common.EMAIL_USERNAME, Common.EMAIL_PASSWOED);
		ts.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		ts.close();
	}
	
	public static void main(String args[]) throws AddressException, MessagingException {
		String VerifyCode = VerifyCodeUtil.getVerifyCode();
		mailSend("574025348@qq.com","验证码",VerifyCode);
	}
}
