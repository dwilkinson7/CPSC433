import java.util.ArrayList;
import java.util.HashSet;

/**
* Room class to represent the room. Within this class we store information about room size, 
* people assigned to this room, close by rooms, 
* the name of the room and the size of the room (default assumed to be medium).
*/

public class Room {

	private String size;
	private String name;
	private ArrayList<Person> assigned = new ArrayList<Person>(); 
	private HashSet<Room> closeRooms = new HashSet<Room>();
	private boolean full;
	
	/**
	 * Room constructor method
	 * @param name - room name
	 */
	public Room(String name)
	{
		this.name = name;
		full = false;
		size = "medium";
	}
	
	/**
	 * Instead of hashing based on object id we hash based on String name
	 * Makes hashing more predictable
	 */
	@Override
	public int hashCode()
	{
		return this.getName().hashCode();
	}
	
	/**
	 * Method to check if the room is full
	 * @return full - boolean to see if the room is full
	 */
	public boolean isFull()
	{
		return this.full;
	}
	
	/**
	 * Method to get room name 
	 * @return name - String with the room name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method to assign person to the room
	 * @param person - Person object, represents person we are assigning to this room
	 */
	public void assignPerson(Person person)
	{
		assigned.add(person);
		if (person.getManager() == true || person.getProjectHead().size() > 0 || person.getGroupHeadList().size() > 0 || assigned.size() == 2)
				this.full = true;
	}
	
	/**
	 * Method to remove person assigned to this room
	 * @param person - Person object, represents person we remove from this room
	 */
	public void removePerson(Person person)
	{
		assigned.remove(person);
		if (person.getManager() == true || person.getProjectHead().size() > 0 || person.getGroupHeadList().size() > 0 || assigned.size() < 2)
			this.full = false;
	}
	
	/**
	 * Method to add room that is close to this Room to the HashSet of close rooms
	 * @param room - Room object, represents room that is close to this room
	 */
	public void assignClose(Room room)
	{
		this.closeRooms.add(room);
	}
	
	/**
	 * Get the HashSet of close rooms
	 * @return closeRooms - HashSet of close rooms
	 */
	public HashSet<Room> getCloseRooms()
	{
		return this.closeRooms;
	}
	
	/**
	 * Method to set the size of the room
	 * @param str - string that represents the size of the room
	 */
	public void setRoomSize(String str)
	{
		this.size = str;
	}
	
	/**
	 * Method to get the room size
	 * @return size - String that represents the size of the room
	 */
	public String getRoomSize()
	{
		return this.size;
	}
	
	/**
	 * Method to get the list of people assigned to this room
	 * @return assigned - ArrayList of Person objects that represent people assigned to this room
	 */
	public ArrayList<Person> getAssigned()
	{
		return this.assigned;
	}
}

