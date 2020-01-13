package Chatting;

import java.io.IOException;
import Server.CreateClientVo;
import Vo.ClientVo;
import Vo.ServerVo;

public class ChattingRelay extends ServerVo {

	public ChattingRelay(ClientVo vo) { 	//��Ȳ, ���ҿ����� ������� �޽����� ����
		try {
			roomLeader = nameList.get(0);  //�ʱ�ȭ
			turnName = nameList.get(i);
			
			TaggerChatting tagger = new TaggerChatting();
			UserChatting user = new UserChatting();
			FreeChatting free = new FreeChatting();
			
			while (true) {
				String message = vo.getBr().readLine();
				if(message.equals(vo.getName() + ": /����")){
					break;
				}
				if (gameState) { //������ ������ ���¸�
					if (vo.getName().equals(CreateClientVo.tagger)) {
						tagger.taggerChatting(message, vo);  //���� ä������ �޼��� ����
						
					} else {
						if(aws != null) {     	 //������ �����ߴµ� ������ ������ �������� ���¶�� else
							user.userChatting(message, vo);
						
						} else {
							vo.getPw().println("���� ���� �� ���� ��ȭ�� �� �� �����ϴ�.");

						}
					}
				} else {
					free.freeMessage(message, vo);
				}
			} // while
		} catch (IOException e) {} // try~catch
		  catch (NullPointerException e) {} // try~catch
		
	} // chattingRelay

} //ChattingRelay
