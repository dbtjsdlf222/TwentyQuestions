package Server;

import java.util.Random;

import Vo.ClientVo;
import Vo.ServerVo;

public class GameStartEnd extends ServerVo{
	
	public int gameStartCheck() {

		if (!(nameList.size() >= 2)) {
			
			Broadcast.execute("인원수가 2명이상이여야 게임을 시작 할 수 있습니다.");
			
			return 0;
			
		}

		for (ClientVo client : list) {   //접속중인 사람이 모두다 준비를 했는지 체크 
			
			if (client.isReady() == false) {
				
				if(!client.getName().equals(roomLeader)){  //방장은 준비를 체크하지 않습니다
					
					Broadcast.execute("모두다 준비를 해야 시작을 할 수 있습니다.");

					return 0;
					
				} //방장인지 체크
			} //for
		} // listFor
		gameStart();
		return 0;
		
	} // gameStartCheck
	
	public void gameStart() {
		Broadcast.execute("게임을 시작합니다");
		Broadcast.execute("정답을 맞출려면 [/정답]  차례를 넘길려면 [/패스]를 입력해주세요 (술래 제외)");
		randomTagger(); 		// 술래 무작위 선정
		gameState = true;
	} // gameStart
	

	public void randomTagger() { //무작위 한사람을 술래로 선정

		Random random = new Random();

		int idx = random.nextInt(nameList.size());

		if(idx == 0) {        //0일경우 질문을 해야하는 첫 번째 이기때문에 뛰어넘습니다  
			new Increase().turn();
		}
		nameList.get(idx);

		tagger = nameList.get(idx);

		Broadcast.execute("술래는 [" + tagger + "]님 입니다. \n술래가 문제를 출제중입니다.....");
		list.get(idx).getPw().println("당신이 술래입니다 문제를 출제해주세요.");
		
	} // randomTagger()
	
	public void gameEnd() { // 게임 종료
		gameState = false;
		questionCount = 0;
		aws = null;
		i = 0;
		taggerAns = 0;
		for (ClientVo client : list) { //준비 초기화
			client.setReady(false);
		} //for
	} // gameEnd
}
