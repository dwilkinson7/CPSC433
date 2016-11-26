
public class Assignment {

	
	public int score;
	public Person person;
	public Room room;
	
	public Assignment(Person person, Room room) {
		this.person = person;
		this.room = room;
		this.score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
