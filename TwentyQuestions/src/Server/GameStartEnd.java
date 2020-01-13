package Server;

import java.util.Random;

import Vo.ClientVo;
import Vo.ServerVo;

public class GameStartEnd extends ServerVo{
	
	public int gameStartCheck() {

		if (!(nameList.size() >= 2)) {
			
			Broadcast.execute("�ο����� 2���̻��̿��� ������ ���� �� �� �ֽ��ϴ�.");
			
			return 0;
			
		}

		for (ClientVo client : list) {   //�������� ����� ��δ� �غ� �ߴ��� üũ 
			
			if (client.isReady() == false) {
				
				if(!client.getName().equals(roomLeader)){  //������ �غ� üũ���� �ʽ��ϴ�
					
					Broadcast.execute("��δ� �غ� �ؾ� ������ �� �� �ֽ��ϴ�.");

					return 0;
					
				} //�������� üũ
			} //for
		} // listFor
		gameStart();
		return 0;
		
	} // gameStartCheck
	
	public void gameStart() {
		Broadcast.execute("������ �����մϴ�");
		Broadcast.execute("������ ������� [/����]  ���ʸ� �ѱ���� [/�н�]�� �Է����ּ��� (���� ����)");
		randomTagger(); 		// ���� ������ ����
		gameState = true;
	} // gameStart
	

	public void randomTagger() { //������ �ѻ���� ������ ����

		Random random = new Random();

		int idx = random.nextInt(nameList.size());

		if(idx == 0) {        //0�ϰ�� ������ �ؾ��ϴ� ù ��° �̱⶧���� �پ�ѽ��ϴ�  
			new Increase().turn();
		}
		nameList.get(idx);

		tagger = nameList.get(idx);

		Broadcast.execute("������ [" + tagger + "]�� �Դϴ�. \n������ ������ �������Դϴ�.....");
		list.get(idx).getPw().println("����� �����Դϴ� ������ �������ּ���.");
		
	} // randomTagger()
	
	public void gameEnd() { // ���� ����
		gameState = false;
		questionCount = 0;
		aws = null;
		i = 0;
		taggerAns = 0;
		for (ClientVo client : list) { //�غ� �ʱ�ȭ
			client.setReady(false);
		} //for
	} // gameEnd
}
