package Check;

import java.io.IOException;
import Server.Broadcast;
import Server.GameStartEnd;
import Server.Increase;
import Vo.ClientVo;
import Vo.ServerVo;

public class Check extends ServerVo{
	
	private ClientVo vo = null;
	
	public Check(ClientVo vo) {
		this.vo = vo;
	}
	
	public Check() {}  //턴 증가메서드 에서 vo를 안받아도됨

	public void iIndexCheck() { 			   //i가 넘어가는지 체크
		if (nameList.indexOf(tagger) == i) {
			i++;
		} // if
		if (nameList.size() <= i) {
			if(nameList.indexOf(tagger) == 0){ // 0인덱스가 술래면 1인덱스가 질문한다
				i = 1;
			}else{
				i = 0;
			}
		} // if
		turnName = nameList.get(i);
	} // iIndexCheck

	public void answerCheck(String message) {
		try {
			vo.getPw().println("정답을 입력해주세요");
			message = vo.getBr().readLine();
			Broadcast.execute(message);

			if (message.equals(vo.getName() + ": " + aws)) {   	 //정답과 message가 같을경우
				Broadcast.execute("[" + vo.getName() + "]님이 정답을 맞추셨습니다.");
				Broadcast.execute("정답: [" + aws + "]\n GameEnd");
				new GameStartEnd().gameEnd();
				
			} else {
				Broadcast.execute("정답이 아닙니다.");
				new Increase().turn();
				new Increase().question(); //정답도 질문횟수 증가
				Broadcast.execute("["+turnName+"]님 차례입니다");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // answerCheck
} //Check