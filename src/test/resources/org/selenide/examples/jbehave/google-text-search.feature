Feature: Google search
Scenario: user can search any keyword

Given an open browser with google.com
When a keyword selenide is entered in input field
Then top 10 matches should be shown
