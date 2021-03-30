/*
Compile:
javac -cp mail.jar;activation.jar MailSpamer.java
Running:

java -cp mail.jar;activation.jar MailSpamer.java

*/
import java.util.Date;
import java.util.Scanner;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailSpamer {
  public static void main(String[] args) {
    
	
	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
 // Get a Properties object
    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", "smtp.mail.ch");
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.port", "465");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", "true");
    props.put("mail.store.protocol", "pop3");
    props.put("mail.transport.protocol", "smtp");
	Scanner scanner = new Scanner(System.in);
	System.out.println("Gib deine E-mail an E-Mailadresse");
	String Mail = scanner.nextLine();
	System.out.println("Passwort");
	String pw = scanner.nextLine();
	System.out.println("wie viele Mails sollen verschickt werden?");
	int anzahl = scanner.nextInt();
	/* geht noch nicht */
	System.out.println("An welche Mail soll es verschickt werden?");
	String zuSpammendeMail = scanner.nextLine();
	
    final String username = Mail;//
    final String password = pw;
    try{
      Session session = Session.getDefaultInstance(props, 
                          new Authenticator(){
                             protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                             }});
						 
		for (int i= 1; i < anzahl; i++){
   // -- Create a new message --
      Message msg = new MimeMessage(session);

   // -- Set the FROM and TO fields --
      msg.setFrom(new InternetAddress(username));
      msg.setRecipients(Message.RecipientType.TO, 
                        InternetAddress.parse(/*zuSpammendeMail*/"/*TO_DO hier zu spammende Mail einfügen*/",false));
      msg.setSubject("Salue");
      msg.setText("est Mail ");
      msg.setSentDate(new Date());
      Transport.send(msg);
      System.out.println("Message sent.");
	  System.out.println(i);
		}
    }catch (MessagingException e){ 
      System.out.println("Oh mon Dieu, erreur d'envoi, cause: " + e);
    }
  }
}
