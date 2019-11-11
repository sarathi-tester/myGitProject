Feature: Login scenerios

Scenario: Login functionality validation

When I login into Sctock acounting URL on chrome browser and click go
When I click reset button on login page
And I enter admin in UserNameField
And I enter master in PasswordField
When I click on login button
When I wait for logout link
Then I validate the title of the homepage
Then I close the Chrome