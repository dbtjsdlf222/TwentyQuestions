package Vo;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

//������ �������� ����
public class ClientVo {
	protected Socket socket;
	protected BufferedReader br; // Ŭ���̾�Ʈ�κ��� �Է¹޴� ��Ʈ��
	protected PrintWriter pw; // Ŭ���̾�Ʈ�� ����ϴ� ��Ʈ��
	protected String name;
	protected boolean ready = false;
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public BufferedReader getBr() {
		return br;
	}
	public void setBr(BufferedReader br) {
		this.br = br;
	}
	public PrintWriter getPw() {
		return pw;
	}
	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
