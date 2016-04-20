package assignment6;

public class theaterSeat {//capitalize this plz ty

	int row;
	int seatNum;
	String seatName;
	boolean taken; //false if available, true if taken
	
	theaterSeat(int index, char letter, int j)
	{
		this.row = index;
		this.seatNum = j;
		Integer num = (Integer) j;
		this.seatName = letter + num.toString();
		this.taken = false;
	}
	
}
