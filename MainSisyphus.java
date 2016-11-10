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
		OurEnvironment.printPredicates();
	}
	
}
