package org.jerraloid.baahroulette.service;

import org.jerraloid.baahroulette.model.Horoscope;
import org.jerraloid.baahroulette.model.HoroscopeEnum;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.*;

public class HoroscopeService {

	private static final String API_URL = "https://aztro.sameerkumar.website/";
	private static Client client = ClientBuilder.newClient();
	
	/**
	 * Gets the horoscope of the day
	 * 
	 * @param horoscopeName Name of your horoscope
	 * @return Gives horoscope object with quotes and such
	 */
	public static Horoscope getHoroscope(String horoscopeName) {
		HoroscopeEnum horoscopeEnum = null;
		
		//check input
		try {
			horoscopeEnum = HoroscopeEnum.valueOf(horoscopeName.toUpperCase());
		} catch(IllegalArgumentException ex) {
			return null;
		}

		//Execute
		Horoscope horoscope = client
				.target(API_URL)
				.queryParam("sign", horoscopeEnum.name().toLowerCase())
				.queryParam("day", "today")
				.request(MediaType.APPLICATION_JSON)
				.post(null, Horoscope.class);

		horoscope.setName(horoscopeName);

		return horoscope;
	}
}
