Feature: Image search

  Scenario: user can search images by keyword

    Given an open browser with duckduckgo.com
    When  enter a keyword "selenide" in input field
    And   click "Images" link
    Then  at least top 10 matching images should be shown
