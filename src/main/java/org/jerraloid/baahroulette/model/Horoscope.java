package org.jerraloid.baahroulette.model;

/**
 * Horoscope class, gives quotes of the day!
 * 
 * @author jblaauw
 *
 */
public class Horoscope {
	
	private String name;
	private String description;
	private String mood;
	private String color;
	private String luckyTime;
	private int luckyNumber;
	private String compatibility;
	
	/**
	 * Constructor
	 * 
	 * @param name Name of the horoscope
	 * @param description Quote of the day
	 * @param mood Your mood/emotion
	 * @param color Color of the day
	 * @param luckyTime Your lucky time!
	 * @param luckyNumber Your lucky number
	 * @param compatibility Best friend Kappa
	 */
	public Horoscope(String name, String description, String mood, String color, String luckyTime, int luckyNumber, String compatibility) {
		this.name = name;
		this.description = description;
		this.mood = mood;
		this.color = color;
		this.luckyTime = luckyTime;
		this.luckyNumber = luckyNumber;
		this.compatibility = compatibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getLuckyTime() {
		return luckyTime;
	}
	
	public void setLuckyTime(String luckyTime) {
		this.luckyTime = luckyTime;
	}

	public int getLuckyNumber() {
		return luckyNumber;
	}

	public void setLuckyNumber(int luckyNumber) {
		this.luckyNumber = luckyNumber;
	}

	public String getCompatibility() {
		return compatibility;
	}

	public void setCompatibility(String compatibility) {
		this.compatibility = compatibility;
	}
	
	@Override
	public String toString() {
		return "name=" + name +
				", description=" + description +
				", mood=" + mood +
				", color=" + color +
				", luckytime" + luckyTime +
				", luckynumber=" + luckyNumber + 
				", compatibility=" + compatibility;
	}
}
