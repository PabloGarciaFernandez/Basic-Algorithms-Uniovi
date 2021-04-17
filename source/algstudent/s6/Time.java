package algstudent.s6;

public class Time {
	private int minutes;
	private int seconds;
	
	public Time(int minutes, int seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}
	
	public int getTotSec() {
		return (60*minutes)+seconds;
	}
}
