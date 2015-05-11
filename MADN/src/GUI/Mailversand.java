package GUI;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class Mailversand extends Thread{
	private Properties props;
	private String username = null;
	private String password = null;
	private String sendTo = null;
	private String pfad;
	private File datei;
	
	private class MailAuthenticator extends Authenticator{
		private String user, password;
		public MailAuthenticator(){
			this.user = "" + props.get("mail.smtp.user");
			this.password = "" + props.get("mail.smtp.password");
		}
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}

	
	
	public Mailversand(String pfad){
		//Loginanfrage
		datei = new File(pfad);
		JPanel panel = new JPanel();
		JLabel label3 = new JLabel("Zieladresse:");
		JTextField to = new JTextField(20);
		panel.add(label3);
		panel.add(to);
		
		final String anschreiben = String.format(
				"Sehr geehrter Empfänger,\n hiermit erhalten sie"
				+ "den Spielstand eines MADN_GORE_EDITION_OF_ULTIMATE_FACE_MELTING-Matches im Anhang.\n"
				+ "Mit freundlichen Gruessen,\n"
				+ "Gruppe A1"
				);
		
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(null, panel, "Passwort für brueckna",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		if(option == 0) //OK button
		{
		    this.sendTo = to.getText();
		}
		else if (option == 1){
			System.out.println("Speichern abgebrochen");
			return;
		}
		
		
		
		props = new Properties();
		String putMeIn;
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", "GruppeA1INF2SS15");
		props.put("mail.smtp.password", "ezgamemadn");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.port","587");
		props.put("von","screamer27th@gmail.com");
		props.put("an",this.sendTo);
		props.put("betreff","Spielstandversand MADN - Gruppe A1");
		props.put("text",anschreiben);
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		this.start();
		
		
		
		
		
		
	}
	@Override
	public void run(){
		
		try{
			
			System.out.println("Starte Mailversand an "+props.getProperty("an"));
			MailAuthenticator auth = new MailAuthenticator();
			Session session = Session.getDefaultInstance(props,auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(props.getProperty("von")));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(props.getProperty("an"),false));
			msg.setSubject(props.getProperty("betreff"));
			
			MimeBodyPart bodyNachricht = new MimeBodyPart();
			bodyNachricht.setText(props.getProperty("text"));
			Multipart body = new MimeMultipart();
			body.addBodyPart(bodyNachricht);
			
				MimeBodyPart bodyAnhang = new MimeBodyPart();
				DataSource source = new FileDataSource(datei);
				
				bodyAnhang.setDataHandler(new DataHandler(source));
				if(datei.getPath().endsWith(".ser")){
					bodyAnhang.setFileName("Savegame_MADN.ser");
				}
				else if(datei.getPath().endsWith(".pdf")){
					bodyAnhang.setFileName("Savegame_MADN.pdf");
				}
				body.addBodyPart(bodyAnhang);
			
			msg.setContent(body);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Mailing an "+props.getProperty("an")+" abgeschlossen");		
		}
		catch(Exception e){
			System.out.println("Mailversand an " + props.getProperty("an")+" fehlgeschlagen!");
			e.printStackTrace();
			System.err.println(e.getCause());
		}
	}
	
	

}
