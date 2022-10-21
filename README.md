Getting Started with Fuel App
This project was created with Angular and Spring Boot. Frontend folder contains Angular project and the Backend folder contains Spring Boot project.

Project setup:
Download the project and navigate to frontend folder and run the below commands

scripts:

-- To run the frontend in development mode --

"npm install"
Install all the packages to the project

"npm start"
Open http://localhost:4200 to view it in the browser.

-- To deploy the both frontend and backend projects and run in production --

"npm install"
Install all the packages to the project

"ng build --prod --base-href /Frontend/"
Builds the app for production to the build folder named as Frontend and place it on a webserver.
Then go to http://localhost/Frontend

It correctly bundles Angular in production mode and optimizes the build for the best performance.

To run the backend project, first clean and build the project from maven and run the jar file. 
"java -jar Backend-1.0-SNAPSHOT.jar"

Now your app is ready to be deployed and run!

Learn More
You can learn more in the Angular App documentation.

To learn Spring boot, check out the Spring documentation.
