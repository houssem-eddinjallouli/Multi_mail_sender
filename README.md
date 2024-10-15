# Multi-Mail Sender Application

This Spring Boot application sends a predefined email to a list of recipients stored in a `mails.json` file.

## Setup Instructions

1. **Update Gmail Credentials**:

   In `src/main/resources/application.properties`, update:

   ```properties
   spring.mail.username=yourgmail@gmail.com
   spring.mail.password=your16digitcode

2. **Add Recipient Details**:
   
  In `src/main/resources/mails.json`, add recipients in the same format (names are optional but of course the email is a required field)

4. **Run the spring app**:

5. **Access to this URL to Send Emails**:
  http://localhost:8089/mail/send
