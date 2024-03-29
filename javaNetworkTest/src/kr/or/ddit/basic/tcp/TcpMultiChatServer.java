package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보(Socket객체)를 저장할 변수
	// ==> key값 : 접속한 사람의 이름(대화명), value값 : 접속한 Socket객체
	private Map<String, Socket> clientMap;

	// 생성자
	public TcpMultiChatServer() {
		// Map객체를 동기화 처리가 되도록 생성
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}

	// 시작 메서드
	public void serverstart() {
		ServerSocket server = null;
		Socket socket = null;

		
		try {
			server = new ServerSocket(7777);
			System.out.println("채팅 서버가 시작되었습니다...");

			while (true) {
				socket = server.accept(); // 클라이언트의 접속을 기다린다.
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다...");
				System.out.println();
				////////////////////////////////////////////////////////////////
				
				//쓰레드 객체 생성 및 실행
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
				
				

			}

		} catch (Exception e) {

		}
	} // 시작 메서드 끝!

	// Map에 저장된 전체 클라이언트들에게 메시지를 전송하는 메서드
	private void sendToAll(String msg) {
		// Map의 데이터 개수만큼 반복
		for (String name : clientMap.keySet()) {
			try {
				// 각 key값에 대응하는 Socket객체의 출력용 스트림 객체를 구해서 사용한다.
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());
				// 출력용 스트림을 이용해서 메시지를 전송(출력)한다.
				dout.writeUTF(msg);

			} catch (Exception e) {

			}
		}

	} // sendToAll()메서드 끝...

	public static void main(String[] args) {
		new TcpMultiChatServer().serverstart();

	}

	// ---------------------------------------------------------------------------
	// 서버에서 각 클라이언트로 메시지를 전송하는 Thread를 Inner Class로 작성한다.
	// => Inner Class를 사용하는 이유 (Outer Class의 멤버변수를 자유롭게 사용할 수 있다.)
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;

		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());

				// 송신용 스트림 객체 생성
				dout = new DataOutputStream(this.socket.getOutputStream());

			} catch (Exception e) {

			}
		} // 생성자 끝...

		@Override
		public void run() {
			String name = "";
			try {
				// 클라이언트가 연결이 성공하면 클라이언트는 첫번째 데이터로 '대화명'을
				// 입력 받아서 보낸다.
				// 서버에서는 이 '대화명'을 받아서 '대화명'이 중복되는지 여부를 검사해서
				// 그 결과를 응답으로 클라이언트에게 보내준다.

				// 클라이언트가 보내온 '대화명'이 중복되지 않을 때까지 반복한다.
				while (true) {
					name = din.readUTF(); // 클라이언트가 보낸 '대화명' 받기

					if (clientMap.containsKey(name)) { // '대화명'이 중복되면....
						dout.writeUTF("대화명 중복"); // 응답으로 '대화명 중복' 메시지 전송
					} else { // '대화명'이 중복되지 않으면...
						dout.writeUTF("OK"); // 응답으로 'OK'메시지 전송
						break; // 반복문 탈출

					}
				} //while문 끝..
				
				// 접속한 사람의 대화명을 이용하여 다른 전체 클라이언트들에게 
				// 대화방에 참여한다는 메시지를 보낸다.
				sendToAll("[" + name + "]님 대화방에 입장했습니다...");
				
				//대화명을 key값으로 클라이언트의 Socket객체를 Map에 추가한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
				
				//현 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 다시 보낸다.
				while(din!=null) {
					sendToAll(din.readUTF());
				}
				
				

			} catch (Exception e) {
			}finally {
				//이 finally영역이 실행된다는 것은 현 클라이언트가 접속을 종료했다는 의미
				sendToAll("["+name+"]님이 접속을 종료했습니다...");
				
				//Map에서 해당 클라이언트 정보를 삭제
				clientMap.remove(name);
				
				System.out.println();
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료했습니다...");
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
				
				
			}
			}
		}

	}


