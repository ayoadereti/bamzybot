import java.io.*;
import java.net.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherAPI
{
	public String[] getObject(String location) throws IOException {
		// Format location data for URL String if necessary
		if (location.contains(" ")) {
			location = location.replace(' ', '%');
		}

	        // Store the components of the URL string
	        final String API_KEY = "d4b2698cffeb4b028dc221159231608";
	        String urlString = "http://api.weatherapi.com/v1" + "/current.json?key=" + API_KEY + "&q=" + location;

		// Create a URL object 
		URL weather = new URL(urlString);
		
		// Create a HttpUrlConnection object 
		HttpURLConnection con = (HttpURLConnection) weather.openConnection();
		
		// Create a get request to establish the connection 
		con.setRequestMethod("GET");
		
		// Create a BufferedReader for the connection inputStream
		BufferedReader in = new BufferedReader(new InputStreamReader(weather.openStream()));
		
		// Convert the BufferedReader to a String and store in result variable
		String buffer;
		String result = ""; 

		while ((buffer = in.readLine()) != null) {
		    result += buffer;
		}
	
		// Close the BufferedReader 
    		in.close();

		// Call the parseJsonTemperature method
		JsonObject temp = parseJsonTemperature(result);

		// Create a String array to store temperature data 
		// Parse temperature, weather conditions, city, and country name from the JSON object
		String[] tempC = new String[4];

		tempC[0] = temp.get("current").getAsJsonObject().get("temp_c").getAsString();
		tempC[1] = temp.get("current").getAsJsonObject().get("condition").getAsJsonObject().get("text").getAsString();
		tempC[2] = temp.get("location").getAsJsonObject().get("name").getAsString();
		tempC[3] = temp.get("location").getAsJsonObject().get("country").getAsString();

		// Replace country with state names for American cities 
		if (tempC[3].contains("United States")) {
			tempC[3] = temp.get("location").getAsJsonObject().get("region").getAsString();
		}

		// Return the temp array to the calling function 
		return tempC;
	}

	public JsonObject parseJsonTemperature(String json) {
		// Create a JSON parser 
		JsonParser parser = new JsonParser();

		// Create a JSON Object to store the weather data
		JsonObject weatherObj = (JsonObject) parser.parse(json).getAsJsonObject();

		// return the JSON Object
		return weatherObj;
	}
	
}
