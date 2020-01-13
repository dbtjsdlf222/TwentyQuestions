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
		
		if (name.equals(turnName)) {    // ip와 name이 일치할경우만 말을 할
			if(taggerAns == 0){ 		//전 사람의 질문을 받았는지
				if (message.equals(name + ": /정답")) {
					new Check(vo).answerCheck(message);

				} else {
					if (message.equals(name + ": /pass") || 
						message.equals(name + ": /패스")) {
						Broadcast.execute(name + "님이 패스하셨습니다.");
						increase.turn();
						Broadcast.execute("["+turnName+"]님의 차례입니다");
						
					} else { 		//질문을 했을시 
						Broadcast.execute(message);
						taggerAns = 1;
						increase.question();
					}
				} // if~else

			} else {
				pw.println("아직 전 질문의 답변을 받지 못 하였습니다.");
			}

		} else {
			pw.println("차례를 기달려주세요. [" + turnName + "]님의 차례입니다.");
		} // turnName
	} // gameProgress
}