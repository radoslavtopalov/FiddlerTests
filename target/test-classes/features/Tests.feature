Feature: Tests

  Scenario: Login with valid credentials and verify Current plan = FREE
    Given Website is opened
    When Login with Username: "TestUser1000" and Password: "Fidd123456!"
    Then Verify Subscriptions pane fields are displayed correctly


