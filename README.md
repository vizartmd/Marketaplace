# Marketplace application
> To start the application you must have Java 11.0.15, Maven 3.6.3 and MySQL 8.0.12 installed<br /><br />
> start MySQL, create 'marketplace' database by running in MySQL -> CREATE SCHEMA `marketplace`;<br />
> run the following commands in the command line:<br />
> git clone https://github.com/vizartmd/Marketplace.git<br />
> cd Marketplace/<br />
> mvn clean install -DskipTests<br />
> after BUILD SUCCESS run -> cd target/<br />
> if you don't need a password to access MySQL,<br />
> run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar \<MySQL username\> ""<br />
> else run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar \<MySQL username\> \<MySQL password\><br />
> if Tomcat successfully started on port 8082,<br />
> run in MySQL scripts from add_products.sql file witch is in resources folder to create some products in the database<br />
> run in browser -> http://localhost:8082<br />
> complete the registration and then log in, after successfully log in you can create another products and test the functionality of the "Marketplace application" as per the requirements<br />
> to view Swagger API Docs run in browser -> http://localhost:8088/swagger-ui.html





