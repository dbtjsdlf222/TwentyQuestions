package Vo;

import java.util.ArrayList;

public class ServerVo {
	
	protected static ArrayList<ClientVo> list = new ArrayList<ClientVo>();
	protected static ArrayList<String> nameList = new ArrayList<String>();
	
	protected static boolean gameState = false;     // ���� ������� (�������̸� 1 �ƴϸ� 0)
	protected static int questionCount = 0; // ���� Ƚ���� 20�� �ɰ�� ��
	protected static int i = 0; 		    // �� ����
	protected static String turnName = null;// ������ ������ ������ �̸��� ����
	protected static String aws = null;     // ������ �Է��� ������ ������ ����
	protected static String roomLeader = "";// ������ �̸�����
	protected static String tagger = "";    // ������ �̸� ����
	protected static int taggerAns = 0;     // ������ ������ ���� �ߴ��� üũ ���ߴٸ� ���� �� ���� ����

}
