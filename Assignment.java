
public class Assignment {

	
	public int score;
	public Person person;
	public Room room;
	
	public Assignment(Person person, Room room, int prevscore) {
		this.person = person;
		this.room = room;
		this.score = prevscore;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
