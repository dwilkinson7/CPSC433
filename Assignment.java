
/**
 * Assignment class that represents the current pair of person, room assignment
 *  with the corresponding score evaluation
 */

public class Assignment {
	
	public int score;
	public Person person;
	public Room room;
	
	/**
	 * Assignment Constructor
	 * @param person - Person object for the current person we are assigning into the room
	 * @param room - Room object
	 * @param prevscore - integer that represents the score before the current assignment (cumulative score)
	 */
	public Assignment(Person person, Room room, int prevscore) {
		this.person = person;
		this.room = room;
		this.score = prevscore;
	}
	
	/**
	 * Method to set the score for the assignment
	 * @param score - integer, score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
