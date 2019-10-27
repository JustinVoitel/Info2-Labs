package Lab1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	
	static int startport = 8000;
	static int Socketrange = 11;
	
	static Socket socket[] = new Socket[Socketrange];
	static ServerSocket serverSocket[] = new ServerSocket[Socketrange];
	
	static DataInputStream din[] = new DataInputStream[Socketrange];
	static DataOutputStream dout[] = new DataOutputStream[Socketrange];
	
	static BufferedReader br[] = new BufferedReader[Socketrange];
	
	static String msgin = "";
	static String msgout = "";

	
	public static void main(String[] args) throws Exception{
		
		//
		
		for(int i = 0; i<=10;i++) {
			serverSocket[i] = new ServerSocket(startport + i);
		}

		socket[0] = serverSocket[0].accept();
		System.out.println("Server is now running on ports: " + startport + "-" + serverSocket[Socketrange-1].getLocalPort());
		
				
		br[0] = new BufferedReader(
				new InputStreamReader(System.in));
		
		
		Server myserver = new Server();
			myserver.start();
			
		writer();
	}
	public static void reader() throws IOException {
		
		din[0] = new DataInputStream(socket[0].getInputStream());
		
		while(!msgout.equals("end")) {
			msgin = din[0].readUTF();
			System.out.println(socket[0].getInetAddress()+": " + msgin);
		}
		serverSocket[0].close();
		
	}
	
	public static void writer() throws IOException {
		
		dout[0] = new DataOutputStream(socket[0].getOutputStream());
		
		while(!msgout.equals("end")) {
			msgout = br[0].readLine();
			
			dout[0].writeUTF(msgout);
			dout[0].flush();
		}
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
