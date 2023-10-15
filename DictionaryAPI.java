import java.util.*;
import java.io.*;
import java.net.*;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DictionaryAPI
{
	public ArrayList<ArrayList<String>> getSyns(String word) throws IOException {

        // Store the components of the URL string
		final String API_KEY = "c29420c8-93b2-42bc-82ca-09a2d3aa3452";
        String urlString = "https://www.dictionaryapi.com/api/v3/references/thesaurus/json/" + word + "?key=" + API_KEY;

		// Create a URL object 
		URL diction = new URL(urlString);
		
		// Create a HttpUrlConnection object 
		HttpURLConnection con = (HttpURLConnection) diction.openConnection();
		
		// Create a get request to establish the connection 
		con.setRequestMethod("GET");
		
		// Create a BufferedReader for the connection inputStream
		BufferedReader in = new BufferedReader(new InputStreamReader(diction.openStream()));
		
		// Convert the BufferedReader to a String and store in result variable
		String buffer;
		String result = ""; 

		while ((buffer = in.readLine()) != null) {
		    result += buffer;
		}
		
		// Close the BufferedReader
    	in.close();
	
		// Call the parseJsonSynonyms method 
		JsonArray synonyms = parseJsonSynonyms(result);

		// This code parses and pushes all the synonyms for all potential defitions
		// of a given word. Each potential defition or set of related defitions and 
		// its respective synonyms are stored in an ArrayList of Strings. Each 
		// ArrayList is added to an ArrayList to store all possible definition/synonym
		// sets in one structure.
		// 
		// Outputting all definition/synonym data can be long and timely. The code 
		// is slightly modified to only output the first three synonyms of the first 
		// three potential defitions/definition sets for the sake of efficiency. 
		// This can easily be reverted to its original output by commenting out/re-introducing
		// a few lines 

		ArrayList<ArrayList<String>> options = new ArrayList<>();

		// for (int i = 0; i < synonyms.size(); i++) {
		for (int i = 0; i < synonyms.size() && i < 3; i++) {
			ArrayList<String> possibleSyn = new ArrayList<>();

			JsonArray shortdef = (JsonArray) synonyms.get(i).getAsJsonObject().get("shortdef").getAsJsonArray();

			// for (int j = 0; j < shortdef.size(); j++) {
			for (int j = 0; j < shortdef.size() && j < 3; j++) {
				String def = "(" + (j+1) + ") " + StringUtils.capitalize(shortdef.get(j).getAsString());
				possibleSyn.add(def);
			}

			JsonObject meta = (JsonObject) synonyms.get(i).getAsJsonObject().get("meta");
			JsonArray syns = (JsonArray) meta.getAsJsonArray("syns");

			// for (int k = 0; k < syns.size(); k++) {
			//for (int k = 0; k < syns.size() && k < 3; k++) {
				// Create a Json array to store access each element as an array
				JsonArray s = syns.get(0).getAsJsonArray();

				// Iterate through the synonyms in each "syns" element
				// for (int l = 0; l < s.size(); l++) {
				for (int l = 0; l < s.size() && l < 3; l++) {

					// Add the synonyms to the ArrayList
					possibleSyn.add("\t" + StringUtils.capitalize(s.get(l).getAsString()));
				}

				possibleSyn.add("\n\n");
			// }

			options.add(possibleSyn);
		}
		
		return options;

		/* 
		// The "syns" member of the JSON temperature data is an
		// array of arrays of synonyms. The "syns" member exists 
		// for every potential definition of a given word.
		// This code parses and pushes only the synonyms for the
		// first potential definition/definition set of a given word 
		// to the chat when prompted
		
		// Create an ArrayList of Strings to store the synonym data
		ArrayList<String> syns = new ArrayList<>();

		// Each element in the "syns" array stores an array of synonyms
		// Iterate through the elements of this array
		for (int i = 0; i < synonyms.size(); i++) {

			// Create a Json array to access "syns" each element as an array
			JsonArray s = synonyms.get(i).getAsJsonArray();

			// Iterate through the synonyms in each "syns" element
			for (int j = 0; j < s.size(); j++) {
				// Add the synonyms to the ArrayList
				syns.add(StringUtils.capitalize(s.get(j).getAsString()));
			}
		}

		// return the ArrayList
		return syns;
		*/
	}

	public JsonArray parseJsonSynonyms(String json) {
		// Create a JSON parser
		JsonParser parser = new JsonParser();

		// Create a JSON array to store the JSON temperature data 
		JsonArray array = (JsonArray) parser.parse(json).getAsJsonArray();

		// Return the JSON array 
		return array;

		/*
		// This code parses the synonyms for the first potential definition
	
		// Create a JSON object to parse the "meta" member
		JsonObject object = (JsonObject) parser.parse(json).getAsJsonArray().get(0).getAsJsonObject().get("meta");

		// Create a JSON array to parse the "syns" member
		JsonArray synArr = object.getAsJsonArray("syns");

		// Return the JSON array
		return synArr;
		*/

	}
	
}
