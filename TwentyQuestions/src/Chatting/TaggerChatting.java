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
		Check check = new Check(vo); //체크
		
		if(aws == null){
			message = message.substring(message.indexOf(":") + 1, message.length()); //이름을 뺴고 답만저장
			aws = message.trim();
			Broadcast.execute("문제가 출제되었습니다.");
			check.iIndexCheck();
			Broadcast.execute("["+turnName+"]님의 차례입니다.");
		} else {
			if (taggerAns == 1) {

				if (message.equals(name + ": 네") || 
					message.equals(name + ": 아니오")||
				    message.equals(name + ": 모르겠습니다")) {
					Broadcast.execute("[술래]" + message);
					taggerAns = 0;
					Broadcast.execute("[" + turnName + "]님의 차례입니다."); // turnName
				} else {
					pw.println("술래는 '네', '아니오', '모르겠습니다' 만 입력가능합니다.");
				}
			} else {
				pw.println("아직 질문을 하지 않았습니다.");
			}
		}
	} // taggerMessageCheck
}
