package unboredify;
import java.util.Random;

public class RandSchedule {

	static String[] dayEvents= {"shopping","learn","public+park", "theater", "gym"};
	static String[] nightEvents= {"pub","night+club", "bar"};
	static String[] eatEvents= {"food","restaurant"};
	/**
	 * @param args
	 */
	private int rand(int n) {
		Random randNum=new Random();
		return randNum.nextInt(n);
	}
	//morning,lunch,afternoon,dinner,night
	//select one in (reading, movie, park) for (morning or afternoon)
	//select one in (pub,night club) for night
	public String getDayEvent() {
		return dayEvents[rand(dayEvents.length)];
	}
	public String getNightEvent() {
		return nightEvents[rand(nightEvents.length)];
	}
	public String getEatEvent() {
		return nightEvents[rand(eatEvents.length)];
	}
	//method for select specific stuff among n stuff
	public int getRand(int n) {
		return rand(n);
	}
	
	
	
}
