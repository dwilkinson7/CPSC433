import java.util.TimerTask;

public class TimeLimit extends TimerTask {

	private MainSisyphus mainSis;
	
	public TimeLimit() {
	}
	
	@Override
	public void run() {
			TreeNode.terminated = true;
	}

}
