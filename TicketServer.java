package assignment6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class TicketServer {
	static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;
	
	public static void start(int portNumber) throws IOException {
		PORT = portNumber;
		Runnable serverThread = new ThreadedTicketServer();
		Thread t = new Thread(serverThread);
		
		t.start();
	}
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	theater theaterSeats = new theater();
	
	public void run() {
		// TODO 422C
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(TicketServer.PORT);
			
			while(true){ 
			//System.out.println("accepting connection to client");
			Socket clientSocket = serverSocket.accept();
			serverConnectionManager serverConnect = new serverConnectionManager(clientSocket, this.theaterSeats);
			serverConnect.start();
			
			}
			
			
			//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}