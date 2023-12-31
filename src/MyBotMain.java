// import org.jibble.pircbot.*;

public class MyBotMain {
    
    public static void main(String[] args) throws Exception {
        
        // Start the requests
        WeatherAPI weather = new WeatherAPI();
        DictionaryAPI word = new DictionaryAPI();

        // Now start our bot up.
        MyBot bot = new MyBot(weather, word);
        
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.us.libera.chat");

        // Join the #bamzywurld channel.
        bot.joinChannel("#bamzywurld");

        // Output the default message to indicate the bot has got live
        bot.sendMessage("#bamzywurld", "Hello! What kind of information would you like to retrieve?");
        bot.promptMenu();
    }
    
}