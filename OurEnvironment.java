import java.util.HashMap;
import java.util.TreeSet;

import cpsc433.Environment;
import cpsc433.Pair;
import cpsc433.Predicate.ParamType;
import cpsc433.SisyphusPredicates;

public class OurEnvironment extends Environment implements SisyphusPredicates {

	private static OurEnvironment instance=null;
	public static HashMap<String,Person> peopleList = new HashMap<String,Person>();
	public static HashMap<String,Grp> groupList = new HashMap<String,Grp>();
	public static HashMap<String,Project> projectList = new HashMap<String,Project>();
	public static HashMap<String,Room> roomList = new HashMap<String,Room>();
	
	
	protected OurEnvironment(String name) {
		super(name==null?"theEnvironment":name);
	}
	
	public static Environment get() {
		if (instance==null) instance = new OurEnvironment(null);
		return instance;
	}


	@Override
	public void a_person(String p) {
		// Check if a person does not exist in the list
		if(!peopleList.containsKey(p)){

			Person newPerson = new Person(p); //create new person

			//add new person to the list
			peopleList.put(p, newPerson);
		}
	}

	@Override
	public boolean e_person(String p) {
		if (peopleList.containsKey(p))
				return true;
		return false;
	}

	@Override
	public void a_secretary(String p) {
		//check if the person exists
		if(peopleList.containsKey(p))
			peopleList.get(p).setSecretary(); //set the boolean that person is secretary to true
			
			//else we create a new person
		else {
			Person newPerson = new Person(p); //create new person
			newPerson.setSecretary();

			//add new person to the list
			peopleList.put(p,newPerson);
		}

	}

	@Override
	public boolean e_secretary(String p) {
		if(peopleList.containsKey(p))//if person exists
			return peopleList.get(p).getSecretary();
		return false;
	}

	@Override
	public void a_researcher(String p) {
		//check if the person exists
		if(peopleList.containsKey(p))
			peopleList.get(p).setResearcher(); //set the boolean that person is researcher to true

		//else we create a new person
		else {
			Person newPerson = new Person(p); //create new person
			newPerson.setResearcher();

			//add new person to the list
			peopleList.put(p,newPerson);
		}

	}

	@Override
	public boolean e_researcher(String p) {
		if(peopleList.containsKey(p))//if person exists
			return peopleList.get(p).getResearcher();
		return false;
	}

	@Override
	public void a_manager(String p) {
		//check if the person exists
		if(peopleList.containsKey(p))
			peopleList.get(p).setManager(); //set the boolean that person is secretary to true

		//else we create a new person
		else {
			Person newPerson = new Person(p); //create new person
			newPerson.setManager();

			//add new person to the list
			peopleList.put(p,newPerson);
		}

	}


	@Override
	public boolean e_manager(String p) {
		if(peopleList.containsKey(p))//if person exists
			return peopleList.get(p).getManager();
		return false;
	}

	@Override
	public void a_smoker(String p) {

		//check if the person exists
		if(peopleList.containsKey(p))
			peopleList.get(p).setSmoker(); //set the boolean that person is smoker to true

		//else we create a new person
		else {
			Person newPerson = new Person(p); //create new person
			newPerson.setSmoker();

			//add new person to the list
			peopleList.put(p,newPerson);
		}

	}

	@Override
	public boolean e_smoker(String p) {
		if(peopleList.containsKey(p))//if person exists
			return peopleList.get(p).getSmoker();
		return false;
	}

	@Override
	public void a_hacker(String p) {

		//check if the person exists
		if(peopleList.containsKey(p))
			peopleList.get(p).setHacker(); //set the boolean that person is smoker to true

		//else we create a new person
		else {
			Person newPerson = new Person(p); //create new person
			newPerson.setHacker();

			//add new person to the list
			peopleList.put(p,newPerson);
		}

	}
	
	@Override
	public boolean e_hacker(String p) {
		if(peopleList.containsKey(p))//if person exists
			return peopleList.get(p).getHacker();
		return false;
	}

	@Override
	public void a_group(String p, String grp) {
		// If Group Exists
		if (groupList.containsKey(grp))
		{
			// If Group AND Person exist
			if (peopleList.containsKey(p))
			{
				peopleList.get(p).addGroup(groupList.get(grp));
				groupList.get(grp).addGroupMember(peopleList.get(p));
			}
			// If Group exists but not person
			else
			{
				Person newPerson = new Person(p);
				peopleList.put(p, newPerson);
				newPerson.addGroup(groupList.get(grp));
				groupList.get(grp).addGroupMember(newPerson);
			}
		}
		// If Group doesn't exist
		else
		{
			// If Group doesnt exist but Person exists
			if (peopleList.containsKey(p))
			{
				Grp newGroup = new Grp(grp);
				groupList.put(grp, newGroup);
				peopleList.get(p).addGroup(newGroup);
				groupList.get(grp).addGroupMember(peopleList.get(p));
			}
			// If neither exists
			else
			{
				Grp newGroup = new Grp(grp);
				Person newPerson = new Person(p);
				peopleList.put(p, newPerson);
				groupList.put(grp, newGroup);
				peopleList.get(p).addGroup(newGroup);
				groupList.get(grp).addGroupMember(newPerson);
			}
		}
	}

	@Override
	public boolean e_group(String p, String grp) {
		if (peopleList.get(p).getGroupsList().contains(groupList.get(grp)))
			return true;
		return false; 
	}

	@Override
	public void a_project(String p, String prj) {
		// If Project Exists
				if (projectList.containsKey(prj))
				{
					// If Project AND Person exist
					if (peopleList.containsKey(p))
					{
						peopleList.get(p).addProject(projectList.get(prj));
						projectList.get(prj).addProjectMember(peopleList.get(p));
					}
					// If Project exists but not person
					else
					{
						Person newPerson = new Person(p);
						peopleList.put(p, newPerson);
						peopleList.get(p).addProject(projectList.get(prj));
						projectList.get(prj).addProjectMember(newPerson);
					}
				}
				// If Project doesn't exist
				else
				{
					// If Project doesnt exist but Person exists
					if (peopleList.containsKey(p))
					{
						Project newProject = new Project(prj);
						projectList.put(prj, newProject);
						peopleList.get(p).addProject(newProject);
						projectList.get(prj).addProjectMember(peopleList.get(p));
					}
					// If neither exists
					else
					{
						Project newProject = new Project(prj);
						Person newPerson = new Person(p);
						peopleList.put(p, newPerson);
						projectList.put(prj, newProject);
						peopleList.get(p).addProject(newProject);
						projectList.get(prj).addProjectMember(newPerson);
					}
				}
	}

	@Override
	public boolean e_project(String p, String prj) {
		if (peopleList.get(p).getProjectsList().contains(projectList.get(prj)))
				return true;
		return false; 
	}

	@Override
	public void a_heads_group(String p, String grp) {
		// If person exists
		if(peopleList.containsKey(p))
		{
			// If person and group exist
			if (groupList.containsKey(grp))
			{
				peopleList.get(p).setGroupHead(groupList.get(grp));
				groupList.get(grp).addGroupHead(peopleList.get(p));
			}
			// If person exists but not group
			else
			{
				Grp newGroup = new Grp(grp);
				peopleList.get(p).addGroup(newGroup);
				peopleList.get(p).setGroupHead(newGroup);
				groupList.put(grp, newGroup);
				newGroup.addGroupHead(peopleList.get(p));
			}
		}
		// If person doesn't exist
		else
		{
			// If person doesn't exist but group does
			if (groupList.containsKey(grp))
			{
				Person newPerson = new Person(p);
				peopleList.put(p, newPerson);
				newPerson.setGroupHead(groupList.get(grp));
				groupList.get(grp).addGroupHead(newPerson);
			}
			// If neither exists
			else
			{
				Person newPerson = new Person(p);
				Grp newGroup = new Grp(grp);
				peopleList.put(p, newPerson);
				groupList.put(grp, newGroup);
				newPerson.setGroupHead(newGroup);
				newGroup.addGroupHead(newPerson);
			}
		}
	}

	@Override
	public boolean e_heads_group(String p, String grp) {
		if (peopleList.get(p).getGroupHeadList().contains(groupList.get(grp)))
			return true;
		return false;
	}

	@Override
	public void a_heads_project(String p, String prj) {
		// If person exists
		if(peopleList.containsKey(p))
		{
			// If person and project exist
			if (projectList.containsKey(prj))
			{
				peopleList.get(p).setProjectHead(projectList.get(prj));
				projectList.get(prj).addProjectHead(peopleList.get(p));
			}
			// If person exists but not project
			else
			{
				Project newProject = new Project(prj);
				peopleList.get(p).addProject(newProject);
				peopleList.get(p).setProjectHead(newProject);
				projectList.put(prj, newProject);
				newProject.addProjectHead(peopleList.get(p));
			}
		}
		// If person doesn't exist
		else
		{
			// If person doesn't exist but project does
			if (projectList.containsKey(prj))
			{
				Person newPerson = new Person(p);
				peopleList.put(p, newPerson);
				newPerson.setProjectHead(projectList.get(prj));
				projectList.get(prj).addProjectHead(newPerson);
			}
			// If neither exists
			else
			{
				Person newPerson = new Person(p);
				Project newProject = new Project(prj);
				peopleList.put(p, newPerson);
				projectList.put(prj, newProject);
				newPerson.setProjectHead(newProject);
				newProject.addProjectHead(newPerson);
			}
		}
	}

	@Override
	public boolean e_heads_project(String p, String prj) {
		if (peopleList.get(p).getProjectHead().contains(projectList.get(prj)))
			return true;
		return false; 
	}

	@Override
	public void a_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		//traverse the tree
		for (Pair<ParamType, Object> pair : p2s) {
			String personName = (String)pair.getValue();
			a_works_with(p, personName);
		}

	}

	@Override
	public boolean e_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		
		for (Pair<ParamType, Object> pair : p2s)
		{
			String personName = (String)pair.getValue();
			if (peopleList.get(p).getWorksWith().contains(peopleList.get(personName)))
				;
			else
				return false;
		}
		
		return true;
	}

	@Override
	public void a_works_with(String p, String p2) {
		if(peopleList.containsKey(p) && peopleList.containsKey(p2))
		{
			peopleList.get(p).setWorksWith(peopleList.get(p2));
			peopleList.get(p2).setWorksWith(peopleList.get(p));
		}
		else if (peopleList.containsKey(p) && !peopleList.containsKey(p2))
		{
			Person newPerson = new Person(p2);
			peopleList.get(p).setWorksWith(newPerson);
			peopleList.put(p2,newPerson);
			newPerson.setWorksWith(peopleList.get(p));
		}
		else if (!peopleList.containsKey(p) && peopleList.containsKey(p2))
		{
			Person newPerson = new Person(p);
			peopleList.get(p2).setWorksWith(newPerson);
			peopleList.put(p,newPerson);
			newPerson.setWorksWith(peopleList.get(p2));
		}
		else
		{
			Person newPerson = new Person(p);
			Person newPerson2 = new Person(p2);
			peopleList.put(p,newPerson);
			peopleList.put(p2,newPerson2);
			newPerson.setWorksWith(newPerson2);
			newPerson2.setWorksWith(newPerson);
		}
	}

	@Override
	public boolean e_works_with(String p, String p2) {
		if (peopleList.get(p).getWorksWith().contains(peopleList.get(p2)))
			return true;
		return false;
	}

	@Override
	public void a_assign_to(String p, String room) throws Exception {
		// If person already exists
		if (peopleList.containsKey(p)){
			// if person already has room
			if (peopleList.get(p).getRoom() != null)
			{
				peopleList.get(p).getRoom().removePerson(peopleList.get(p));
			}
			//if room already exists
			if (roomList.containsKey(room)){
				peopleList.get(p).setRoom(roomList.get(room));
				roomList.get(room).assignPerson(peopleList.get(p));
			}
			// if room doesnt exist
			else
			{
				Room newRoom = new Room(room);
				roomList.put(room, newRoom);
				newRoom.assignPerson(peopleList.get(p));
				peopleList.get(p).setRoom(newRoom);
			}
		}
		// If person doesnt exist
		else
		{
			Person newPerson = new Person(p);
			peopleList.put(p, newPerson);
			// If room exists
			if (roomList.containsKey(room))
			{
				newPerson.setRoom(roomList.get(room));
				roomList.get(room).assignPerson(newPerson);
			}
			// room doesnt exist
			else
			{
				Room newRoom = new Room(room);
				roomList.put(room, newRoom);
				newRoom.assignPerson(newPerson);
				newPerson.setRoom(newRoom);
			}
		}
	}

	@Override
	public boolean e_assign_to(String p, String room) {
		if (peopleList.get(p).getRoom().equals(roomList.get(room)))
			return true;
		else
			return false;
	}

	@Override
	public void a_room(String r) {
		if (!roomList.containsKey(r))
		{
			Room newRoom = new Room(r);
			roomList.put(r, newRoom);
		}

	}

	@Override
	public boolean e_room(String r) {
		return roomList.containsKey(r);
	}

	@Override
	public void a_close(String room, String room2) {
		Room newRoom1, newRoom2;
		// check if rooms exist, if not create
		if (!roomList.containsKey(room))
		{
			newRoom1 = new Room(room);
			roomList.put(room, newRoom1);
		}
		else
			newRoom1 = roomList.get(room);
		if (!roomList.containsKey(room2))
		{
			newRoom2 = new Room(room2);
			roomList.put(room2, newRoom2);
		}
		else
			newRoom2 = roomList.get(room2);
		// Assign closeness of rooms
		newRoom1.assignClose(newRoom2);
		newRoom2.assignClose(newRoom1);
	}

	@Override
	public boolean e_close(String room, String room2) {
		
		if (roomList.get(room).getCloseRooms().contains(roomList.get(room2)))
			return true;
		else
			return false;
	
	}

	@Override
	public void a_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		for (Pair<ParamType, Object> pair : set) {
			String room2 = (String) pair.getValue();
			a_close(room, room2);
		}

	}

	@Override
	public boolean e_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		for (Pair<ParamType, Object> pair : set)
		{
			String roomName = (String)pair.getValue();
			if (roomList.get(room).getCloseRooms().contains(roomList.get(roomName)))
				;
			else
				return false;
		}
		
		return true;
	}

	@Override
	public void a_large_room(String r) {
		if (!roomList.containsKey(r))
		{
			Room newRoom = new Room(r);
			roomList.put(r, newRoom);
		}
		roomList.get(r).setRoomSize("large");
	}

	@Override
	public boolean e_large_room(String r) {
		if (roomList.get(r).getRoomSize().equals("large"))
			return true;
		else
			return false;
	}

	@Override
	public void a_medium_room(String r) {
		if (!roomList.containsKey(r))
		{
			Room newRoom = new Room(r);
			roomList.put(r, newRoom);
		}
		roomList.get(r).setRoomSize("medium");
	}

	@Override
	public boolean e_medium_room(String r) {
		if (roomList.get(r).getRoomSize().equals("medium"))
			return true;
		else
			return false;
	}

	@Override
	public void a_small_room(String r) {
		if (!roomList.containsKey(r))
		{
			Room newRoom = new Room(r);
			roomList.put(r, newRoom);
		}
		roomList.get(r).setRoomSize("small");
	}

	@Override
	public boolean e_small_room(String r) {
		if (roomList.get(r).getRoomSize().equals("small"))
			return true;
		else
			return false;
	}

	@Override
	public void a_group(String g) {
		if(!groupList.containsKey(g)){
			Grp newGroup = new Grp(g);
			groupList.put(g,newGroup);
		}
	}

	@Override
	public boolean e_group(String g) {
		return groupList.containsKey(g);
	}

	@Override
	public void a_project(String p) {
		if(!projectList.containsKey(p)){
			Project newProject = new Project(p);
			projectList.put(p,newProject);
		}
	}

	@Override
	public boolean e_project(String p) {
		return projectList.containsKey(p);
	}

	@Override
	public void a_large_project(String prj) {
		
		if (!projectList.containsKey(prj))
		{
			Project newProject = new Project(prj);
			projectList.put(prj, newProject);
		}
		projectList.get(prj).setLarge(true);
	
	}

	@Override
	public boolean e_large_project(String prj) {
		return projectList.get(prj).getLarge();
	}
	
	public static void printPredicates()
	{
		for (Person psn : peopleList.values())
		{
			System.out.println("person("+psn.getName()+")");
			if (psn.getHacker())
				System.out.println("hacker("+psn.getName()+")");
			if (psn.getManager())
				System.out.println("manager("+psn.getName()+")");
			if (psn.getResearcher())
				System.out.println("researcher("+psn.getName()+")");
			if (psn.getSecretary())
				System.out.println("secretary("+psn.getName()+")");
			if (psn.getSmoker())
				System.out.println("smoker("+psn.getName()+")");
			
			for (Project prj : psn.getProjectsList())
				System.out.println("project("+psn.getName()+", "+prj.getName()+")");
			
			for (Project prj : psn.getProjectHead())
				System.out.println("heads-project("+psn.getName()+", "+prj.getName()+")");
			
			for (Grp group : psn.getGroupsList())
				System.out.println("group("+psn.getName()+", "+group.getName()+")");
			
			for (Grp group : psn.getGroupHeadList())
				System.out.println("heads-group("+psn.getName()+", "+group.getName()+")");
			
			if (psn.getRoom()!= null)
				System.out.println("assigned-to("+psn.getName()+", "+psn.getRoom().getName()+")");
			
			for (Person psn2 : psn.getWorksWith())
				System.out.println("works-with("+psn.getName()+", "+psn2.getName()+")");
		}
		
		for (Room room : roomList.values())
		{
			System.out.println("room("+room.getName()+")");
			System.out.println(room.getRoomSize()+"-room("+room.getName()+")");
			
			for (Room room2 : room.getCloseRooms())
				System.out.println("close("+room.getName()+", "+room2.getName()+")");
			
		}
		
		for (Grp group : groupList.values())
			System.out.println("group("+group.getName()+")");
		
		for (Project prj : projectList.values())
		{
			System.out.println("project("+prj.getName()+")");
			if (prj.getLarge())
				System.out.println("large-project("+prj.getName()+")");
		}
	}
}