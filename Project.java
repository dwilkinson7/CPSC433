import java.util.ArrayList;
import java.util.HashSet;

public class Project {

	private String name;
	private boolean large;
	private HashSet<Person> heads;
	private HashSet<Person> projectMembers;
	
	
	public Project(String name)
	{
		this.name = name;
		this.large = false;
		this.heads = new HashSet<Person>();
		this.projectMembers = new HashSet<Person>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public HashSet<Person> getProjectMembers()
	{
		return this.projectMembers;
	}
	
	public void addProjectMember(Person person)	{
		this.projectMembers.add(person);
	}
	
	public void addProjectHead(Person person)	{
		this.heads.add(person);
	}
	
	public HashSet<Person> getProjectHeads()	{
		return this.heads;
	}

	public void setLarge(boolean islarge)
	{
		this.large = islarge;
	}
	
	public boolean getLarge()
	{
		return this.large;
	}
}
