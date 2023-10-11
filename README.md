# bamzybot
A custom implementation of Pircbot for consuming the WeatherAPI.com and Merriam-Webster Dictionary APIs

## About 
The project employs a custom implementation of Pircbot to request and consumes two REST APIs. The first API, WeatherAPI.com, returns the temperature and weather conditions of a given location based on the city name, US zip, or postal code; in the absence of an exact match, this API returns the data for the location most similar to the query parameter. The second API, Merriam-Webster Dictionary, returns synonyms of the various meanings of a given word. The user is able to execute the program and interact with the Pircbot implementation by connecting to a channel in a web-based IRC client. Approrpiate prompts from the user within the chat will result in the requested information being pushed to the channel, whereas unsatisfactory input will prompt correction from the chatbot.

## Getting Started 
