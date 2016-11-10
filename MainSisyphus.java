import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
		new MainSisyphus(args);
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
	
}
