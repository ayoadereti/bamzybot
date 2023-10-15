# BamzyBot
A custom implementation of PircBot for consuming REST APIs

# About  
With BamzyBot, you can retrieve weather and word data (potential definitions + synonyms) by running the program locally and connecting to the #bamzywurld channel in KiwiIRC, a web-based IRC client. Once connected, BamzyBot prompts you to enter a request for (1) the temperature and weather conditions of a location——a city name, US zip, or postal code——or (2) potential synonyms of a given word. 

# Getting Started 
This program was built and run using Visual Studio Code and Java SE 11. The following library files are included:

* PircBot 1.5.0
* Gson 2.6.2
* Apache Commons Lang 3.0

## Installation 
1. Clone the repository

   `git clone https://github.com/ayoadereti/bamzybot.git`
   
2. Launch the program in VSCode 

3. Connect to the #bamzywurld channel via KiwiIRC
   
   https://web.libera.chat/
   
## Usage 
You can request weather information using the stem "weather in" + a location query (city, zip code, postal code)
![Screenshot](weather.png)

You can request synonyms using the stem "synonyms of" + a word 
![Screenshot](synonyms.png)

End the chat when satisfied 
![Screenshot](endChat.png)
 
# License
Distributed under the GLP License. See 'LICENSE.txt' for more information

# Acknowledgements
Relevant links, including API documentation and resources used to help with implementation and understanding of
PircBot and IRC

* http://www.jibble.org/pircbot.php
* https://www.weatherapi.com/docs/
* https://dictionaryapi.com/products/api-collegiate-thesaurus
* https://www.baeldung.com/java-http-request
* https://www.postman.com/
* https://chrome.google.com/webstore/detail/json-viewer/gbmdgpbipfallnflgajpaliibnhdgobh?hl=en-US
