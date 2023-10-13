import java.util.*;
import java.io.*;
import java.net.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class DictionaryAPI
{
	public List<ArrayList<String>> getSyns(String word, int num) throws IOException {

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

		// Create an ArrayList to store/access all definition/synonym sets 
		List<ArrayList<String>> thesaurus = new ArrayList<>();

		// Create a Json array and parse all definitions of the word 
		JsonArray shortdef = (JsonArray) synonyms.get(0).getAsJsonObject().get("shortdef").getAsJsonArray();

		// Create a Json array and parse all possible synonyms of the word
		JsonArray syns = (JsonArray) synonyms.get(0).getAsJsonObject().get("meta").getAsJsonObject().get("syns").getAsJsonArray();

		// Iterate through the array of definitions
		for (int j = 0; j < shortdef.size(); j++) {
			// Create an ArrayList to store/access the current definition/synonym set 
			ArrayList<String> syn = new ArrayList<>();

			String definition = "(" + (j+1) + ") " + shortdef.get(j).getAsString();
			syn.add(definition);

			// Create a Json array to store/access the synonyms for the current definition
			JsonArray s = syns.get(j).getAsJsonArray();

			// Iterate through the array of synonyms for the current definition
			for (int k = 0; k < num && k < s.size(); k++) {
				syn.add("\t" + s.get(k).getAsString());
			}

			if (s.size() < num) {
				syn.add("\tOnly " + s.size() + " synonyms were found for this definition");
			}
		
			thesaurus.add(syn);
		}
		
		// Return the thesaurus ArrayList to the calling function 
		return thesaurus;
	}

	public JsonArray parseJsonSynonyms(String json) {
		// Create a JSON parser
		JsonParser parser = new JsonParser();

		// Create a JSON array to store the JSON dictionary data 
		JsonArray array = (JsonArray) parser.parse(json).getAsJsonArray();

		// Return the JSON array 
		return array;
	}
	
}
