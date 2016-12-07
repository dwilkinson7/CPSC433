import java.util.HashSet;

/**
 * Group class to represent the groups. Within the class we store name of the group, members of the group,
 * group heads. Secretaries, large project heads and managers within the group.
 */

public class Grp {

	public String name;
	private HashSet<Person> heads;
	private HashSet<Person> groupMembers;
	private HashSet<Person> secretaryList;
	private HashSet<Person> managerList;
	private HashSet<Person> largeProjectHeadsList;
	
	/**
	 * Group Constructor
	 * @param name - String that represents the name of the group
	 */
	public Grp(String name)
	{
		this.name = name;
		this.groupMembers = new HashSet<Person>();
		this.heads = new HashSet<Person>();
		this.secretaryList = new HashSet<Person>();
		this.managerList = new HashSet<Person>();
		this.largeProjectHeadsList = new HashSet<Person>();
	}
	
	/**
	 * Method to get the name of the group
	 * @return name - String name of the group
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method to the HashSet of all the group members
	 * @return groupMembers - HashSet of the members of the group
	 */
	public HashSet<Person> getGroupMembers()
	{
		return this.groupMembers;
	}
	
	/**
	 * Method to get the HashSet of secretaries within the group
	 * @return secretaryList - HashSet of secretaries within the group
	 */
	public HashSet<Person> getGroupSecretaries()
	{
		return this.secretaryList;
	
	}
	
	/**
	 * Method to add the secretary to the HashSet of secretaries in the group
	 * @param person - Person object that represents the secretary being added to the group
	 */
	public void addGroupSecretary(Person person)
	{
		this.secretaryList.add(person);
	}
	
	/**
	 * Method to get the HashSet of large project heads within the group
	 * @return largeProjectHeadList - HashSet of large project heads within the group
	 */
	public HashSet<Person> getLargeProjectHeads()
	{
		return this.largeProjectHeadsList;
	
	}
	
	/**
	 * Method to add the large project heads to the HashSet of large project heads in the group
	 * @param person - Person object that represents the large project heads being added to the group
	 */
	public void addLargeProjectHead(Person person)
	{
		this.largeProjectHeadsList.add(person);
	}
	
	/**
	 * Method to get the HashSet of managers within the group
	 * @return managerList - HashSet of managers within the group
	 */
	public HashSet<Person> getGroupManagers()
	{
		return this.managerList;
	
	}
	
	/**
	 * Method to add the manager to the HashSet of managers in the group
	 * @param person - Person object that represents the manager being added to the group
	 */
	public void addGroupManager(Person person)
	{
		this.managerList.add(person);
	}
	
	/**
	 * Method to add the person to the HashSet of group members in the group
	 * @param person - Person object that represents the group members being added to the group
	 */
	public void addGroupMember(Person person)	{
		this.groupMembers.add(person);
	}
	
	/**
	 * Method to add the group head to the HashSet of group heads in the group
	 * @param person - Person object that represents the group head being added to the group
	 */
	public void addGroupHead(Person person)
	{
		this.heads.add(person);
	}
	
	/**
	 * Method to get the HashSet of group heads within the group
	 * @return heads - HashSet of group heads within the group
	 */
	public HashSet<Person> getGroupHeads()	{
		return this.heads;
	}
}

