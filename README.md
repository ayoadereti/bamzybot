# BamzyBot
A custom implementation of PircBot for consuming REST APIs

# About  
With BamzyBot, you can retrieve weather and word data (potential definitions + synonyms) by running the program locally and connecting to the #bamzywurld channel in KiwiIRC, a web-based IRC client. Once connected, BamzyBot prompts you to enter a request for (1) the temperature and weather conditions of a location——a city name, US zip, or postal code——or (2) potential synonyms of a given word. 

# Getting Started 
## Prerequisites
You will need Java SE 8+ on your computer to run this program. The program was built and run using Visual Studio Code.

The following library files are included:

* PircBot 1.5.0
* Gson 2.6.2
* Apache Commons Lang 3.0

## Installation 
1. Clone the repository

   `git clone https://github.com/ayoadereti/bamzybot.git`


2. Navigate to the project directory
   
   `cd bamzybot`


3. Launch the program in VSCode
   
   `code .`


4. Connect to the #bamzywurld channel via KiwiIRC
   
   https://web.libera.chat/

   
## Usage 
1. You can request weather information using the stem "weather in" + a location query (city, zip code, postal code)

<img width="662" alt="weather" src="https://github.com/ayoadereti/bamzybot/assets/132008328/75b1d754-7961-4a75-a8dc-cdad6226bd66">



2. You can request synonyms using the stem "synonyms of" + a word 

<img width="668" alt="synonyms" src="https://github.com/ayoadereti/bamzybot/assets/132008328/456e2a02-993f-435a-b60c-1c681aef0fa3">



3. End the chat when satisfied 

<img width="668" alt="endChat" src="https://github.com/ayoadereti/bamzybot/assets/132008328/ae972ba9-fbb5-4304-8462-410bc234dc6a">


 
# License
Distributed under the GPL License. See 'LICENSE.md' for more information

# Acknowledgements
Relevant links, including API documentation and resources used to help with implementation and understanding of
PircBot and IRC

* http://www.jibble.org/pircbot.php
* https://www.weatherapi.com/docs/
* https://dictionaryapi.com/products/api-collegiate-thesaurus
* https://www.baeldung.com/java-http-request
* https://www.postman.com/
* https://chrome.google.com/webstore/detail/json-viewer/gbmdgpbipfallnflgajpaliibnhdgobh?hl=en-US
