import java.util.ArrayList;
import java.util.HashSet;

public class Project {

	public String name;
	public boolean large;
	public HashSet<Person> heads;
	public HashSet<Person> projectMembers;
	
	
	public Project(String name)
	{
		this.name = name;
		this.large = false;
		this.heads = new HashSet<Person>();
		this.projectMembers = new HashSet<Person>();
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

}
