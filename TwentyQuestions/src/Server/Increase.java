package Server;

import Check.Check;
import Vo.ServerVo;

public class Increase extends ServerVo{
	
	public void question(){
		if (questionCount < 20) {
			questionCount++;
			Broadcast.execute(questionCount + " ��");
			turn();
			
		} else {
			Broadcast.execute("���� Ƚ���� �ʰ� �Ǿ����ϴ�.");
			Broadcast.execute("����: [" + aws + "]\nGameOver");
			new GameStartEnd().gameEnd();
		}
	} //questionIncrease
	
	public void turn() {
		i++;
		new Check().iIndexCheck();
		turnName = nameList.get(i);
	} //turnIncrease
	
}
