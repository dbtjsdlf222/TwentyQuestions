package Server;

import Check.Check;
import Vo.ServerVo;

public class Increase extends ServerVo{
	
	public void question(){
		if (questionCount < 20) {
			questionCount++;
			Broadcast.execute(questionCount + " 턴");
			turn();
			
		} else {
			Broadcast.execute("제한 횟수가 초과 되었습니다.");
			Broadcast.execute("정답: [" + aws + "]\nGameOver");
			new GameStartEnd().gameEnd();
		}
	} //questionIncrease
	
	public void turn() {
		i++;
		new Check().iIndexCheck();
		turnName = nameList.get(i);
	} //turnIncrease
	
}
