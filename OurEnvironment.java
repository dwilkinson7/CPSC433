import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import cpsc433.Environment;
import cpsc433.Pair;
import cpsc433.Predicate.ParamType;
import cpsc433.SisyphusPredicates;

public class OurEnvironment extends Environment implements SisyphusPredicates {

	private static OurEnvironment instance=null;
	private static HashMap<String,Person> peopleList = new HashMap<String,Person>();
	private static HashMap<String,Grp> groupList = new HashMap<String,Grp>();
	private static HashMap<String,Project> projectList = new HashMap<String,Project>();
	
	private static ArrayList<String> roomNameList = new ArrayList<String>();
	private static ArrayList<Room> roomList = new ArrayList<Room>();
	
	
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
		for (int i = 0; i<peopleList.size(); i++)
		{
			if (peopleList.get(i).getName().equals(p))
			{
				return peopleList.get(i).getWorksWith().equals(p2s);
			}
		}
		return false;
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
		if(peopleNameList.contains(p)){
			for(int i = 0; i < peopleList.size(); i++){
				if(peopleList.get(i).getName().equals(p)){
					for (int j = 0; j < roomList.size(); j++){
						if(roomList.get(j).getName().equals(room)){
							roomList.get(j).assign(peopleList.get(i));
							break;
						}
					}
				}
			}
		} else {
			Person newPerson = new Person(p);
			for(int i = 0; i < peopleList.size(); i++){
				if(roomList.get(i).getName().equals(room)){
					roomList.get(i).assign(newPerson);
				}
			}
			peopleList.add(newPerson);
			peopleNameList.add(p);
		}
		if (!roomNameList.contains(room))
		{
			Room newRoom = new Room(room);
			roomList.add(newRoom);
			roomNameList.add(room);
			for(int i = 0; i < peopleList.size(); i++){
				if(peopleList.get(i).getName().equals(p)){
					newRoom.assign(peopleList.get(i));
				}
			}
		}
	}

	@Override
	public boolean e_assign_to(String p, String room) {
		if(peopleNameList.contains(p) && roomNameList.contains(room)){
			for(int i = 0; i < roomList.size(); i++){
				if(roomList.get(i).getName().equals(room)){
							return roomList.get(i).isAssigned(p);
						}
					}
				}
			
		return false;
	}

	@Override
	public void a_room(String r) {
		if (!roomNameList.contains(r)){
			Room newRoom = new Room(r);
			roomList.add(newRoom);
			roomNameList.add(r);
		}

	}

	@Override
	public boolean e_room(String r) {
		return roomNameList.contains(r);
	}

	@Override
	public void a_close(String room, String room2) {

		if (roomNameList.contains(room)){
			for (int i = 0; i < roomList.size(); i++){
				if (roomList.get(i).getName().equals(room)){
					roomList.get(i).setClose(room2);
					break;
				}
			}
		}
		//If the first room doesn't exists
		else{
			Room newRoom = new Room(room);
			newRoom.setClose(room2);
			roomList.add(newRoom);
			roomNameList.add(room);
		}
		//Check to see if the second room exists.
		if (roomNameList.contains(room2)){
			for (int i = 0; i < roomList.size(); i++){
				if (roomList.get(i).getName().equals(room2)){
					roomList.get(i).setClose(room);
					break;
				}
			}
		}
		else{
			Room newRoom = new Room(room2);
			newRoom.setClose(room);
			roomList.add(newRoom);
			roomNameList.add(room2);
		}

	}

	@Override
	public boolean e_close(String room, String room2) {
		if (roomNameList.contains(room)){
			for (int i = 0; i < roomList.size(); i++){
				if (roomList.get(i).getName().equals(room)){
					return roomList.get(i).getClose().contains(room2);
				}
			}
		}
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
		for (Pair<ParamType, Object> pair : set) {
			String room2 = (String) pair.getValue();
			if (e_close(room, room2) == true)
				return true;
		}
		return false;
	}

	@Override
	public void a_large_room(String r) {
		if (roomNameList.contains(r)){
			for (int i = 0; i < roomList.size(); i++){
				if (roomList.get(i).getName().equals(r)){
					roomList.get(i).setSize("large");
					break;
				}
			}
		}
		else{
			Room newRoom = new Room(r);
			newRoom.setSize("large");
			roomList.add(newRoom);
			roomNameList.add(r);
		}

	}

	@Override
	public boolean e_large_room(String r) {
		for (int i = 0; i<roomList.size(); i++){
			if (roomList.get(i).getName().equals(r)){
				return roomList.get(i).getSize().equals("large");
			}
		}
		return false;
	}

	@Override
	public void a_medium_room(String r) {
		if (roomNameList.contains(r)){
			for (int i = 0; i < roomList.size(); i++){
				if (roomList.get(i).getName().equals(r)){
					roomList.get(i).setSize("medium");
					break;
				}
			}
		}
		else{
			Room newRoom = new Room(r);
			newRoom.setSize("medium");
			roomList.add(newRoom);
			roomNameList.add(r);
		}

	}

	@Override
	public boolean e_medium_room(String r) {
		for (int i = 0; i<roomList.size(); i++){
			if (roomList.get(i).getName().equals(r)){
				return roomList.get(i).getSize().equals("medium");
			}
		}
		return false;
	}

	@Override
	public void a_small_room(String r) {
		if (roomNameList.contains(r)){
			for (int i = 0; i < roomList.size(); i++){
				if (roomList.get(i).getName().equals(r)){
					roomList.get(i).setSize("small");
					break;
				}
			}
		}
		else{
			Room newRoom = new Room(r);
			newRoom.setSize("small");
			roomList.add(newRoom);
			roomNameList.add(r);
		}

	}

	@Override
	public boolean e_small_room(String r) {
		for (int i = 0; i<roomList.size(); i++){
			if (roomList.get(i).getName().equals(r)){
				return roomList.get(i).getSize().equals("small");
			}
		}
		return false;
	}

	@Override
	public void a_group(String g) {
		if(!groupNameList.contains(g)){
			Grp newGroup = new Grp(g);
			groupList.add(newGroup);
			groupNameList.add(g);
		}
	}

	@Override
	public boolean e_group(String g) {
		return groupNameList.contains(g);
	}

	@Override
	public void a_project(String p) {
		if(!projectNameList.contains(p)){
			Project newProject = new Project(p);
			projectList.add(newProject);
			projectNameList.add(p);
		}
	}

	@Override
	public boolean e_project(String p) {
		return projectNameList.contains(p);
	}

	@Override
	public void a_large_project(String prj) {
		if (projectNameList.contains(prj)){
			for (int i = 0; i < projectList.size(); i++){
				if (projectList.get(i).getName().equals(prj)){
					projectList.get(i).setLargeProject(true);
					break;
				}
			}
		}
		else{
			Project newProject= new Project(prj);
			newProject.setLargeProject(true);
			projectList.add(newProject);
			projectNameList.add(prj);
		}

	}

	@Override
	public boolean e_large_project(String prj) {
		for (int i = 0; i < projectList.size(); i++){
			if (projectList.get(i).getName().equals(prj))
				return projectList.get(i).getLargeProject();
		}
		return false;
	}
}