package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/*
	클라이언트 프로그램은 서버와 접속이 완료되면
	'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다.
	==> 파일 스트림으로 읽어서 소켓 스트림으로 출력한다.
 */

public class TcpFileClient {

	public void clientStart() {
		//전송할 파일 정보를 갖는 File객체 생성
		File file = new File("d:/d_other/펭귄.jpg");
		if(!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("작업을 마칩니다.");
			return;
		}
		
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		try {
			socket = new Socket("localhost",7777);
			System.out.println("파일 전송 시작...");
			
			//파일 입력용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			//소켓으로 출력할 스트림 객체 생성
			dout = new DataOutputStream(socket.getOutputStream()); //파일에 대한 정보(이름)을 전송
			bout = new BufferedOutputStream(dout); //파일의 실제 내용을 전송
		
			//서버에 접속하면 처음으로 파일명을 전송한다.
			dout.writeUTF(file.getName());
			
			//파일이름 전송이 완료되면 파일의 내용을 읽어서 전송한다.
			byte[] temp = new byte[1024];
			int len = 0;
			while( (len = bin.read(temp)) != -1) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			System.out.println("파일 전송 완료...");
			
		} catch (Exception e) {
			
			System.out.println("파일 전송 실패...");
			e.printStackTrace();
		} finally {
			if(dout!=null) try { dout.close();}catch(Exception e) {}
			if(bout!=null) try { bout.close();}catch(Exception e) {}
			if(bin!=null) try { bin.close();}catch(Exception e) {}
			if(socket!=null) try { socket.close();}catch(Exception e) {}
		}
		
		
	}

	public static void main(String[] args) {
		new TcpFileClient().clientStart();
		

	}

}
