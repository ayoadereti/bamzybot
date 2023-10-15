import java.io.IOException;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
// import org.apache.commons.lang3.text.WordUtils;


import org.jibble.pircbot.*;

public class MyBot extends PircBot {

    WeatherAPI cWeather;
    DictionaryAPI cWebster;
    
    public MyBot(WeatherAPI weather, DictionaryAPI word) {
        this.setName("BamzyBot");
        cWeather = weather;
        cWebster = word;
    }

    // Method for reading messages from the channel
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        // Responds to prompts for weather info
        if (message.toLowerCase().contains("weather in")) {
            StringBuilder geo = new StringBuilder();
            String[] temp;

            // Extracts zip code if present 
            if (message.matches(".*[0-9].*")) {
                for (int i = 0; i < message.length(); i++) {
                    if (Character.isDigit(message.charAt(i))) {
                        geo.append(message.charAt(i));
                    }
                }
            }
            else { // Extracts city name otherwise 
                geo.append(StringUtils.substringAfter(message, "weather in").trim());
            }
            
            // calls WeatherAPI with location info + output to chatbot
            try {
                temp = cWeather.getObject(geo.toString());

                // this.sendMessage("#CS2336", "The temperature in " + WordUtils.capitalizeFully(geo.toString()) + " is "  + temp[0] + "\u00B0C");
                this.sendMessage("#CS2336", "The temperature in " + temp[2] + ", " + temp[3] + " is "  + temp[0] + "\u00B0C");
                
                if (temp[1].contains("rain") || temp[1].contains("mist")) {
                    this.sendMessage("#CS2336", "There is " + temp[1].toLowerCase());
                }
                else {
                    this.sendMessage("#CS2336", "It is " + temp[1].toLowerCase());
                }
            } 
            catch (IOException e) {
               // e.printStackTrace();
                this.sendMessage("#CS2336", "There was an error in your message. Try again");
                this.sendMessage("#CS2336", "For weather requests, preface your location with \"weather in\"");
                this.sendMessage("#CS2336", "For synonym requests, preface your word with \"synonyms of\"");

            }
        }
        
        // Responds to prompts for synonyms 
        if (message.toLowerCase().contains("synonyms of")) {
            // calls DictionaryAPI with desired term 
            try {
                String term = StringUtils.substringAfter(message, "synonyms of").trim();
                ArrayList<ArrayList<String>> words;

                words = cWebster.getSyns(term);
                this.sendMessage("#CS2336", "Some synonyms of " + term + " are: ");

                for (ArrayList<String> w : words) {
                    for (String s : w) {
                        this.sendMessage("#CS2336", s);
                    }
                }
            } 
            catch (IOException e) {
               // e.printStackTrace();
                this.sendMessage("#CS2336", "There was an error in your message. Try again");
                this.sendMessage("#CS2336", "For weather requests, preface your location with \"weather in\"");
                this.sendMessage("#CS2336", "For synonym requests, preface your word with \"synonyms of\"");

            }        
        }
    
        // Responds to prompts expressing thanks 
        if (message.toLowerCase().contains("thank")) {
            this.sendMessage("#CS2336", "You're so welcome!");
            this.sendMessage("#CS2336", "Is there anything else I can help you with?");
            this.sendMessage("#CS2336", "(1) Weather\n");
            this.sendMessage("#CS2336", "(2) Synonyms\n");
            this.sendMessage("#CS2336", "(3) End Chat\n");
            this.sendMessage("#CS2336", "Enter a request with one of the keywords listed above\n");

        }

        // Responds to prompts to end chat
        if (message.toLowerCase().contains("end")) {
            this.sendMessage("#CS2336", "Okay! Until next time. Have a great day!"); 
        }

        // Responds to prompts that fall outside of the scenarios above 
        if (!message.toLowerCase().contains("weather in") && !message.toLowerCase().contains("synonyms of") && !message.toLowerCase().contains("thank") && !message.toLowerCase().contains("end")) {
                this.sendMessage("#CS2336", "There was an error in your message. Try again");
                this.sendMessage("#CS2336", "For weather requests, preface your location with \"weather in\"");
                this.sendMessage("#CS2336", "For synonym requests, preface your word with \"synonyms of\"");

        }
    }
}
