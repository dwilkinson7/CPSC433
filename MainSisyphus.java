import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;
import java.util.Timer;

import cpsc433.Environment;
import cpsc433.SisyphusI;

/**
 * MainSisyphus class, which is subclass of SisyphusI, so we can easily import any updated jar
 * provided by CPSC 433 for the assignment. Main class which starts timer, runs predicate parser,
 * conducts Or-Tree search, then prints out results.
 *
 */


public class MainSisyphus extends SisyphusI {
 	
	/**
	 * MainSisyphus Constructor simply calls SisyphusI constructor
	 * @param args - Command-line Arguments
	 */
	public MainSisyphus(String[] args) {
		super(args);
	}
	
	/**
	 * Override SisyphusI getEnvironment to return instance of OurEnvironment,
	 * instead of Environment
	 */
	@Override
	protected Environment getEnvironment() {
		return OurEnvironment.get();
	}
	
	/**
	 * Override doSearch to stop it from printing "not implemented"
	 */
	@Override
	protected void doSearch(Environment env, long timeLimit) {
		;
	}
	
	/**
	 * Override printResults to stop it from printing "not implemented"
	 */
	@Override
	protected void printResults() {
		;
	}
	
	
	/**
	 * Main function, which starts timer, runs predicate parser, conducts Or-Tree search,
	 * then prints out results.
	 * @param args - Command line arguments
	 */
	public static void main(String[] args) {
		// Start timer for 95% of given time to ensure output
		Timer myTimer = new Timer(true);
		long time = (long) (0.95f*Integer.parseInt(args[1]));
		myTimer.schedule(new TimeLimit(),time);
		// Start parser
		new MainSisyphus(args);
		// Start Or-Tree search
		doOrTree();
		// Print output
		PrintWriter pw;
		try {
			pw = new PrintWriter(args[0]+".out");
			printSolution(pw);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * doOrTree conducts the Or-Tree search algorithm. Assign each person to a room. Initially assigns pre-assigned
	 * people, then group heads, managers, secretaries and large projects heads (in that order) for each group, starting
	 * with the largest groups to the smallest groups. Then assign remaining project heads, then remaining others.
	 */
	private static void doOrTree()
	{
		// Get people stack and room set
		HashSet<Person> peopleList = new HashSet<Person>(OurEnvironment.peopleList.values());
		Stack<Person> temp = new Stack<Person>();
		// Push pre-assigned people first
		for (Person preassigned : OurEnvironment.preAssigned)
		{
			temp.push(preassigned);
			peopleList.remove(preassigned);
		}
		// Order groups by size
		ArrayList<Grp> allgroups = new ArrayList<Grp>(OurEnvironment.groupList.values());
		
		Collections.sort(allgroups, new Comparator<Grp>() {
			@Override 
			public int compare(Grp g1, Grp g2) {
				return g2.getGroupMembers().size() - g1.getGroupMembers().size(); // Descending
			}
		});
		
		// Start with largest groups to smallest groups
		for (Grp group : allgroups)
		{
			// Push group heads
			for (Person grphead : group.getGroupHeads())
			{
				if (peopleList.contains(grphead))
				{
					temp.push(grphead);
					peopleList.remove(grphead);
				}
			// Push managers
			}
			for (Person manager : group.getGroupManagers())
			{
				if (peopleList.contains(manager))
				{
					temp.push(manager);
					peopleList.remove(manager);
				}
			}
			// Push secretaries
			for (Person secretary : group.getGroupSecretaries())
			{
				if (peopleList.contains(secretary))
				{
					temp.push(secretary);
					peopleList.remove(secretary);
				}
			}
			// Push large project heads
			for (Person prjhead : group.getLargeProjectHeads())
			{
				if (peopleList.contains(prjhead))
				{
					temp.push(prjhead);
					peopleList.remove(prjhead);
					OurEnvironment.projectHeadList.remove(prjhead);
				}
			}
		}
		// Push remaining project heads
		for (Person stalemanager : OurEnvironment.projectHeadList)
		{
			temp.push(stalemanager);
			peopleList.remove(stalemanager);
		}
		// Push remaining people
		for (Person staleleftover : peopleList)
		{
			temp.push(staleleftover);
		}
		
		// Create TreeNode root
		TreeNode root = new TreeNode();
		
		// Flip stack into TreeNode people stack (to give proper order)
		while (!temp.isEmpty())
		{
			TreeNode.people.push(temp.pop());	
		}
		// Conduct Or-Tree search (processNode is recursive)
		root.processNode();
	}
	
	/**
	 * printSolution takes all assignments in the best solution and prints to output file
	 * @param pw - PrintWriter used to write to file
	 */
	
	private static void printSolution(PrintWriter pw)
	{
		for (Assignment assign : TreeNode.bestsolution)
		{
			pw.println("assign-to("+assign.person.getName()+", "+assign.room.getName()+")");
		}
	}
	
}
