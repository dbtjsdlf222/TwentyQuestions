package Server;

import Vo.ServerVo;

public class Broadcast extends ServerVo{
	
	public synchronized static void execute(String message) {
		for (int i = 0; i < ServerVo.list.size(); i++) { // list�� �ִ� ����鸸ŭ ������(�����)
			list.get(i).getPw().println(message); // pw�� outPut�޽����� ������ ����Ѵ�
		} // for
	} // broadcast
}