Feature: Tag Cloud for word frequencies
 Scenario: Get the title of webpage
  Given Request is sent to homepage
  When Response is ok
  Then There is a tag cloud page rendered