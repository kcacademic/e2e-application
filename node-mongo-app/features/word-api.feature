Feature: Rest API for word frequencies
 Scenario: If requested, return list of words
  Given Request is sent to api
  When Response is generated
  Then There is a list of 10 words or less in the response