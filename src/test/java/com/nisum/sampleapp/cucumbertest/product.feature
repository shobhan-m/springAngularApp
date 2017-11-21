Feature: Testing Product Rest API 
  Users should be able to submit GET and POST requests to a web service, 
  represented by WireMock

  Scenario: Data submitted to Web service
    When users submitted data to the Web service 
    Then the server should return a success status
    
  Scenario: Data retrieval from a web service
    When users want to get information on the Cucumber project
    Then the requested data is returned   