Feature: To verify the Login Module after create an account and create a customer

  @Smoke @TC_01
  Scenario Outline:
    Given i am on Guru99 page
    And Enter "<Email>" data inside Email field for Login
    And Enter "<Password>" data inside Password field for Login
    Then click on Login button
    Then Verify that user successfully gets login

    Examples:
      | Email | Password |
      | Email | Password |


  @Smoke @TC_02
  Scenario Outline:
    Given i am on customer page
    Then click on NewCustomer tab
    And removed the unnecessary popup
    And Enter "<CustomerName>" data inside CustomerName field
    And Select radio button
    And Select "<Date>" accordingly
    And Enter "<Address>" data inside Address field
    And Enter "<City>" data inside City field
    And Enter "<State>" data inside State field
    And Enter "<PIN>" data inside PIN field
    And Enter "<MobileNumber>" data inside MobileNumber field
    And Enter "<EmailField>" data inside Email field
    And Enter "<PasswordField>" data inside Password field
    Then click on Submit button
    Then Verify that user successfully gets created
    Then validate the records

    Examples:
      | EmailField | PasswordField | CustomerName | Date | Address | City | State | PIN | MobileNumber |
      | EmailField | PasswordField | CustName     | Date | Address | City | State | Pin | MobileNumber |