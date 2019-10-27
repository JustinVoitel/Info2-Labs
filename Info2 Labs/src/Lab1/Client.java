package Lab1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread{
	

	static Socket clientSocket;
	
	
	static DataOutputStream dout;
	static DataInputStream din;
	
	static String msgin = "";
	static String msgout = "";
	
	static BufferedReader br;
	
	public static void main(String[] args) throws Exception {

		clientSocket = new Socket("172.30.80.1", 8000);
		
		
		br = new BufferedReader(
				new InputStreamReader(System.in));
			
		
		Client myclient = new Client();
		myclient.start();
		
		writer();
	}
	

	public static void writer() throws IOException {
		
		dout = new DataOutputStream(clientSocket.getOutputStream());
		
		
		while(!msgout.equals("end")) {
			msgout = br.readLine();
			dout.writeUTF(msgout);
			dout.flush();
		}
	}
	public static void reader() throws IOException {
		din = new DataInputStream(clientSocket.getInputStream());
		
		while(!msgout.equals("end")) {
		
			msgin = din.readUTF();
			System.out.println("Server: " + msgin);
		
		}
		clientSocket.close();
	}
	
	@Override
	public void run() {
		try {
			reader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

