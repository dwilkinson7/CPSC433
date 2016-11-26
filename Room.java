import java.util.ArrayList;
import java.util.HashSet;

public class Room {

	private String size;
	private String name;
	private ArrayList<Person> assigned = new ArrayList<Person>(); 
	private HashSet<Room> closeRooms = new HashSet<Room>();
	private boolean full;
	
	public Room(String name)
	{
		this.name = name;
		full = false;
		size = "medium";
	}
	
	public boolean isFull()
	{
		return this.full;
	}
	public String getName()
	{
		return this.name;
	}
	public void assignPerson(Person person)
	{
		assigned.add(person);
		if (person.getManager() == true || assigned.size() == 2)
				this.full = true;
	}
	public void removePerson(Person person)
	{
		assigned.remove(person);
		if (person.getManager() == true || assigned.size() < 2)
			this.full = false;
	}
	
	public void assignClose(Room room)
	{
		this.closeRooms.add(room);
	}
	
	public HashSet<Room> getCloseRooms()
	{
		return this.closeRooms;
	}
	
	public void setRoomSize(String str)
	{
		this.size = str;
	}
	
	public String getRoomSize()
	{
		return this.size;
	}
	
	public ArrayList<Person> getAssigned()
	{
		return this.assigned;
	}
}
