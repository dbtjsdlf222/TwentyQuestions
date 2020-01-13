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
	
	private Socket socket;       //서버와 통신하기 위한 소켓 
	protected BufferedReader consoleReader; //콘솔에서 입력받기 위한 스트림 
	protected PrintWriter pw;      //서버로 데이터 출력을 위한 스트림 
	protected BufferedReader br; //서버에서 오는 메세지를 입력받기 위한 스트림
	protected String name = null;

	public void clientEngine() throws UnknownHostException, IOException{
		
		try{
			Scanner sc = new Scanner(System.in);
			
			System.out.println("접속할 서버 ip 주소를 입력해주세요");
			while (true) {
				try {
					String ip = sc.nextLine();
					socket = new Socket(ip,4999);
					System.out.println("====서버에 접속 성공하였습니다====");
					break;
				} catch (Exception e) {
					System.out.println("ip주소를 다시 입력해주세요.");
				}
			}
			
			consoleReader = new BufferedReader(new InputStreamReader(System.in));
			
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));  

			String message = br.readLine();
			System.out.println(message); //이름을 입력해주세요를 받음 

			nameInputCheck(); //이름을 입력받고 형식을 체크한후 종료

			//ClientWorker Thread 를 생성해서 start시킨다
			Thread thread = new Thread(new ClientWorker());       
			thread.setDaemon(true); // main thread 종료되면 ClientWorker thread도 함께 종료
			thread.start();
			//main thread는 ClientWorker thread를 start 시킨 후 
			//서버로 데이터를 출력(보내는)하는 작업을 진행한다

			while(true){
				message = consoleReader.readLine();
				if(message.trim().length() == 0){
					System.out.println("공백은 입력 할 수 없습니다");
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

	//서버에서 입력받는 스레드
	class ClientWorker implements Runnable {  //클라이언트 단에서 서버에서 오는 메시지 감지하는 쓰레드

		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				while(true){
					String message = br.readLine();
					if(message==null){
						System.out.println("종료합니다...");
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
					System.out.println("이름은 공백으로 할 수 없습니다");
					
				} else if(name.length()<2) {
					System.out.println("이름은 두 글자 이상이여야 합니다");
					
				} else {
					pw.println(name);
					message = br.readLine();
					System.out.println(message);
					if(!message.equals("이름이 중복됩니다 다시 입력해주세요.")){ 
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