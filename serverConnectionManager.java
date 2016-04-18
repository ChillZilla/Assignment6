package Assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class serverConnectionManager implements Runnable{

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private theater Tseat;
	
	
	serverConnectionManager(Socket s, theater room)
	{
		try{
			this.socket = s;
			this.out = new PrintWriter(socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.Tseat = room;
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//handle the inputs and outputs here.
		
		String input;
		try {
			input = this.in.readLine();
			while(input != null) //we have a request
			{
			//	System.out.println("We have a request");
				if(input.equals("Seat Request"))
				{
					//pick a seat
					theaterSeat aSeat = bestAvailableSeat();
					if(aSeat != null)
					{
						System.out.println(aSeat.taken);
						out.println(aSeat.seatName);						
						//mark the seat as taken
						//output the seat to the console --- happens on the client end.
					}
					else {out.println("go away");}
				}
				input = this.in.readLine();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void start() {
		// TODO Auto-generated method stub
		this.run();
	}

	synchronized public theaterSeat bestAvailableSeat()
	{
		//need to find the best available seat
		//search the middle first
		for(int rowSearch = 0; rowSearch < 26; rowSearch ++){
			
		for(int search = 8; search < 21; search ++ )
		{
			theaterSeat seat = this.Tseat.seating.get(rowSearch).get(search);
			if(seat.taken == false)
			{
				System.out.println(seat.taken);
				seat.taken = true;
				return seat;
			}
		}
		for(int search = 0; search < 8; search ++)
		{
			theaterSeat seat = this.Tseat.seating.get(rowSearch).get(search);
			if(seat.taken == false)
			{
				System.out.println(seat.taken);
				seat.taken = true;
				return seat;
			}
			
		}
		for(int search = 21; search < 28; search ++)
		{
			theaterSeat seat = this.Tseat.seating.get(rowSearch).get(search);
			if (seat.taken == false)
			{
				System.out.println(seat.taken);
				seat.taken = true;
				return seat;
			}
		}
			
		}
		return null;
		
	}
	
	
}
