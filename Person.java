import java.util.ArrayList;
import java.util.HashSet;

/**
 * Person class to represent the person. Within the class we store information about the role of the person
 * which projects, groups the person is part of and is head of. If the person is smoker, hacker, researcher etc.
 * Who the person works with and which room the person is assigned to. 
 */

public class Person {

		private String name;
		private boolean secretary;
		private boolean researcher;
		private boolean manager;
		private boolean smoker;
		private boolean hacker;
		private HashSet<Project> projects;
		private HashSet<Person> worksWith;
		private HashSet<Grp> headgroup;
		private HashSet<Project> headproject;
		private HashSet<Grp> groups;
		private Room room;
		
		/**
		 * Constructor method for Person
		 * @param p - String represents name of the Person
		 */
		public Person(String p) {
			this.name = p;
			secretary = false;
			researcher = false;
			manager = false;
			smoker = false;
			hacker = false;
			projects = new HashSet<Project>();
			worksWith = new HashSet<Person>();
			headgroup = new HashSet<Grp>();
			headproject = new HashSet<Project>();
			groups = new HashSet<Grp>();
			
		}

		/**
		 * Method to get the name of the person
		 * @return name - String name of the Person
		 */
		public String getName(){
			return this.name;
		}

		/**
		 * Method to set the boolean that represents if the Person is a secretary
		 */
		public void setSecretary(){
			this.secretary = true;
		}

		/**
		 * Method to see if the Person is a secretary
		 * @return secretary - boolean that shows if a person is a secretary
		 */
		public boolean getSecretary(){
			return this.secretary;
		}

		/**
		 * Method to set the boolean that represents if the Person is a researcher
		 */
		public void setResearcher(){
			this.researcher = true;
		}

		/**
		 * Method to see if the Person is a researcher
		 * @return researcher - boolean that shows if a person is a researcher
		 */
		public boolean getResearcher(){
			return this.researcher;
		}

		/**
		 * Method to set the boolean that represents if the Person is a manager
		 */
		public void setManager(){
			this.manager = true;
		}

		/**
		 * Method to see if the Person is a manager
		 * @return manager - boolean that shows if a person is a manager
		 */
		public boolean getManager(){
			return this.manager;
		}

		/**
		 * Method to set the boolean that represents if the Person is a smoker
		 */
		public void setSmoker(){
			this.smoker = true;
		}

		/**
		 * Method to see if the Person is a smoker
		 * @return smoker - boolean that shows if a person is a smoker
		 */
		public boolean getSmoker(){
			return this.smoker;
		}

		/**
		 * Method to set the boolean that represents if the Person is a hacker
		 */
		public void setHacker(){
			this.hacker = true;
		}

		/**
		 * Method to see if the Person is a hacker
		 * @return smoker - boolean that shows if a person is a hacker
		 */
		public boolean getHacker(){
			return this.hacker;
		}

		/**
		 * Method to add the group the Person is a part of
		 * @param g - Group object
		 */
		public void addGroup(Grp g){
			this.groups.add(g);
		}
		
		/**
		 * Method to fetch the HashSet of the projects the Person is a part of
		 * @return projects - HashSet of projects Person is a part of
		 */
		public HashSet<Project> getProjectsList(){
			return this.projects;
		}
		
		/**
		 * Method to add the Project to the HashSet of project the person is a part of
		 * @param proj - Project object that Person is a part of
		 */
		public void addProject(Project proj)	{
			this.projects.add(proj);
		}

		/**
		 * Method to add project to the HashSet of the projects Person is a head of
		 * @param p - Project object that Person is a head of
		 */
		public void setProjectHead(Project p){
			this.headproject.add(p);
		}

		/**
		 * Method to get the HashSet of projects Person is a head of
		 * @return headproject - HashSet of projects Person is a head of
		 */
		public HashSet<Project> getProjectHead(){
			return this.headproject;
		}

		/**
		 * Method to add group to the HashSet of the group Person is a head of
		 * @param g - Group object the Person is a head of
		 */
		public void setGroupHead(Grp g){
			this.headgroup.add(g);
		}

		/**
		 * Method to get the HashSet of groups Person is a head of
		 * @return headgroup - HashSet of groups Person is a head of
		 */
		public HashSet<Grp> getGroupHeadList(){
			return this.headgroup;
		}

		/**
		 * Metohd to get the HashSet of People the Person works with
		 * @return worksWith - HashSet of Person that this Person works with
		 */
		public HashSet<Person> getWorksWith(){
			return this.worksWith;
		}
		
		/**
		 * Method to add Person object to the HashSet that this Person works with
		 * @param worksWith - Person object that this Person works with
		 */
		public void setWorksWith(Person worksWith) {
			if (!this.worksWith.contains(worksWith))
				this.worksWith.add(worksWith);
		}

		/**
		 * Method to get the HashSet of groups the Person is in
		 * @return group - HashSet of groups that Person is part of
		 */
		public HashSet<Grp> getGroupsList() {
			return groups;
		}

		/**
		 * Method to get the room Person is assigned to
		 * @return room - Room object represents the Room that Person is assigned to
		 */
		public Room getRoom() {
			return room;
		}

		/**
		 * Method to set the Room that Person is assigned to
		 * @param room - Room object that Person is assigned to
		 */
		public void setRoom(Room room) {
			this.room = room;
		}

	}