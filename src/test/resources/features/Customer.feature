Feature: To verify that Bayt site is functional according to the scenarios

#  ----------------------------------------------------------

  @Smoke @TC_01
  Scenario Outline:
    Given i am on About page
    And scrolldown to the footer and click on About link
    And user click on the specific job
    And user apply to the particular job
    And verify that validation msg gets occur on providing existing "<Email>" email
    And user gets successfully registered upon giving the random email

    Examples:
      | Email | Password |
      | Email | Password |


  @Smoke @TC_01
  Scenario:
    Given i am on Login page
    And navigate to the Account Settings
    And scroll down to delete existing account


  @Smoke @TC_01
  Scenario:
    Given i am on Home page
    Then resize the position to iPhonemobile
    And search the specific job
    And apply to the job
    Then verify that registration page appears