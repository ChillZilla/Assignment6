package Assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	Boolean[][] seats = new Boolean[26][28];
	theater theaterSeats = new theater();
	int numCustomers = 0;
	int numSeats = 528;
	
	ThreadedTicketServer()
	{
		for(int i = 0; i < 26; i ++)
		{
			for (int j = 0; j < 28; j ++)
			{
				this.seats[i][j] = true;
			}
		}
	}
	
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
	
	public int bestAvailableSeat()
	{
		for (int i = 0; i < 26; i++) //rows a through b. 
		{
			for (int j = 8; j < 21; j++) //checks middle
				if (seats[i][j] == true)
					return (i*100+j);
			for (int j = 0; j < 8; j++) //checks side
				if (seats[i][j] == true)
					return (i*100+j);
			for (int j = 21; j < 28; j++)  //checks other side.
				if (seats[i][j] == true)
					return (i*100+j);
		}
		return -1;
	}
	
	public void markAvailableSeatTaken(int seat)
	{
		seats[seat/100][seat%100] = false;
		numSeats-=1;
	}
	
	public void printAvailableSeat(int seat)
	{
		System.out.println("Admit One to EE422C: Row " + (char)((seat/100) + 65) + ", Seat" + (seat%100 + 100));
	}
}