package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;
	Boolean seatsLeft;

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
		this.seatsLeft = true;
	}

	public void run() {
		System.out.flush();
		if (seatsLeft)
		{	
		try {
			
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			//ticket checker here? not sure what flag to use
			//System.out.println("Requesting ticket");
			out.println("Seat Request");
			
			while(true){
				if (in.ready()) {
					String input = in.readLine();
					if (!input.equals("go away")) {
						System.out.println("Reserved Seat " + input + " for " + threadname);
					} else {
						System.out.println("No seat reserved for " + this.threadname + ". Closing offices...");
						seatsLeft = false;
					}
					break;
					
				} else {
					Thread.sleep(10);
				}
			}
			//System.out.println("closing Socket");
			echoSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
}

public class TicketClient {
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";

	TicketClient(String hostname, String threadname) {
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
	}

	TicketClient(String name) {
		this("localhost", name);
	}

	TicketClient() {
		this("localhost", "unnamed client");
	}

	void requestTicket() {
		// TODO thread.run()
		tc.run();
		//System.out.println(hostName + "," + threadName + " got one ticket");
	}

	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
