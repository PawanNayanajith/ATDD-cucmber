Feature: Login

  Scenario: verify login for valid credentials
    Given I have OrangeHRM application loaded
    When I login with username "Admin" and password "admin123"
    Then I should navigate to the dashboard page

  @invalidCredentials
  Scenario Outline: verify login for invalid credentials
    Given I have OrangeHRM application loaded
    When I login with username "<username>" and password "<password>"
    Then I should get error message "Invalid credentials"
    
    Examples:
      | username |password |
      | admin    |admin    |
      | Admin    |Admin123 |
