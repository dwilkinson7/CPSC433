import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

/**
 * TreeNode class to represent each node in the Or-Tree. Each TreeNode holds 
 * the current assignment it represents, a single TreeNode child (that is changed throughout
 * the search) and a list of other possible assignments to iterate through.
 */

public class TreeNode {

	// Member variables
	public Assignment assignment;
	public TreeNode child;
	public ArrayList<Assignment> possibleAssigns;
	
	public static Stack<Assignment> solution = new Stack<Assignment>();
	public static Stack<Assignment> bestsolution = new Stack<Assignment>();
	public static int bestscore;
	public static Stack<Person> people;
	public static HashSet<Room> rooms;
	public static boolean terminated;
	
	
	/**
	 * TreeNode Constructor used for children
	 * @param assign - Assignment of this child
	 */
	
	public TreeNode(Assignment assign)
	{
		this.assignment = assign;
		this.child = null;
		this.possibleAssigns = new ArrayList<Assignment>();
	}
	
	/**
	 * TreeNode Constructor for the root node
	 */
	
	public TreeNode()
	{
		this.assignment = new Assignment(null, null, 0);
		TreeNode.people = new Stack<Person>();
		TreeNode.rooms = new HashSet<Room>(OurEnvironment.roomList.values());
		TreeNode.bestscore = Integer.MIN_VALUE;
		TreeNode.terminated = false;
		this.possibleAssigns = new ArrayList<Assignment>();
	}
	
	/**
	 * Processes this node. If no more people exist to assign, update best score and
	 * best solution if necessary. If this solution has penalty of 0, end tree traversal. Otherwise, create next possible assignments, sorting them 
	 * by score and size of room clusters. Choose best possible assignment, then create child TreeNode for it. Push this assignment onto solution 
	 * stack, and process this new child node recursively.
	 */
	
	@SuppressWarnings("unchecked")
	public void processNode()
	{
		// If there are no more people to assign
		if (people.isEmpty())
		{
			// If the current score is better than the stored best score
			if (TreeNode.bestscore < this.assignment.score)
			{
				// Update new best score and best solution
				TreeNode.bestsolution = (Stack<Assignment>) TreeNode.solution.clone();
				TreeNode.bestscore = this.assignment.score;
				System.out.println("New best solution: " + TreeNode.bestscore);
				// If this new best score is 0, terminate program as a best solution is found.
				if (TreeNode.bestscore == 0)
					TreeNode.terminated = true;
			}
		}
		// If more people to assign still
		else
		{
			// Check next person in list and create assignments for him/her
			Person current = people.peek();
			createAssignments(current);
			// For each of these assignments, which are sorted from best to worst
			for (Assignment assign : this.possibleAssigns)
			{
				// Check is program is terminating
				if (!terminated)
				{
					// Take next child and create TreeNode
					TreeNode child = new TreeNode(assign);
					// Push assignment on solution stack, and process node
					TreeNode.pushAssignment(assign);
					child.processNode();
					// Backtrack scenario, pop assignment off solution stack
					TreeNode.popAssignment();
				}
				else
					return;
				
			}
		}
	}

	/**
	 * Creates all possible assignments for the given person and populate the TreeNode possible assignments list.
	 * The list will be sorted by best score. Furthermore, if the person is a manager or head, the top elements of
	 * the list of equal score are sorted by larger clusters of rooms.
	 * If there is already a room assigned to person, then it must be a pre-assignment, push it onto solution stack.
	 * Does not include assignments who's cumulative scores are worse than the current best score.
	 * @param assignedPerson - Person to assign to the room assignments
	 */
	
	public void createAssignments(Person assignedPerson)
	{
		// If person is not assigned yet
		if (assignedPerson.getRoom() == null)
		{
			// For all remaining available rooms
			for (Room room : TreeNode.rooms)
			{
				// Check if program is terminating
				if (terminated)
					return;
				// Check if room has space for a manager/head, only if the person is a manager/head
				if ((assignedPerson.getManager() || assignedPerson.getProjectHead().size() > 0 || assignedPerson.getGroupHeadList().size() > 0) && room.getAssigned().size() > 0)
					;
				// Create assignment for given person and current room, get score, and add to possible
				// assignments if it's not already worse than the best full solution
				else
				{
					Assignment current = new Assignment(assignedPerson,room, this.assignment.score);
					evalAssignment(current);
					if (current.score > TreeNode.bestscore)
						this.possibleAssigns.add(current);
				}
			}
			// Check for program termination
			if (terminated)
				return;
			// Sort assignments based on score, descending
			Collections.sort(this.possibleAssigns, new Comparator<Assignment>() {
				@Override 
				public int compare(Assignment a1, Assignment a2) {
					return a2.score - a1.score; // Descending
				}

			});
			
			// If person is a manager or head
			if ((assignedPerson.getManager() || assignedPerson.getGroupHeadList().size()>0 || assignedPerson.getProjectHead().size()>0) && this.possibleAssigns.size()>1)
			{
				int index = 0;
				// Find index of last assignment that has equal score to best assignment in list 
				while (this.possibleAssigns.size() > index+1 && this.possibleAssigns.get(index).score == this.possibleAssigns.get(index+1).score)
				{
					index++;
				}
				// Create sublist which is "best assignments of same score". Sort them by the amount of close rooms.
				// The more close rooms the better
				ArrayList<Assignment> equalRoomAssigns = new ArrayList<Assignment>(this.possibleAssigns.subList(0, index+1));
				Collections.sort(equalRoomAssigns, new Comparator<Assignment>(){
					@Override 
					public int compare(Assignment a1, Assignment a2) {
						return a2.room.getCloseRooms().size() - a1.room.getCloseRooms().size(); // Descending
					}
				});
				// Combine this sorted sublist with the other room assignments.
				Collections.copy(this.possibleAssigns,equalRoomAssigns);
			}
		} // If the person already has a room assignment, they must be pre-assigned
		else
		{
			// Add this pre-assignment as the only possible assignment.
			this.possibleAssigns.add(new Assignment(assignedPerson,assignedPerson.getRoom(), this.assignment.score));
		}
	}
	
	/**
	 * Evaluate the given assignment based on the constraints given in class. Only checks constraints 
	 * that apply to the type of person in the assignment. Any deduction that depends on a potential 
	 * future assignment is ignored until a penalty is assured.
	 * @param assign - Assignment to evaluate
	 */
	public void evalAssignment(Assignment assign)
	{
		// Constraints applying to group heads
		if (assign.person.getGroupHeadList().size()>0)
		{
			Constraints.constraint1(assign);
			Constraints.constraint3grphead(assign);
			Constraints.constraint6grphead(assign);
			Constraints.constraint10grphead(assign);
		}
		// Constraints applying to secretaries
		if (assign.person.getSecretary())
		{
			Constraints.constraint3sec(assign);
			Constraints.constraint4(assign);
			Constraints.constraint5sec(assign);
			Constraints.constraint9sec(assign);
		}
		// Constraints applying to managers
		if (assign.person.getManager())
		{
			Constraints.constraint5manager(assign);
			Constraints.constraint6manager(assign);
		}
		// Constraints applying to project heads
		if (assign.person.getProjectHead().size()>0)
		{
			Constraints.constraint9largeprj(assign);
			Constraints.constraint10largeprj(assign);
		}
		// All rest of constraints applying to all
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
	
	/**
	 * Push the given assignment onto solution stack. Assign the person and room in the room and person objects,
	 * respectively. The person is removed from the available people stack, and the room is removed from available
	 * room hashset if it is full.
	 * @param assign - Assignment to add to solution stack
	 */
	
	public static void pushAssignment(Assignment assign)
	{
		TreeNode.solution.push(assign);
		assign.person.setRoom(assign.room);
		assign.room.assignPerson(assign.person);
		if (assign.room.isFull())
			TreeNode.rooms.remove(assign.room);
		TreeNode.people.pop();
	}
	
	/**
	 * Pop the most recent assignment off the solution stack. Remove assignment from the person and room
	 * objects. Push the person back on people stack, and add the room back to rooms hashset.
	 */
	
	public static void popAssignment()
	{
		Assignment temp = TreeNode.solution.pop();
		TreeNode.people.push(temp.person);
		TreeNode.rooms.add(temp.room);
		temp.person.setRoom(null);
		temp.room.removePerson(temp.person);
		
	}
	
}
