package GUI;

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
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Passwort:");
		JLabel label2 = new JLabel("Name:");
		JLabel label3 = new JLabel("Zieladresse:");
		JTextField nick = new JTextField(10);
		JTextField to = new JTextField(20);
		JPasswordField pass = new JPasswordField(10);
		panel.add(label2);
		panel.add(nick);
		panel.add(label);
		panel.add(pass);
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
			this.username = nick.getText();
		    char[] password = pass.getPassword();
		    this.password = new String(password);
		    this.sendTo = to.getText();
		}
		
		
		
		props = new Properties();
		String putMeIn;
		final String[] adressen = {
		 "Alexander.Brueckner@Student.Reutlingen-University.de",
		 "Kevin.Schroetter@Student.Reutlingen-University.de",
		 "Anna.Rosa@Student.Reutlingen-University.de",
		 "Felix_Frederic.Rosa@Reutlingen-Unsiversity.de"}; 
		
		if(this.username == null) return;
		
		if(this.username.equals("brueckna")){
			putMeIn = adressen[0];			 
		}
		else if(this.username.equals("schroett")){
			putMeIn = adressen[1];
		}
		else if(this.username.equals("rosaa")){
			putMeIn = adressen[2];
		}
		
		else if (this.username.equals("rosa")){
			putMeIn = adressen[3];
		}
		else{
			throw new IllegalArgumentException("Benutzer nicht Teil von Gruppe A1!");
		}
		
		props.put("mail.smtp.host", "maildap.reutlingen-university.de");
		props.put("mail.smtp.user", this.username);
		props.put("mail.smtp.password", this.password);
		props.put("mail.smtp.socketFactory.port","465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth",true);
		props.put("mail.smtp.port", 465);
		props.put("von",putMeIn);
		props.put("an",this.sendTo);
		props.put("betreff","Spielstandversand MADN - Gruppe A1");
		props.put("text",anschreiben);
		
		
		
		
		
		
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
			if(pfad != null){
				MimeBodyPart bodyAnhang = new MimeBodyPart();
				DataSource source = new FileDataSource(pfad);
				bodyAnhang.setDataHandler(new DataHandler(source));
				bodyAnhang.setFileName("Savegame_MADN");
				body.addBodyPart(bodyAnhang);
			}
			msg.setContent(body);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Mailing an "+props.getProperty("an")+" abgeschlossen");		
		}
		catch(Exception e){
			System.out.println("Mailversand an " + props.getProperty("an")+" fehlgeschlagen!");
			e.printStackTrace();
		}
	}
	
	

}
