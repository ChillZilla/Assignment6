package assignment6;

import java.util.ArrayList;

public class theater {

	ArrayList<ArrayList<theaterSeat>> seating;
	
	theater()
	{
	//	String rowName = "A";
		this.seating = new ArrayList<ArrayList<theaterSeat>>();
		for(char i = 'A'; i <= 'Z'; i ++)
		{
			int index = 0;
			ArrayList<theaterSeat> seats = new ArrayList<theaterSeat>();
			for(int j = 0; j < 28; j ++){
				
			theaterSeat current = new theaterSeat(index, i, j );
			seats.add(current);
			index += 1;
			}
			seating.add(seats); //add the row to the arraylist
		
		}
	}
}
