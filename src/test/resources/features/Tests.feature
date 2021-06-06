@Regression @Sanity
Feature: Tests 

  Scenario: Login and verify Subscriptions pane fields are displayed correctly
    Given Website is opened
    When Login with Username: "TestUser1000" and Password: "Fidd123456!"
    Then Verify Subscriptions pane fields are displayed correctly

  Scenario: Login and verify Subscription page is opened successfully, click on 'ADD' button and verify 'Save Cards' tab is opened
    Given Website is opened
    When Login with Username: "TestUser1000" and Password: "Fidd123456!"
    And Click on ‘Manage Subscription’
    Then Verify subscription page is opened
    And Click on the ‘ADD’ button
    Then Verify ‘Saved Cards’ tab is opened

  Scenario: Login and verify annual payment is selected by default, Total Charge is calculated correctly
    Given Website is opened
    When Login with Username: "TestUser1000" and Password: "Fidd123456!"
    And Click on ‘Upgrade to Pro now’ button
    Then Verify ‘Process Order’ page is opened and annual payment is selected by default
    Then Upgrade with Monthly subscription with Seats: 3 and verify Total Charge is correct
    Then Verify the card details form is displayed
