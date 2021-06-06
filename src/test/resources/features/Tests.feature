@Regression @Sanity
Feature: Tests

  Scenario: Login and verify Subscriptions pane fields are displayed correctly
    Given Website is opened
    When Login with Username: "TestUser1000" and Password: "Fidd123456!"
    Then Verify Subscriptions pane fields are displayed correctly

    Scenario: Test2
      Given Website is opened
      When Login with Username: "TestUser1000" and Password: "Fidd123456!"
      And Click on ‘Manage Subscription’
      Then Verify subscription page is opened
      And Click on the ‘ADD’ button
      Then Verify ‘Saved Cards’ tab is opened