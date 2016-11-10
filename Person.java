import java.util.ArrayList;
import java.util.HashSet;


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

		public String getName(){
			return this.name;
		}

		public void setSecretary(){
			this.secretary = true;
		}

		public boolean getSecretary(){
			return this.secretary;
		}

		public void setResearcher(){
			this.researcher = true;
		}

		public boolean getResearcher(){
			return this.researcher;
		}

		public void setManager(){
			this.manager = true;
		}

		public boolean getManager(){
			return this.manager;
		}

		public void setSmoker(){
			this.smoker = true;
		}

		public boolean getSmoker(){
			return this.smoker;
		}

		public void setHacker(){
			this.hacker = true;
		}

		public boolean getHacker(){
			return this.hacker;
		}

		public void addGroup(Grp g){
			this.groups.add(g);
		}
		
		
		public HashSet<Project> getProjectsList(){
			return this.projects;
		}
		
		public void addProject(Project proj)	{
			this.projects.add(proj);
		}

		public void setProjectHead(Project p){
			this.headproject.add(p);
		}

		public HashSet<Project> getProjectHead(){
			return this.headproject;
		}

		public void setGroupHead(Grp g){
			this.headgroup.add(g);
		}

		public HashSet<Grp> getGroupHeadList(){
			return this.headgroup;
		}

		public HashSet<Person> getWorksWith(){
			return this.worksWith;
		}
		
		public void setWorksWith(Person worksWith) {
			if (!this.worksWith.contains(worksWith))
				this.worksWith.add(worksWith);
		}

		public HashSet<Grp> getGroupsList() {
			return groups;
		}

		public Room getRoom() {
			return room;
		}

		public void setRoom(Room room) {
			this.room = room;
		}

	}