import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	
	public static void main(String[] args) {
		MainSisyphus ms = new MainSisyphus(args);
		
		Timer myTimer = new Timer(true);
		myTimer.schedule(new TimeLimit(),1000); // TODO: change time to arg value
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(args[0]+".out");
			OurEnvironment.printPredicates(pw);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private static void doOrTree(OurEnvironment ourEnv)
	{
		// Get people and room lists
		// Create ordered people stack by group
		// Create room hashset
		// Create TreeNode root
		// processNode(root)
		
		//
		
		
	}
	
}
