package Chatting;

import java.io.IOException;
import Server.CreateClientVo;
import Vo.ClientVo;
import Vo.ServerVo;

public class ChattingRelay extends ServerVo {

	public ChattingRelay(ClientVo vo) { 	//상황, 역할에따라 사람들의 메시지를 보냄
		try {
			roomLeader = nameList.get(0);  //초기화
			turnName = nameList.get(i);
			
			TaggerChatting tagger = new TaggerChatting();
			UserChatting user = new UserChatting();
			FreeChatting free = new FreeChatting();
			
			while (true) {
				String message = vo.getBr().readLine();
				if(message.equals(vo.getName() + ": /종료")){
					break;
				}
				if (gameState) { //게임이 시작할 상태면
					if (vo.getName().equals(CreateClientVo.tagger)) {
						tagger.taggerChatting(message, vo);  //술래 채팅으로 메세지 전달
						
					} else {
						if(aws != null) {     	 //게임은 시작했는데 술래가 문제를 출제안한 상태라면 else
							user.userChatting(message, vo);
						
						} else {
							vo.getPw().println("문제 출제 전 까지 대화를 할 수 없습니다.");

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
