package commomMail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class javaMail {

	public static void main(String[] args) {

		// *발신자의 정보
		String host = "smtp.naver.com"; // <= 네이버 SMTP 서버명
		final String user = "sally_kim_@naver.com"; // <= 발신자 이메일 주소
		final String password = "Tidlsl0525!"; // <= 발신자 이메일 비밀번호

		// *수신자의 정보
		String to = "chae_ming@naver.com"; // <= 수신자 이메일 주소

		// Property에 SMTP 서버 정보를 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host); // <= SMTP 서버명
		props.put("mail.smtp.auth", "true"); // <= SSL 설정

		// SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// Message 클래스의 객체를 사용해 수신자와 내용, 제목, 메일을 작성
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(user));

					// 수신자 메일 주소
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

					// *제목
					message.setSubject("[Subject] Java Mail Test");

					// *내용
					message.setText("Simple mail test..");

					// Transport 클래스를 사용해 작성한 메일 수신
					Transport.send(message);
					System.out.println("메일 수신 작업 완료 했습니다");

					// 첨부 내용이 없을 시 예외처리
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			          }
		                   }

