# azure-identity-mac-issue

This project is set up to highlight an issue with the azure-identity Java 
library when working with the Azure plugin for Intellij and Java (specifically
Spring Boot) apps that access AZ KeyVault and possible other Azure data on a Mac.

See the [opened issue in the azure-identity project](https://github.com/Azure/azure-sdk-for-java/issues/39799)
for more details of what I believe is the cause of the issue highlighted here.

## Running the sample

* Install IntelliJ Ultimate 2023.3.2 on a Mac (running MacOS Sonoma).

* Install AZ toolkit plugin for IntelliJ and then log into an AZ account 
  with access to a key vault.

* Update the `application.properties` file in this project to set the correct
  Keyvault URI that your account has access to (in the `spring.cloud.azure.keyvault.secret.endpoint`
  property).

* Import this project into IntelliJ (using Gradle import) and run the supplied Spring Boot
  app `AzureIdentityMacIssueApplication`.

* You should see a log entry similar to this:

   ```
   2024-05-09T11:16:36.514+01:00  INFO 37287 --- [azure-identity-mac-issue] [           main] c.azure.identity.ChainedTokenCredential  : Azure Identity => Attempted credential IntelliJCredential is unavailable.
   ```

  Note that if you are logged into the Azure CLI and the CLI tools are in your 
  PATH, the login may succeed, as the `ChainedTokenCredential` falls through to using 
  the CLI if it can't get the creds from IntelliJ. But in some dev environments
  it is not desirable to have the AZ CLI tools added to the path for local dev,
  moreover the `IntelliJCredential` is higher up in the priority list and should
  be able to perform the login.