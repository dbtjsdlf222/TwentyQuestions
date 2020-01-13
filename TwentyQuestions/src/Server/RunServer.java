package Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Vo.ServerVo;

public class RunServer extends ServerVo { //게임 서버 시작

	RunServer() throws IOException {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(4999);
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("내 ip주소: "+ip.getHostAddress());
			System.out.println("==============서버 접속 상황==============");
			
			while (true) {
				Socket socket = serverSocket.accept();
				if (!gameState) { 		//게임중이 아닐시
					new Thread(new CreateClientVo(socket)).start();
					
				} else {
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)
							.println("게임이 진행중입니다 나중에 접속을 다시시도 해주세요");
					socket.close();
				}
			} // while
		} finally {
			if (serverSocket != null)
				serverSocket.close();
		} // try~finally
	} // serverAceept
	
	public static void main(String[] args) { 
		try {
			new RunServer(); //들어오는 클라이언트 받아들임

		} catch (IOException e) {
			System.err.println("서버가 이미 실행중입니다");
		} catch (Exception e) {}
	} //main
} //class
