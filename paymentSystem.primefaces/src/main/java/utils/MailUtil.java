/**
 * 
 */
package utils;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;



/**
 * @author cz
 *
 * 2018年2月2日下午1:29:35
 */
public class MailUtil {
	public static String getUUID() {
		/*
		 * 2018-2-4
		 * 增加代码的健壮性，防止库内激活码重复，造成错误，进一步完善代码。
		 * */
		String uuid;
		//进行库内查重操作
		do {
			uuid = UUID.randomUUID().toString().replace("-", "");
		}while(DataSearchUtil.duplicateCheckingData("User", "mailCode", uuid));
		return uuid;
	}
	public static void sendMail(String to,String mailCode) {
		
		//链接pop3服务器的主机名，协议，用户名，密码
		String pop3Server = "smtp.126.com";
		String protocol = "smtp";
		
		//1.创建连接对象，链接到邮箱服务器
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", protocol);
		props.setProperty("mail.smtp.host", pop3Server);
		props.setProperty("mail.smtp.auth","true");
		//props.setProperty("host",value);
		Session session = Session.getInstance(props, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("cz826033956@126.com","xuewuzhijing9981");
			}
			
		});
		//2.1创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//2.2设置收件人
		try {
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//2.3设置邮件的主题
			message.setSubject("来自XX网站的激活邮件");
			//2.4设置邮件的正文
			message.setContent("<h1>来自XX网站激活邮件，激活请点击下面链接：</h1>"
					+ "<h3>"
					+ "<a href='http://localhost:8080/paymentSystem.primefaces/ActiveServlet?mailCode="+mailCode+"'>"
					+ "http://localhost:8080/paymentSystem.primefaces/ActiveServlet?mailCode="+mailCode+"</a>"
					+ "</h3>","text/html;charset=UTF-8");
			
			//3.发送一封激活邮件
			
			message.setFrom("cz826033956@126.com");
			Transport.send(message);
			
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
	}
public static boolean sendCheckCode(String to,String mailCode) {
		
		//链接pop3服务器的主机名，协议，用户名，密码
		String pop3Server = "smtp.126.com";
		String protocol = "smtp";
		
		//1.创建连接对象，链接到邮箱服务器
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", protocol);
		props.setProperty("mail.smtp.host", pop3Server);
		props.setProperty("mail.smtp.auth","true");
		//props.setProperty("host",value);
		Session session = Session.getInstance(props, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("cz826033956@126.com","xuewuzhijing9981");
			}
			
		});
		//2.1创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//2.2设置收件人
		try {
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//2.3设置邮件的主题
			message.setSubject("来自XX网站的邮件");
			//2.4设置邮件的正文
			message.setContent("<h1>来自XX网站修改密码验证码邮件</h1>"
					+ "<h3>"
					+ mailCode
					+ "</h3>","text/html;charset=UTF-8");
			
			//3.发送一封激活邮件
			
			message.setFrom("cz826033956@126.com");
			Transport.send(message);
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
