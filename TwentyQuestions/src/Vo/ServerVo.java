package Vo;

import java.util.ArrayList;

public class ServerVo {
	
	protected static ArrayList<ClientVo> list = new ArrayList<ClientVo>();
	protected static ArrayList<String> nameList = new ArrayList<String>();
	
	protected static boolean gameState = false;     // 게임 진행상태 (게임중이면 1 아니면 0)
	protected static int questionCount = 0; // 질문 횟수가 20이 될경우 끝
	protected static int i = 0; 		    // 턴 제한
	protected static String turnName = null;// 지금이 누구의 턴인지 이름을 저장
	protected static String aws = null;     // 술래가 입력한 게임의 정답을 저장
	protected static String roomLeader = "";// 방장의 이름저장
	protected static String tagger = "";    // 술래의 이름 저장
	protected static int taggerAns = 0;     // 술래가 질문의 답을 했는지 체크 안했다면 다음 턴 질문 제한

}
