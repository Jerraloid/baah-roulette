package org.jerraloid.baahroulette.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.jerraloid.baahroulette.model.Horoscope;
import org.jerraloid.baahroulette.model.HoroscopeEnum;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HoroscopeService {

	private static final String API_URL = "https://aztro.sameerkumar.website/";
	
	/**
	 * Gets the horoscope of the day
	 * 
	 * @param horoscopeName Name of your horoscope
	 * @return Gives horoscope object with quotes and such
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static Horoscope getHoroscope(String horoscopeName) throws ClientProtocolException, IOException {
		Horoscope horoscope = null;
		HoroscopeEnum horoscopeEnum = null;
		
		//check input
		try {
			horoscopeEnum = HoroscopeEnum.valueOf(horoscopeName.toUpperCase());
		} catch(IllegalArgumentException ex) {
			return null;
		}
		
		//request params
		String params = "?sign=" + horoscopeEnum.name().toLowerCase() + "&day=today";
		
		//HTTP objects
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(API_URL + params);
		
		//Execute + get response
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
			
		if(entity != null) {
			InputStream inStream = entity.getContent();
			
			try {
				//read out the response
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(inStream, "UTF-8"));
				horoscope = deserialize(jsonObject);
				horoscope.setName(horoscopeName);
				
			} catch(IOException | ParseException ex) {
				ex.printStackTrace();
			} finally {
				//close connections
				inStream.close();
			}
		}
		
		//return object
		return horoscope;
	}
	
	private static Horoscope deserialize(JSONObject jsonObject) {
		//System.out.println(jsonObject.toJSONString());
		
		//read data out of jsonobject
		String description = (String)jsonObject.get("description");
		String mood = (String)jsonObject.get("mood");
		String color = (String)jsonObject.get("color");
		String luckyTime = (String)jsonObject.get("lucky_time");
		int luckyNumber = 0;
		try {
			String luckyNumberString = (String)jsonObject.get("lucky_number");
			luckyNumber = Integer.parseInt(luckyNumberString.trim());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String compatibility = (String)jsonObject.get("compatibility");
		
		//put data into an object
		Horoscope horoscope = new Horoscope("",
				description,
				mood,
				color,
				luckyTime,
				luckyNumber,
				compatibility
		);
		
		//give object back
		return horoscope;
	}
}
