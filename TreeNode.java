import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

public class TreeNode {

	public Assignment assignment;
	public TreeNode child;
	public ArrayList<Assignment> possibleAssigns;
	
	public static Stack<Assignment> solution = new Stack<Assignment>();
	public static Stack<Assignment> bestsolution = new Stack<Assignment>();
	public static int bestscore;
	public static Stack<Person> people;
	public static HashSet<Room> rooms;
	public static boolean terminated;
	public TreeNode(Assignment assign)
	{
		this.assignment = assign;
		this.child = null;
		this.possibleAssigns = new ArrayList<Assignment>();
	}
	
	public TreeNode()
	{
		this.assignment = new Assignment(null, null, 0);
		TreeNode.people = new Stack<Person>();
		TreeNode.rooms = new HashSet<Room>(OurEnvironment.roomList.values());
		TreeNode.bestscore = Integer.MIN_VALUE;
		TreeNode.terminated = false;
		this.possibleAssigns = new ArrayList<Assignment>();
	}
	
	@SuppressWarnings("unchecked")
	public void processNode()
	{ 
		if (people.isEmpty())
		{
			if (TreeNode.bestscore < this.assignment.score)
			{
				TreeNode.bestsolution = (Stack<Assignment>) TreeNode.solution.clone();
				TreeNode.bestscore = this.assignment.score;
				System.out.println("New best solution: " + TreeNode.bestscore);
				if (TreeNode.bestscore == 0)
					TreeNode.terminated = true;
			}
		}
		else
		{
			Person current = people.peek();
			createAssignments(current);
			for (Assignment assign : this.possibleAssigns)
			{
				TreeNode child = new TreeNode(assign);
				TreeNode.pushAssignment(assign);
				if (!terminated)
				{
					child.processNode();
				}
				
				TreeNode.popAssignment();
			}
		}
	}

	public void createAssignments(Person assignedPerson)
	{
		if (assignedPerson.getRoom() == null)
		{
			for (Room room : TreeNode.rooms)
			{
				if ((assignedPerson.getManager() || assignedPerson.getProjectHead().size() > 0 || assignedPerson.getGroupHeadList().size() > 0) && room.getAssigned().size() > 0)
					;
				else
				{
					Assignment current = new Assignment(assignedPerson,room, this.assignment.score);
					evalAssignment(current);
					if (current.score > TreeNode.bestscore)
						this.possibleAssigns.add(current);
				}
			}
			Collections.sort(this.possibleAssigns, new Comparator<Assignment>() {
				@Override 
				public int compare(Assignment a1, Assignment a2) {
					return a2.score - a1.score; // Descending
				}

			});
		}
		else
		{
			this.possibleAssigns.add(new Assignment(assignedPerson,assignedPerson.getRoom(), this.assignment.score));
		}
	}
	
	public void evalAssignment(Assignment assign)
	{
		if (assign.person.getGroupHeadList().size()>0)
		{
			Constraints.constraint1(assign);
			Constraints.constraint3grphead(assign);
			Constraints.constraint6grphead(assign);
			Constraints.constraint10grphead(assign);
		}
		if (assign.person.getSecretary())
		{
			Constraints.constraint3sec(assign);
			Constraints.constraint4(assign);
			Constraints.constraint5sec(assign);
			Constraints.constraint9sec(assign);
		}
		if (assign.person.getManager())
		{
			Constraints.constraint5manager(assign);
			Constraints.constraint6manager(assign);
		}
		if (assign.person.getProjectHead().size()>0)
		{
			Constraints.constraint9largeprj(assign);
			Constraints.constraint10largeprj(assign);
		}
		
		Constraints.constraint2(assign);
		Constraints.constraint7(assign);
		Constraints.constraint8(assign);
		Constraints.constraint11(assign);
		Constraints.constraint12(assign);
		Constraints.constraint13(assign);
		Constraints.constraint14(assign);
		Constraints.constraint15(assign);
		Constraints.constraint16(assign);
		
		
	}
	
	public static void pushAssignment(Assignment assign)
	{
		TreeNode.solution.push(assign);
		assign.person.setRoom(assign.room);
		assign.room.assignPerson(assign.person);
		if (assign.room.isFull())
			TreeNode.rooms.remove(assign.room);
		TreeNode.people.pop();
	}
	
	public static void popAssignment()
	{
		Assignment temp = TreeNode.solution.pop();
		TreeNode.people.push(temp.person);
		TreeNode.rooms.add(temp.room);
		temp.person.setRoom(null);
		temp.room.removePerson(temp.person);
		
	}
	
}
