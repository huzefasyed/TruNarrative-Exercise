- where the app starts:
  The app starts in the file src - > main - > TruNarrativeSpringExerciseApplication, run this class to get the springboot app running.
  The api calls made can be found in: src -> controller -> SearchApiController class which uses the SearchApiService.

- how to run it:
  Can start by running TruNarrativeSpringExerciseApplication, after that use this endpoint: http://localhost:8080/searchAPI, but to invoke this endpoint 
  you'll need an input since were sending a post request, the input is: 
  
  {
  "companyName" : "BBC LIMITED",
  "companyNumber": "06500244"
  }
  
  However you must use your own api key provided.
  If both arugments are parsed then companyNumber is used else companyName is used.
  

- Did i meet the requirements:
  The result of the search is returned as JSON - Yes
  A request parameter has to be added to decide whether only active companies should be returned - No
  The officers of each company have to be included in the company details (new field officers) - Yes
  Only include officers that are active (resigned_on is not present in that case) - No
  Please add unit tests and integrations tests, e.g. using WireMock to mock TruProxyAPI calls - Yes

- if i had time what i would've done different:

  Would've completed the requirements properly including the bonus parts but due to lack of time i aimed to write clean code that follows a 
  pretty simple but readable style of code including structing of folders and etc. Would've also added more test cases for some of the other methods.
  
  

