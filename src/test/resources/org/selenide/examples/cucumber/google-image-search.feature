Feature: Google image search
Scenario: user can search images by keyword

Given an open browser with google.com
When  click "Images" link
And   enter a keyword selenide in input field
Then  at least top 100 matching images should be shown
