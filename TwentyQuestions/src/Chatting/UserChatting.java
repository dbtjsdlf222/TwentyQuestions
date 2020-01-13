package Chatting;

import java.io.IOException;
import java.io.PrintWriter;

import Check.Check;
import Server.Broadcast;
import Server.Increase;
import Vo.ClientVo;
import Vo.ServerVo;

public class UserChatting extends ServerVo {

	public void userChatting(String message, ClientVo vo) throws IOException {

		Increase increase = new Increase();
		String name = vo.getName();
		PrintWriter pw =vo.getPw();
		
		if (name.equals(turnName)) {    // ip�� name�� ��ġ�Ұ�츸 ���� ��
			if(taggerAns == 0){ 		//�� ����� ������ �޾Ҵ���
				if (message.equals(name + ": /����")) {
					new Check(vo).answerCheck(message);

				} else {
					if (message.equals(name + ": /pass") || 
						message.equals(name + ": /�н�")) {
						Broadcast.execute(name + "���� �н��ϼ̽��ϴ�.");
						increase.turn();
						Broadcast.execute("["+turnName+"]���� �����Դϴ�");
						
					} else { 		//������ ������ 
						Broadcast.execute(message);
						taggerAns = 1;
						increase.question();
					}
				} // if~else

			} else {
				pw.println("���� �� ������ �亯�� ���� �� �Ͽ����ϴ�.");
			}

		} else {
			pw.println("���ʸ� ��޷��ּ���. [" + turnName + "]���� �����Դϴ�.");
		} // turnName
	} // gameProgress
}