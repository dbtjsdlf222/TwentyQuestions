package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunClient {
	
	private Socket socket;       //������ ����ϱ� ���� ���� 
	protected BufferedReader consoleReader; //�ֿܼ��� �Է¹ޱ� ���� ��Ʈ�� 
	protected PrintWriter pw;      //������ ������ ����� ���� ��Ʈ�� 
	protected BufferedReader br; //�������� ���� �޼����� �Է¹ޱ� ���� ��Ʈ��
	protected String name = null;

	public void clientEngine() throws UnknownHostException, IOException{
		
		try{
			Scanner sc = new Scanner(System.in);
			
			System.out.println("������ ���� ip �ּҸ� �Է����ּ���");
			while (true) {
				try {
					String ip = sc.nextLine();
					socket = new Socket(ip,4999);
					System.out.println("====������ ���� �����Ͽ����ϴ�====");
					break;
				} catch (Exception e) {
					System.out.println("ip�ּҸ� �ٽ� �Է����ּ���.");
				}
			}
			
			consoleReader = new BufferedReader(new InputStreamReader(System.in));
			
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));  

			String message = br.readLine();
			System.out.println(message); //�̸��� �Է����ּ��並 ���� 

			nameInputCheck(); //�̸��� �Է¹ް� ������ üũ���� ����

			//ClientWorker Thread �� �����ؼ� start��Ų��
			Thread thread = new Thread(new ClientWorker());       
			thread.setDaemon(true); // main thread ����Ǹ� ClientWorker thread�� �Բ� ����
			thread.start();
			//main thread�� ClientWorker thread�� start ��Ų �� 
			//������ �����͸� ���(������)�ϴ� �۾��� �����Ѵ�

			while(true){
				message = consoleReader.readLine();
				if(message.trim().length() == 0){
					System.out.println("������ �Է� �� �� �����ϴ�");
				}else{
					pw.println(name+": " + message);
				} //if~else
			} //while
		} //try
		finally{
			if(pw != null)
				pw.close();
			if(consoleReader != null)
				consoleReader.close();
			if(socket != null)
				socket.close();
		} //try~finally
	} //clientEngine

	//�������� �Է¹޴� ������
	class ClientWorker implements Runnable {  //Ŭ���̾�Ʈ �ܿ��� �������� ���� �޽��� �����ϴ� ������

		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				while(true){
					String message = br.readLine();
					if(message==null){
						System.out.println("�����մϴ�...");
						break;
					}
					System.out.println(message);
				} //while
			} //try
			catch (SocketException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}
			finally{
				try {
					if(br != null) br.close();
				} //try
				catch (IOException e) {e.printStackTrace();} //try~catch
			}//try~finally
		} //run
	} //ClientWorker

	public void nameInputCheck() {
		try {
			String message;
			while(true){
				name = consoleReader.readLine();
				
				if(name.trim().length() == 0){
					System.out.println("�̸��� �������� �� �� �����ϴ�");
					
				} else if(name.length()<2) {
					System.out.println("�̸��� �� ���� �̻��̿��� �մϴ�");
					
				} else {
					pw.println(name);
					message = br.readLine();
					System.out.println(message);
					if(!message.equals("�̸��� �ߺ��˴ϴ� �ٽ� �Է����ּ���.")){ 
						break;
					}
				} //if~else
			} //while
		} catch (IOException e) {e.printStackTrace();}
	} //nameInputCheck
	
	public static void main(String[] args) {
		try {
			new RunClient().clientEngine();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} //try~catch
	} //main
}