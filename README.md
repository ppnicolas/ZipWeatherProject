# ZipWeatherProject

OpenWeather APPID=a45b367e1afd40b609077679dafd40a7

----------------------------------------------------
Notes from OW API
----------------------------------------------------
How to get accurate API response
1 Do not send requests more than 1 time per 10 minutes 
  from one device/one API key. 
  Normally the weather is not changing so frequently.
  
2 Use the name of the server as api.openweathermap.org 
  Please never use the IP address of the server.
  
3 Call API by city ID instead of city name, city coordinates 
  or zip code. In this case you get precise respond exactly 
  for your city. The cities' IDs can be found in the following 
  file: Cities' IDs list.
  
4 Free and Startup accounts have limitation of capacity and 
  data availability. If you do not get respond from server 
  do not try to repeat your request immediately, but only 
  after 10 min. Also we recommend to store your previous request data.

Access limitation
  If account exceeds the limits, then a notification about limits 
  exceeding is sent. If it repeats again, then the account is blocked 
  for an hour. 
  Therefore, the lock period is increased by one hour until 4 hours block sets. 
  When blocking repeats the fifth time, then the lock period lasts 24 hours. 
  This rule is cycled. Please be carefull with the number of API calls you complete.

**********************************************************
Application Constrains
**********************************************************
1 Access limitation 
	As we are using an free Open Weather account, to avoid Access Limitation 
	penalties application will be allow to send request only after a minimum
	of 10 minutes from the last request.
	
2 Recent Searches Cities
	The list of recent search/request will hold a predefined number(10) of maximum 
	requests, this value can be set on application settings (no include in the project) 

3 	The application get UNACCURATE data, could be type of account used to access OpenWeather API

		
**********************************************************
Application 
**********************************************************
Activities Main
    Allows to enter a zip code to search for
    Show a list of the recent searches cities ( max 10) 

    Click on Search FAB button search for ZIP CODE (if valid)
	and opens Activity Details to show info
	
	Click on Recent Searches search for CITY ID and 
	opens Activity Details to show info
	
	Last Search appears on top of the list;
	
	Click on Menu > Clear Recent Searches remove all
	entries from Recent Searches

Activity Details
	Show Weather condition details for the result citybase on zip code or city Id 
	
AppGlobals
		Singleton class to hold list of Recent Searches, max recent elements,..

WeatherHttpGetConditionsAsync
	Get Asynchrony weather information and update  Activity Details 
	
WeatherHttpRequest	
	Send Http requests to the OW API 
	Convert Json response to WeatherInfo object
	
Application implementation use MVP on both Activities defined in the interfaces 
    ActivityMainInterface
	    PresenterInterface
		ViewInterface

	ActivityDetailsInterface
	    PresenterInterface
		ViewInterface

Application use Interface MenusInterface to segregate Menu actions common two both Activities 
    SOLID principles (Interface Segregation)	
	
	

	
