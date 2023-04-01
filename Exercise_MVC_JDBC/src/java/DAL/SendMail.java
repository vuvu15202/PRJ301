/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

//import java.util.Properties;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMail {
 
  public static void main(String args[]) throws AddressException, MessagingException {
    //sendNewPass("vuvu15202@gmail.com","abc");
  }
 
  public void sendNewPass(String toEmail,String newPass) throws AddressException, MessagingException {
      Properties mailServerProperties;
    Session getMailSession;
    MimeMessage mailMessage;
 
    // Step1: setup Mail Server
    mailServerProperties = System.getProperties();
    mailServerProperties.put("mail.smtp.port", "587");
    mailServerProperties.put("mail.smtp.auth", "true");
    mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
    // Step2: get Mail Session
    getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    mailMessage = new MimeMessage(getMailSession);
 
    mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); //Thay abc bằng địa chỉ người nhận
 
    // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
 
 
    mailMessage.setSubject("Your account was changed pasword");//
    mailMessage.setText("Dear customer!\nYour new password is : "+newPass +"\nDon't share this password to anyone!");
 
    // Step3: Send mail
    Transport transport = getMailSession.getTransport("smtp");
 
    // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
    transport.connect("smtp.gmail.com", "vuvthe163299@fpt.edu.vn", "ljaxsoswxmesevze"); 
    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
    transport.close();
  }
  public void sendAnnounce(String toEmail,String subjectContent,String emailContent) throws AddressException, MessagingException {
      Properties mailServerProperties;
    Session getMailSession;
    MimeMessage mailMessage;
 
    // Step1: setup Mail Server
    mailServerProperties = System.getProperties();
    mailServerProperties.put("mail.smtp.port", "587");
    mailServerProperties.put("mail.smtp.auth", "true");
    mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
    // Step2: get Mail Session
    getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    mailMessage = new MimeMessage(getMailSession);
 
    mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); //Thay abc bằng địa chỉ người nhận
 
    // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
 
 
    mailMessage.setSubject(subjectContent);//
    mailMessage.setText(emailContent);
 
    // Step3: Send mail
    Transport transport = getMailSession.getTransport("smtp");
 
    // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
    transport.connect("smtp.gmail.com", "vuvthe163299@fpt.edu.vn", "ljaxsoswxmesevze"); 
    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
    transport.close();
  }
}
