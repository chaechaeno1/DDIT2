package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
	클라이언트 프로그램은 서버와 접속이 완료되면
	'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다.
	==> 파일 스트림으로 읽어서 소켓 스트림으로 출력한다.
 */

public class TcpFileClient {

	public void clientStart() {
		// 전송할 파일 정보를 갖는 File객체 생성
		//File file = new File("d:/d_other/펭귄.jpg");
		File file = showDialog("open");
		
		if(file==null) {
			System.out.println("선택한 파일이 없습니다.");
			System.out.println("작업을 마칩니다...");
			return;
		}
		
		if (!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("작업을 마칩니다.");
			return;
		}

		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;

		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작...");

			// 파일 입력용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));

			// 소켓으로 출력할 스트림 객체 생성
			dout = new DataOutputStream(socket.getOutputStream()); // 파일에 대한 정보(이름)을 전송
			bout = new BufferedOutputStream(dout); // 파일의 실제 내용을 전송

			// 서버에 접속하면 처음으로 파일명을 전송한다.
			dout.writeUTF(file.getName());

			// 파일이름 전송이 완료되면 파일의 내용을 읽어서 전송한다.
			byte[] temp = new byte[1024];
			int len = 0;
			while ((len = bin.read(temp)) != -1) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			System.out.println("파일 전송 완료...");

		} catch (Exception e) {

			System.out.println("파일 전송 실패...");
			e.printStackTrace();
		} finally {
			if (dout != null)
				try {
					dout.close();
				} catch (Exception e) {
				}
			if (bout != null)
				try {
					bout.close();
				} catch (Exception e) {
				}
			if (bin != null)
				try {
					bin.close();
				} catch (Exception e) {
				}
			if (socket != null)
				try {
					socket.close();
				} catch (Exception e) {
				}
		}

	}

	// Dialog창을 나타내고 선택한 파일을 반환하는 메서드
	// Option이 'open'이면 열기창, 'save'면 저장창
	public File showDialog(String option) {
		// AWT ==> SWING ==> javaFX

		// SWING으로 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 종류 설정하기(파일의 확장자를 이용하여 구별)
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		FileNameExtensionFilter img = new FileNameExtensionFilter("그림파일", "jpg", "png", "gif");
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word파일", new String[] { "doc", "docx" });

		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);

		// '모든 파일'이라는 항목의 표시 여부 설정 (true:설정, false:해제)
		// (기본값: true)
		chooser.setAcceptAllFileFilterUsed(true);

		// Dialog창에 나타날 기본 경로 설정 ('d:/d_other'로 설정하기)
		chooser.setCurrentDirectory(new File("d:/d_other"));

		int result = 0;

		if ("save".equals(option.toLowerCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장용
		} else if ("open".equals(option.toLowerCase())) {
			result = chooser.showOpenDialog(new Panel()); // 열기용
		} else {
			System.out.println("option변수에는 save 또는 open만 가능합니다.");
		}

		File selectedFile = null;
		// 창에서 '저장'또는 '열기'버튼을 눌렀을 때를 검사한다.
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());

		}
		return selectedFile;

	}

	public static void main(String[] args) {
		new TcpFileClient().clientStart();

	}

}
