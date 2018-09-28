package org.jerraloid.baahroulette.model;

public enum HoroscopeEnum {
	
	AQUARIUS("aquarius"),
	PISCES("pisces"),
	ARIES("aries"),
	TAURUS("taurus"),
	GEMINI("gemini"),
	CANCER("cancer"),
	LEO("leo"),
	VIRGO("virgo"),
	LIBRA("libra"),
	SCORPIO("scorpio"),
	SAGITTARIUS("sagittarius"),
	CAPRICORN("capricorn");
	
	private String horoscopeName;
	
	private HoroscopeEnum(String horoscopeName) {
		this.horoscopeName = horoscopeName;
	}
	
	public static HoroscopeEnum getHoroscope(String horoscope) {
		switch (horoscope) {
			case "aquarius": return AQUARIUS;
			case "taurus": return TAURUS;
			case "gemini": return GEMINI;
			case "cancer": return CANCER;
			case "leo": return LEO;
			case "virgo": return VIRGO;
			case "libra": return LIBRA;
			case "scorpio": return SCORPIO;
			case "sagittarius": return SAGITTARIUS;
			case "capricorn": return CAPRICORN;
			default: return null;
		}
	}
	
	public static String getName(HoroscopeEnum horoscope) {
		switch (horoscope) {
			case AQUARIUS: return AQUARIUS.horoscopeName;
			case TAURUS: return TAURUS.horoscopeName;
			case GEMINI: return GEMINI.horoscopeName;
			case CANCER: return CANCER.horoscopeName;
			case LEO: return LEO.horoscopeName;
			case VIRGO: return VIRGO.horoscopeName;
			case LIBRA: return LIBRA.horoscopeName;
			case SCORPIO: return SCORPIO.horoscopeName;
			case SAGITTARIUS: return SAGITTARIUS.horoscopeName;
			case CAPRICORN: return CAPRICORN.horoscopeName;
			default: return null;
		}
	}
}
