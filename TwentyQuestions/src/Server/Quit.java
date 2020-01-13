package Server;

import Vo.ClientVo;
import Vo.ServerVo;

public class Quit extends ServerVo{
	
	public Quit(ClientVo vo) {
		
		String name = vo.getName();
		
		System.out.println(name + "님이 종료"); // name님이 종료
		list.remove(this);	 // ServerWorker thread 종료전 리스트에서 삭제
		Broadcast.execute("[" +name + "]님이 퇴장했습니다.");

		if (null != name) {
			int removeIdx = nameList.indexOf(name);
			nameList.remove(removeIdx);

			if (gameState) { 	// 게임 중일때

				if (nameList.size() - 1 == removeIdx)  i = 0;   
				else if (removeIdx <= i)  i++; //차례 그대로 
				else if (removeIdx == i)  Broadcast.execute("[" + name + "]님의 차례입니다."); 

				if (tagger.equals(name)) {
					
					Broadcast.execute("술래가 종료하였습니다 GameOver");
					
					new GameStartEnd().gameEnd();
					
				} else if(nameList.size()==1) {
					
					Broadcast.execute("인원수가 부족합니다. GameOver");
					
					new GameStartEnd().gameEnd();
					
				}
			} // if~gameState
			
			if (removeIdx == 0) {
				try {
					roomLeader = nameList.get(0);
					Broadcast.execute("[" + roomLeader + "]님이 방장이 되었습니다.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("서버가 비었습니다.");
				} // try~catch
			}
		}
	} //Quit
}