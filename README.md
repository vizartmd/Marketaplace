# Marketplace application
> To start the application you must have Java 11.0.15, Maven 3.6.3, MySQL 8.0.12 or PostgreSQL 9.6 installed<br /><br />
> start MySQL or PostgreSQL, create 'marketplace' database<br />
> run the following commands in the command line:<br />
> git clone https://github.com/vizartmd/Marketplace.git<br />
> cd Marketplace/<br />
> mvn clean install -DskipTests<br />
> after BUILD SUCCESS run -> cd target/<br />
> if you don't need a password to access database use "" instead of \<MySQL password\>"<br />
> if you use MySQL database,<br />
> run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar mysql \<MySQL username\> \<MySQL password\><br />
> if you use PostgreSQL database,<br />
> run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar postgresql \<PostgreSQL username\> \<PostgreSQL password\><br />
> > if you use H2 database,<br />
  > run -> java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar h2 \<H2 username\> \<H2 password\><br />
> if Tomcat successfully started on port 8088,<br />
> run in browser -> http://localhost:8088<br />
> complete the registration and then log in, after successfully log in you can test the functionality of the "Marketplace application" as per the requirements<br />
> to view Swagger API Docs run in browser -> http://localhost:8088/swagger-ui.html





