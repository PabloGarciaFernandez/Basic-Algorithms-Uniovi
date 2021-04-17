package algstudent.s6;

public class Song {
	private String identifier;
	private Time duration;
	private int score;
	
	public Song(String identifier, int minutes, int seconds, int score) {
		this.identifier = identifier;
		this.duration = new Time(minutes,seconds);
		this.score = score;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Time getDuration() {
		return duration;
	}

	public int getScore() {
		return score;
	}
}
