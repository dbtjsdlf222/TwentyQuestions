package Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Vo.ServerVo;

public class RunServer extends ServerVo { //���� ���� ����

	RunServer() throws IOException {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(4999);
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("�� ip�ּ�: "+ip.getHostAddress());
			System.out.println("==============���� ���� ��Ȳ==============");
			
			while (true) {
				Socket socket = serverSocket.accept();
				if (!gameState) { 		//�������� �ƴҽ�
					new Thread(new CreateClientVo(socket)).start();
					
				} else {
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)
							.println("������ �������Դϴ� ���߿� ������ �ٽýõ� ���ּ���");
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
			new RunServer(); //������ Ŭ���̾�Ʈ �޾Ƶ���

		} catch (IOException e) {
			System.err.println("������ �̹� �������Դϴ�");
		} catch (Exception e) {}
	} //main
} //class
