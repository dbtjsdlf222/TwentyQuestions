package Server;

import Vo.ClientVo;
import Vo.ServerVo;

public class Quit extends ServerVo{
	
	public Quit(ClientVo vo) {
		
		String name = vo.getName();
		
		System.out.println(name + "���� ����"); // name���� ����
		list.remove(this);	 // ServerWorker thread ������ ����Ʈ���� ����
		Broadcast.execute("[" +name + "]���� �����߽��ϴ�.");

		if (null != name) {
			int removeIdx = nameList.indexOf(name);
			nameList.remove(removeIdx);

			if (gameState) { 	// ���� ���϶�

				if (nameList.size() - 1 == removeIdx)  i = 0;   
				else if (removeIdx <= i)  i++; //���� �״�� 
				else if (removeIdx == i)  Broadcast.execute("[" + name + "]���� �����Դϴ�."); 

				if (tagger.equals(name)) {
					
					Broadcast.execute("������ �����Ͽ����ϴ� GameOver");
					
					new GameStartEnd().gameEnd();
					
				} else if(nameList.size()==1) {
					
					Broadcast.execute("�ο����� �����մϴ�. GameOver");
					
					new GameStartEnd().gameEnd();
					
				}
			} // if~gameState
			
			if (removeIdx == 0) {
				try {
					roomLeader = nameList.get(0);
					Broadcast.execute("[" + roomLeader + "]���� ������ �Ǿ����ϴ�.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("������ ������ϴ�.");
				} // try~catch
			}
		}
	} //Quit
}