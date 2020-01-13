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
			if (message.equals(name + ": /����")) {
				new GameStartEnd().gameStartCheck();
				
			} else {
				Broadcast.execute("[����]" + message);
			}
		} else {
			if (message.equals(name + ": /�غ�")) {
				if(!ready){
					Broadcast.execute("[" + name + "]" + "�� �غ�Ϸ�");
					vo.setReady(true);
				
				}else{
					pw.println("�̹� �غ� �����Դϴ�.");
				}

			} else if (message.equals(name + ": /�غ�����")) {  //���� ���¿����� �غ����� ����
				if(ready){  
					Broadcast.execute("[" + name + "]" + "�� �غ�����");
					vo.setReady(false);
					
				}else{
					pw.println("�̹� �غ����� �����Դϴ�");
					
				}
			} else { 
				if(ready){ //�غ� �ߴٸ� �غ� ����
					message = "[�غ�]" + message;
				}
				Broadcast.execute(message);
			}
		} // else
	} // freeChatting	
}
