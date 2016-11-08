import java.util.ArrayList;

public class Room {

	public String size;
	public String name;
	public Person assigned[] = new Person[2]; 
	public ArrayList<Room> closeRooms = new ArrayList<Room>();
	public boolean full;
	
	public Room(String name)
	{
		this.name = name;
		full = false;
	}
	
	
}
