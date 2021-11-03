package br.com.view;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.controller.ConectaBancoController;
import br.com.model.Solicitacao;



public class EmailCronometro  implements Job {

	
	private String to = "donizetep@publisinformatica.com.br";
	private String subject = "subject";
	private String msg = "email text....";
	final String from = "diones@publisinformatica.com.br";
	final String password = "dionesdiones181";
	private String mailHost = "smtp.gmail.com";

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		// TODO Auto-generated method stub

	
		
	 JobDataMap jobData = jobContext.getJobDetail().getJobDataMap();
	 ConectaBancoController c1 = (ConectaBancoController) jobData.get("requestContext");
	
	   	
		Solicitacao ss = new Solicitacao();
        List<Solicitacao> lst =  c1.VerificarViagemAmanha(ss);
        
        if(lst.get(0).getCod() != 0) {
        	
        	 for(Solicitacao ss1: lst) {
                 
             	
                 String email = ss1.getUsuariosolicitante().getEmail();
                 String cliente = ss1.getCliente().getNome();
                 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
     			 String dataFormatada = formato.format(ss1.getDatinicial());
                 String data = dataFormatada;
                 String usuario = ss1.getUsuariosolicitante().getNome();
                 
                 
                 EmailCronometro ec = new EmailCronometro();
                 ec.lembreteEmail(email,cliente,data,usuario);
            	
             }
        	
        }
		
		
	}
	
	
	
	public void lembreteEmail(String email,String cliente, String data, String usuario ) {
		
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
			Address too = new InternetAddress(email);

			message.setFrom(fromm);
			message.addRecipient(RecipientType.TO, too);
			message.setSentDate(new Date());
			message.setSubject("Lembrete de Viagem !");

			String newline = System.getProperty("line.separator");
			
			message.setText("Bom dia "+usuario+ newline + newline+"  Email enviado para relembra-lo da sua viagem na data "+data+" para o cliente "+cliente+ newline+"(Atençaõ não responder E-mail)");
			Transport.send(message);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}

		
			
	
	
	
	//get e set
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

	public String getFrom() {
		return from;
	}

	public String getPassword() {
		return password;
	}

}
