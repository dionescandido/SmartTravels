package br.com.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import br.com.model.Solicitacao;


public class EnviarEmail{
	

	private String to = "donizetep@publisinformatica.com.br";
	private String subject = "subject";
	private String msg = "email text....";
	final String from = "diones@publisinformatica.com.br";
	final String password = "dionesdiones181";
	private String mailHost = "smtp.gmail.com";

	public void email(Solicitacao s1) {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", mailHost);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.ssl.trust", mailHost);
		props.put("mail.smtp.auth", true);
		props.put("mail.debug", true);
		
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});


		MimeMessage message = new MimeMessage(session);

		try {

			Address fromm = new InternetAddress(from);
			Address too = new InternetAddress(s1.getUsuariosolicitante().getEmail());

			message.setFrom(fromm);
			message.addRecipient(RecipientType.TO, too);
			message.setSentDate(new Date());
			message.setSubject("Infomação de solicitação !");
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formato.format(s1.getDatinicial());
			
			String newline = System.getProperty("line.separator");
			
			message.setText("Olá "+s1.getUsuariosolicitante().getNome()+newline+" e-mail enviado para informar que a sua solicitação de viagem na data "+dataFormatada+" para "+s1.getCliente().getNome()+" foi alterado o seu parecer para "+s1.getParecer()+"."+newline+"   (Atenção não responder E-mail). ");
			Transport.send(message);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}
	
	
	

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}


	
	
	
	
	
}