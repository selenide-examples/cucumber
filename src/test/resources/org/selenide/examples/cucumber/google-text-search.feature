Feature: Google search

  Scenario: user can search any keyword

    Given an open browser with google.com
    When a keyword selenide is entered in input field
    Then top 9 matches should be shown
    Then the first one should contain selenide.org

  Scenario: user can search any keyword in Russian

    Given an open browser with google.com
    When a keyword селенид is entered in input field
    Then top 10 matches should be shown
    Then the first one should contain селениды
