import java.util.HashSet;

/**
 * Project class that represents the projects. Within the class we store the name of the project,
 * define if the project is large, members and the heads of the project.
 */

public class Project {

	private String name;
	private boolean large;
	private HashSet<Person> heads;
	private HashSet<Person> projectMembers;
	
	/**
	 * Project Constructor
	 * @param name - String name of the project
	 */
	public Project(String name)
	{
		this.name = name;
		this.large = false;
		this.heads = new HashSet<Person>();
		this.projectMembers = new HashSet<Person>();
	}
	
	/**
	 * Method to get the name of the project
	 * @return name - String name of the project
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method to get the HashSet of project members
	 * @return projectMembers - HashSet of project members
	 */
	public HashSet<Person> getProjectMembers()
	{
		return this.projectMembers;
	}
	
	/**
	 * Method to add person to the HashSet of project members
	 * @param person - Person object to add to the HashSet
	 */
	public void addProjectMember(Person person)	{
		this.projectMembers.add(person);
	}
	
	/**
	 * Method to add person to the HashSet of project heads 
	 * (assumed that there can be multiple project heads)
	 * @param person - Person object to add to the HashSet
	 */
	public void addProjectHead(Person person)	{
		this.heads.add(person);
	}
	
	/**
	 * Method to get the HashSet of project heads
	 * @return heads - HashSet of project heads
	 */
	public HashSet<Person> getProjectHeads()	{
		return this.heads;
	}

	/**
	 * Method to set project to be large
	 * @param islarge - boolean that defines if the project is large
	 */
	public void setLarge(boolean islarge)
	{
		this.large = islarge;
	}
	
	/**
	 * Method to see if the project is large
	 * @return large - boolean if the project is large or not
	 */
	public boolean getLarge()
	{
		return this.large;
	}
}
