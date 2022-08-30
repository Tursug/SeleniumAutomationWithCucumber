
Feature: Show Latest Classified
  @latest
  Scenario Outline: User sees the latest classified
    Given a web browser is at the home page
    When user searched <classified>
      And user clicks submit
    Then user sees classifieds

    Examples:
    |classified |
    |daire      |

