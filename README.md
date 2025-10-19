# Feedback App

A simple “Feedback App” that allows users to submit feedback and view submitted entries

## How to run it locally

Backend will run on port 8080 and frontend on 3000

### Backend
Navigate to the /backend folder and install with ``mvn clean install``.

Then run it with ``mvn spring-boot:run``.

Note: Maven should be installed globally or else use ``./mvnw`` commands instead.

### Frontend 
Navigate to the /frontend folder and install with ``npm install``.

Then run it with ``npm start``.


## How to run tests

### Backend
Navigate to the /backend folder and run ``mvn test``.

### Frontend 
Navigate to the /frontend folder and run ``npm test``.

## Future improvements and ideas

### Backend

- Hide sensitive data in a better way than just hardcoded "*".
- More testing. For example some end to end tests that run on real HTTP port.
- More filters to get the feedback list, for example sorting by created time, searching, pagination...
- Using a real database
- User authentication to allow users to edit or delete feedback.
- Spam protection for the form.

### Frontend

- Accessibility.
- Styling: better CSS rules, some library like styled components etc.
- Form validation: character limit for the text area, email regex validation etc.
- Env files for things like hardcoded urls and others.
- Proper CORS handling other than using localhost.
- Configure forms autocomplete.
- More testing: validation of fields, resetting values, feedback list...
- Loading animation for API calls.
- Global state management with context or Redux for example.
- Responsive design
  
