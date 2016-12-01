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

public class MainSisyphus extends SisyphusI {
 		
	public MainSisyphus(String[] args) {
		super(args);
	}
	
	@Override
	protected Environment getEnvironment() {
		return OurEnvironment.get();
	}
	
	@Override
	protected void doSearch(Environment env, long timeLimit) {
		;
	}
	
	@Override
	protected void printResults() {
		;
	}
	
	
	
	public static void main(String[] args) {
		MainSisyphus ms = new MainSisyphus(args);
		Timer myTimer = new Timer(true);
		long time = (long) (0.9f*Integer.parseInt(args[1]));
		myTimer.schedule(new TimeLimit(),time); // TODO: change time to arg value
		doOrTree();
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
	
	private static void doOrTree()
	{
		// Get people and room lists
		HashSet<Person> peopleList = new HashSet<Person>(OurEnvironment.peopleList.values());
		Stack<Person> temp = new Stack<Person>();
		for (Person preassigned : OurEnvironment.preAssigned)
		{
			temp.push(preassigned);
			peopleList.remove(preassigned);
		}
		// Create ordered people stack by group
		ArrayList<Grp> allgroups = new ArrayList<Grp>(OurEnvironment.groupList.values());
		
		Collections.sort(allgroups, new Comparator<Grp>() {
			@Override 
			public int compare(Grp g1, Grp g2) {
				return g2.getGroupMembers().size() - g1.getGroupMembers().size(); // Descending
			}
		});
		
		for (Grp group : allgroups)
		{
			for (Person grphead : group.getGroupHeads())
			{
				if (peopleList.contains(grphead))
				{
					temp.push(grphead);
					peopleList.remove(grphead);
				}
				
			}
			for (Person manager : group.getGroupManagers())
			{
				if (peopleList.contains(manager))
				{
					temp.push(manager);
					peopleList.remove(manager);
				}
			}
			for (Person secretary : group.getGroupSecretaries())
			{
				if (peopleList.contains(secretary))
				{
					temp.push(secretary);
					peopleList.remove(secretary);
				}
			}
			for (Person prjhead : group.getLargeProjectHeads())
			{
				if (peopleList.contains(prjhead))
				{
					temp.push(prjhead);
					peopleList.remove(prjhead);
				}
			}
			for (Person leftover : group.getGroupMembers())
			{
				if (peopleList.contains(leftover))
				{
					temp.push(leftover);
					peopleList.remove(leftover);
				}
			}
		}
		
		for (Person staleleftover : peopleList)
		{
			temp.push(staleleftover);
			peopleList.remove(staleleftover);
		}
		
		if (!peopleList.isEmpty())
			System.out.println("Aint got time fo yo scrubs");
		
		// Create TreeNode root
		TreeNode root = new TreeNode();
		
		while (!temp.isEmpty())
		{
			TreeNode.people.push(temp.pop());
		}
		root.processNode();
		
		
	}
	
	private static void printSolution(PrintWriter pw)
	{
		for (Assignment assign : TreeNode.bestsolution)
		{
			pw.println("assign-to("+assign.person.getName()+", "+assign.room.getName()+")");
		}
	}
	
}
