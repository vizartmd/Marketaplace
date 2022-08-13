# Marketplace application
> To start the application you must have Java 11.0.15, Maven 3.6.3, MySQL installed<br /><br />
> start MySQL, credentials: username = "root", password=""<br />
> create 'marketplace' database by running in MySQL -> CREATE SCHEMA `marketplace`;<br />
> run the following commands in the command line:<br />
> git clone https://github.com/vizartmd/Marketplace.git<br />
> cd marketplace-rest-api/<br />
> mvn clean install<br />
> after BUILD SUCCESS run -> cd target/<br />
> if you don't need a password to access MySQL, run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar <MySQL username> ""<br />
> else run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar <MySQL username> <MySQL password><br />
> if Tomcat successfully started on port 8082<br />
> run in browser -> http://localhost:8082<br />
> complete the registration and then log in, after successfully log in create some products and test the functionality of the "Marketplace application" as per the requirements<br />





