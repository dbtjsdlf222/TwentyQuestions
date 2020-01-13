package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import Chatting.ChattingRelay;
import Client.InputName;
import Vo.ClientVo;
import Vo.ServerVo;

public class CreateClientVo extends ServerVo implements Runnable {

	private ClientVo vo = new ClientVo(); //들어온사람의 정보를 담을 객체생성

	CreateClientVo(Socket socket) {
		try {
			vo.setSocket(socket); 	// socket객체를 받아서 맴버에 넣는다
			vo.setPw(new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true));
			vo.setBr(new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")));

		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {}
	
	} // ServerWorker

	@Override
	public void run() {
		try {
			new InputName(vo);
			
			if(null != vo.getName()) System.out.println("["+vo.getName()+"]님 입장");
			
			new ChattingRelay(vo); // 채팅시작
			
			new Quit(vo); 	  	   // 나갈때 처리해야할 메서드
		
			closeAll();
			
		} catch (IOException e) {}
		  catch (Exception e) {}
	} // run

	public void closeAll() throws IOException {
		if (vo.getPw() != null)
			vo.getPw().close();
		if (vo.getBr() != null)
			vo.getBr().close();
		if (vo.getSocket() != null)
			vo.getSocket().close();
	}// closeAll
} // ServerWork