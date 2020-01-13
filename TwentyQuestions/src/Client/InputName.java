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
			
			pw.println("�̸��� �Է����ּ���. (�� ���� �̻�)");
			
			while (true) {
				vo.setName(vo.getBr().readLine());
				
				if (!nameList.contains(vo.getName())) {
					nameList.add(vo.getName());
					list.add(vo);
					Broadcast.execute("[" + vo.getName() + "]���� �����ϼ̽��ϴ�.");
					if(nameList.size()==1) {vo.getPw().println(vo.getName()+"���� �����Դϴ�");}
					pw.println("�غ� �Ǽ����� [/�غ�], ���Ḧ ���Ͻø� [/����]�� �Է����ּ���");
					pw.println("������ ��� �÷��̾ �غ�Ǹ� [/����]�� �Է����ּ���");
					
					break;
				} else {
					pw.println("�̸��� �ߺ��˴ϴ� �ٽ� �Է����ּ���.");
				} // if~else
			} // while
		} catch (IOException e) {
		} catch(Exception e){}
	} // InputName
} //InputName