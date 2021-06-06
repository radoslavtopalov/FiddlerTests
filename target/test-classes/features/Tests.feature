Feature: Tests

  @Regression @Sanity
  Scenario: Login and verify Subscriptions pane fields are displayed correctly
    Given Website is opened
    When Login with Username: "TestUser1000" and Password: "Fidd123456!"
    Then Verify Subscriptions pane fields are displayed correctly


