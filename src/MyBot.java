import java.util.*;
import org.apache.commons.lang3.StringUtils;

import org.jibble.pircbot.*;

public class MyBot extends PircBot {
    WeatherAPI cWeather;
    DictionaryAPI cWebster;
    
    public MyBot(WeatherAPI weather, DictionaryAPI word) {
        this.setName("BamzyBot");
        cWeather = weather;
        cWebster = word;
    }

    // Method for displaying prompt menu 
    public void promptMenu() {
        this.sendMessage("#bamzywurld", "(1) Weather");
        this.sendMessage("#bamzywurld", "\tFor weather requests, preface your location with \"weather in\"");
        this.sendMessage("#bamzywurld", "\tYou can provide a city name, zip code, or postal code");
        this.sendMessage("#bamzywurld", "(2) Synonyms");
        this.sendMessage("#bamzywurld", "\tFor thesaurus requests, preface your word with \"n synonyms of\"");
        this.sendMessage("#bamzywurld", "\tReplace \"n\" with the number of desired results");
        
    }

    // Method for reading messages from the channel
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        // Respond to prompts for weather info
        if (message.toLowerCase().contains("weather in")) {
            try {
                // Extract location data 
                String geo = StringUtils.substringAfter(message, "weather in").trim();
                
                // Call WeatherAPI with location info 
                String[] temp = cWeather.getObject(geo.toString());

                // Send output to channel
                this.sendMessage("#bamzywurld", "The temperature in " + temp[2] + ", " + temp[3] + " is "  + temp[0] + "\u00B0C");
                
                if (temp[1].contains("rain") || temp[1].contains("mist")) {
                    this.sendMessage("#bamzywurld", "There is " + temp[1].toLowerCase());
                }
                else {
                    this.sendMessage("#bamzywurld", "It is " + temp[1].toLowerCase());
                }
            } 
            catch (Exception e) {
                this.sendMessage("#bamzywurld", "There was an error in your message. Try again");
                this.promptMenu();
            }
        }
        
        // Respond to prompts for synonyms 
        else if (message.toLowerCase().contains("synonym")) {
            try {
                // Extract word data 
                int n = Integer.parseInt(message.replaceAll("[^0-9]", "").trim());
                String term;
                if ((term = StringUtils.substringAfter(message, "synonym of").trim()) == "") {
                    term = StringUtils.substringAfter(message, "synonyms of").trim();
                };

                // call DictionaryAPI with the word and number of synonyms requested
                List<ArrayList<String>> definitions = cWebster.getSyns(term, n);
                
                // Send output to channel
                this.sendMessage("#bamzywurld", "Synonyms of " + term + ",");

                for (ArrayList<String> w : definitions) {
                    for (String s : w) {
                        if (s.startsWith("(")) {
                            this.sendMessage("#bamzywurld", "as in " + s + ", are:");
                        }
                        else {
                            this.sendMessage("#bamzywurld", s);
                        }
                    }
                }
            } 
            catch (Exception e) {
                this.sendMessage("#bamzywurld", "There was an error in your message. Try again");
                this.promptMenu();
            }        
        }
    
        // Respond to prompts expressing thanks 
        else if (message.toLowerCase().contains("thank")) {
            this.sendMessage("#bamzywurld", "You're so welcome!");
            this.sendMessage("#bamzywurld", "Is there anything else I can help you with?");
            this.promptMenu();
            this.sendMessage("#bamzywurld", "(3) End Chat\n");
        }

        // Respond to prompts to end chat
        else if (message.toLowerCase().contains("end chat")) {
            this.sendMessage("#bamzywurld", "Okay! Until next time. Have a great day!"); 
        }

        // Respond to prompts with greetings
        else if (message.toLowerCase().contains("hey") || message.toLowerCase().contains("hello") || message.toLowerCase().contains("hi")) {
            this.sendMessage("#bamzywurld", "Hello! What kind of information would you like to retrieve?");
            this.promptMenu();            
        }

        else {
            this.sendMessage("#bamzywurld", "There was an error in your message. Try again");
            this.promptMenu();
        }
    }
}
