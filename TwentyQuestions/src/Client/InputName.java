package Client;

import java.io.IOException;
import java.io.PrintWriter;

import Server.Broadcast;
import Vo.ClientVo;
import Vo.ServerVo;

public class InputName extends ServerVo{

	public InputName(ClientVo vo) {
		try {
			PrintWriter pw = vo.getPw(); 
			
			pw.println("이름을 입력해주세요. (두 글자 이상)");
			
			while (true) {
				vo.setName(vo.getBr().readLine());
				
				if (!nameList.contains(vo.getName())) {
					nameList.add(vo.getName());
					list.add(vo);
					Broadcast.execute("[" + vo.getName() + "]님이 입장하셨습니다.");
					if(nameList.size()==1) {vo.getPw().println(vo.getName()+"님이 방장입니다");}
					pw.println("준비가 되셨으면 [/준비], 종료를 원하시면 [/종료]를 입력해주세요");
					pw.println("방장은 모든 플레이어가 준비되면 [/시작]을 입력해주세요");
					
					break;
				} else {
					pw.println("이름이 중복됩니다 다시 입력해주세요.");
				} // if~else
			} // while
		} catch (IOException e) {
		} catch(Exception e){}
	} // InputName
} //InputName