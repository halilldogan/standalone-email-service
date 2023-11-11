Standalone Email Service
===

The Standalone Email Notification Service is a robust Spring Boot solution that seamlessly integrates multiple Email service providers, including SMTP and AWS SES.

## Requirements
Before running the project, please ensure that you provide the necessary environment variables for proper configuration. The YAML file (application.yml) contains placeholders within ${} for sensitive information such as access credentials, API tokens, and authentication details. When launching the project, make sure to replace these placeholders with the actual values of your credentials in your local environment or deployment platform. These environment variables ensure secure and personalized configuration for services like AWS and SMTP allowing the Standalone Email Notification Service to function seamlessly and securely.

``` YAML
cloud:
    aws.mailFrom: <aws-mail>
    aws.access-key: <aws-access-key>
    aws.secret-key: <aws-secret-key>
    aws.region: <aws-region>

smtp:
    mail:
      host: smtp.gmail.com
      port: 587
      username: <username>
      password: <password>
      properties:
        mail:
          smtp:
            auth: true
            starttls:
              enable: true
```

## Running the application
You can directly run your application your local machine. There’s no need to install a Servlet container. In a terminal window execute the following command from the root level of the project:

#### Mac OS X/ Unix
<code>./gradlew bootRun </code> 

#### Windows
<code>gradlew.bat bootRun </code> 

## Using the Service
To leverage the capabilities of the Service, you can make requests to the designated endpoint using your preferred HTTP client. Follow the steps below to send Email notifications:

#### Endpoint:
```
http://localhost:8080/email/send
```

#### Request Body:
``` JSON
{
  "to": ["example@gmail.com"],
  "subject": "Test Email",
  "htmlContent": "<h1>Test Email</h1>",
  "plainContent": "Test Email",
  "channel": "SMTP"
}
```
Select one of the desired Email providers (SMTP or AWSSES) by specifying the corresponding channel in the request.  
