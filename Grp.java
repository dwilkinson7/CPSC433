import java.util.HashSet;

public class Grp {

	public String name;
	private HashSet<Person> heads;
	private HashSet<Person> groupMembers;
	private HashSet<Person> secretaryList;
	private HashSet<Person> managerList;
	private HashSet<Person> largeProjectHeadsList;
	
	
	public Grp(String name)
	{
		this.name = name;
		this.groupMembers = new HashSet<Person>();
		this.heads = new HashSet<Person>();
		this.secretaryList = new HashSet<Person>();
		this.managerList = new HashSet<Person>();
		this.largeProjectHeadsList = new HashSet<Person>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public HashSet<Person> getGroupMembers()
	{
		return this.groupMembers;
	}
	
	public HashSet<Person> getGroupSecretaries()
	{
		return this.secretaryList;
	
	}
	
	public void addGroupSecretary(Person person)
	{
		this.secretaryList.add(person);
	}
	
	public HashSet<Person> getLargeProjectHeads()
	{
		return this.largeProjectHeadsList;
	
	}
	
	public void addLargeProjectHead(Person person)
	{
		this.largeProjectHeadsList.add(person);
	}
	
	
	public HashSet<Person> getGroupManagers()
	{
		return this.managerList;
	
	}
	
	public void addGroupManager(Person person)
	{
		this.managerList.add(person);
	}
	
	public void addGroupMember(Person person)	{
		this.groupMembers.add(person);
	}
	
	public void addGroupHead(Person person)
	{
		this.heads.add(person);
	}
	
	public HashSet<Person> getGroupHeads()	{
		return this.heads;
	}
}