import java.util.TimerTask;
/**
 * 
 * TimeLimit class is a TimerTask subclass that simply sets a "terminated" switch in TreeNode
 * to <CODE>true</CODE> when run.
 *
 */
public class TimeLimit extends TimerTask {

	private MainSisyphus mainSis;
	
	public TimeLimit() {
	}
	
	@Override
	public void run() {
			TreeNode.terminated = true;
	}

}
