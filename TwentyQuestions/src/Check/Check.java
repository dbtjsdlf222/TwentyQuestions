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
	
	public Check() {}  //�� �����޼��� ���� vo�� �ȹ޾Ƶ���

	public void iIndexCheck() { 			   //i�� �Ѿ���� üũ
		if (nameList.indexOf(tagger) == i) {
			i++;
		} // if
		if (nameList.size() <= i) {
			if(nameList.indexOf(tagger) == 0){ // 0�ε����� ������ 1�ε����� �����Ѵ�
				i = 1;
			}else{
				i = 0;
			}
		} // if
		turnName = nameList.get(i);
	} // iIndexCheck

	public void answerCheck(String message) {
		try {
			vo.getPw().println("������ �Է����ּ���");
			message = vo.getBr().readLine();
			Broadcast.execute(message);

			if (message.equals(vo.getName() + ": " + aws)) {   	 //����� message�� �������
				Broadcast.execute("[" + vo.getName() + "]���� ������ ���߼̽��ϴ�.");
				Broadcast.execute("����: [" + aws + "]\n GameEnd");
				new GameStartEnd().gameEnd();
				
			} else {
				Broadcast.execute("������ �ƴմϴ�.");
				new Increase().turn();
				new Increase().question(); //���䵵 ����Ƚ�� ����
				Broadcast.execute("["+turnName+"]�� �����Դϴ�");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // answerCheck
} //Check