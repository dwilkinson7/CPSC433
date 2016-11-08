import java.util.ArrayList;
import java.util.HashSet;

public class Grp {

	public String name;
	private HashSet<Person> heads;
	private HashSet<Person> groupMembers;
	
	
	public Grp(String name)
	{
		this.name = name;
		this.groupMembers = new HashSet<Person>();
		this.heads = new HashSet<Person>();
	}
	
	public HashSet<Person> getGroupMembers()
	{
		return this.groupMembers;
	}
	
	public void addGroupMember(Person person)	{
		this.groupMembers.add(person);
	}
	
	public void addGroupHead(Person person)
	{
		this.heads.add(person);
	}
}