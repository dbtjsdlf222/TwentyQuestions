package Server;

import Vo.ServerVo;

public class Broadcast extends ServerVo{
	
	public synchronized static void execute(String message) {
		for (int i = 0; i < ServerVo.list.size(); i++) { // list에 있는 사람들만큼 돌린다(모든사람)
			list.get(i).getPw().println(message); // pw는 outPut메시지를 가져와 출력한다
		} // for
	} // broadcast
}