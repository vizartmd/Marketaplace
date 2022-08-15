
# Marketplace application
> To start the application you must have Java 11.0.15, Maven 3.6.3, PostgreSQL 9.6 installed<br /><br />
> if you are going to use PostgreSQL database start it, create 'marketplace' schema<br />
> if you will use H2 in memory database, no need to create any schema<br /><br />
> run the following commands in the command line:<br />
> 1) git clone https://github.com/vizartmd/Marketplace.git<br />
> 2) cd Marketplace/<br />
> 3) mvn clean install -DskipTests<br />
> after BUILD SUCCESS run -><br />
> 4) cd target/<br />
> if you don't need a password to access database use "" instead of \<DB password\><br />
> if you are going to use PostgreSQL database run -><br />
> 5) java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar postgresql \<PostgreSQL username\> \<PostgreSQL password\><br />
> else if you are going to use H2 in memory database run -><br />
> 5) java -jar marketplace-rest-api-0.0.1-SNAPSHOT.jar h2 sa ""<br />
> if Tomcat successfully started on port 8088 run in browser -><br />
> 6) http://localhost:8088 and you'll see registration/login form<br />
> complete the registration and then log in, after successfully log in you can create some products and test the functionality of the "Marketplace application" as per the requirements<br />
> to view Swagger API Docs run in browser -><br />
> 7) http://localhost:8088/swagger-ui.html