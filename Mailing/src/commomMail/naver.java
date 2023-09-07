package commomMail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class naver {

	public static void main(String[] args) {
	    String naverEmail = "ㄴㄹㄴㅇㄹㄴㅇ";
        String naverPassword = "ㅇㄴㄹㄴㅇㄹ";
        String recipientEmail = "chae_ming@naver.com"; 

        try {
            // 이메일 객체 생성
            HtmlEmail email = new HtmlEmail();

            // SMTP 서버 설정 (네이버 이메일 설정에 따라 변경)
            email.setHostName("smtp.naver.com");
            email.setSmtpPort(465); // 네이버 SMTP 포트
            email.setAuthenticator(new DefaultAuthenticator(naverEmail, naverPassword));

            // SSL 연결 설정 (필요한 경우)
            email.setSSLOnConnect(true);

            // 이메일 내용 설정
            email.setFrom(naverEmail, "Your Name"); // 보내는 사람 이름 설정
            email.addTo(recipientEmail); // 수신자 이메일 설정
            email.setSubject("제목"); // 이메일 제목 설정
            email.setCharset("UTF-8"); // UTF-8 문자 인코딩 설정
            email.setHtmlMsg("<h1>안녕하세요!</h1><p>이메일 내용입니다.</p>"); // HTML 형식의 이메일 내용

         // 파일 첨부
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("d:/d_other/펭귄.jpg"); // 첨부 파일 경로 설정
            attachment.setDisposition(EmailAttachment.ATTACHMENT); // 파일 첨부 설정
            attachment.setDescription("파일 설명");
            attachment.setName("파일 이름"); // 첨부 파일 이름 설정

            email.attach(attachment);
            
            // 이메일 발송
            email.send();
            System.out.println("이메일을 성공적으로 발송했습니다.");
        } catch (EmailException e) {
            System.err.println("이메일 발송 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

}
