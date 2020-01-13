package Chatting;

import java.io.PrintWriter;

import Server.Broadcast;
import Server.GameStartEnd;
import Vo.ClientVo;
import Vo.ServerVo;

public class FreeChatting extends ServerVo{

	public void freeMessage(String message, ClientVo vo) {
		
		String name = vo.getName();
		boolean ready = vo.isReady();
		PrintWriter pw = vo.getPw();
		
		if (roomLeader.equals(name)) {
			if (message.equals(name + ": /시작")) {
				new GameStartEnd().gameStartCheck();
				
			} else {
				Broadcast.execute("[방장]" + message);
			}
		} else {
			if (message.equals(name + ": /준비")) {
				if(!ready){
					Broadcast.execute("[" + name + "]" + "님 준비완료");
					vo.setReady(true);
				
				}else{
					pw.println("이미 준비 상태입니다.");
				}

			} else if (message.equals(name + ": /준비해제")) {  //레디 상태에서만 준비해제 가능
				if(ready){  
					Broadcast.execute("[" + name + "]" + "님 준비해제");
					vo.setReady(false);
					
				}else{
					pw.println("이미 준비해제 상태입니다");
					
				}
			} else { 
				if(ready){ //준비를 했다면 준비를 붙힘
					message = "[준비]" + message;
				}
				Broadcast.execute(message);
			}
		} // else
	} // freeChatting	
}
