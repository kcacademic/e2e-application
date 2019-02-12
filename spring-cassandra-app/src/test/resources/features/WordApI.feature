Feature: Rest API for word frequencies
 Scenario: If requested, return list of words
  Given There is data available
  When Request on /api/words
  Then Return list of words