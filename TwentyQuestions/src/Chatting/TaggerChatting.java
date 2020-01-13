package Chatting;

import java.io.PrintWriter;

import Check.Check;
import Server.Broadcast;
import Vo.ClientVo;
import Vo.ServerVo;

public class TaggerChatting extends ServerVo{

public void taggerChatting(String message, ClientVo vo) {

		String name = vo.getName();
		PrintWriter pw = vo.getPw();
		Check check = new Check(vo); //üũ
		
		if(aws == null){
			message = message.substring(message.indexOf(":") + 1, message.length()); //�̸��� ���� �丸����
			aws = message.trim();
			Broadcast.execute("������ �����Ǿ����ϴ�.");
			check.iIndexCheck();
			Broadcast.execute("["+turnName+"]���� �����Դϴ�.");
		} else {
			if (taggerAns == 1) {

				if (message.equals(name + ": ��") || 
					message.equals(name + ": �ƴϿ�")||
				    message.equals(name + ": �𸣰ڽ��ϴ�")) {
					Broadcast.execute("[����]" + message);
					taggerAns = 0;
					Broadcast.execute("[" + turnName + "]���� �����Դϴ�."); // turnName
				} else {
					pw.println("������ '��', '�ƴϿ�', '�𸣰ڽ��ϴ�' �� �Է°����մϴ�.");
				}
			} else {
				pw.println("���� ������ ���� �ʾҽ��ϴ�.");
			}
		}
	} // taggerMessageCheck
}
