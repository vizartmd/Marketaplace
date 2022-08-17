## Task:
> To create a Rest API for a "marketplace".
> 1. User Registration/Authentication.
> 2. CRUD for own products
> 3. List with pagination for all products
> 4. Like/Unlike for products that do not belong to the authenticated user (a product cannot receive like and dislike from the same user).
> 5. Add the README file with the necessary steps to build and run the application with the necessary parameters.

### Other requirements
> Note - The basic entities are very simple, User (username, email, password) and Product (title, description, price).<br />
> Framework - Spring (Spring security, spring data (MyBatis-preferably or Hibernate)).<br />
> Database - H2/PostgreSql<br />
><br />
> Endpoints:<br />
> Create product<br />
> Edit product<br />
> Delete product<br />
> View product<br />
> List my-products<br />
> List products<br />
> Patch like-product<br />
> Patch unlike-product<br />
><br />
> package manager - Maven<br />
> Language - min. Java 8<br />
> API Docs - Swagger<br />

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
> complete the registration and then log in<br />
> after successfully log in you can test the Rest API in Postman or test the application through the GUI built with the MVC architecture. You can create some products and test the functionality of the "Marketplace App" as required<br />
### endpoints product-rest-controller:<br />
> /view-product-by-id/productId<br />
> /edit-product<br />
> /delete-product/productId<br />
> /all-products<br />
> /all-my-liked-products/loggedUserEmail<br />
> /all-my-disliked-products/loggedUserEmail<br />
> /like-products/productId/email/loggedUserEmail<br />
> /dislike-products/productId/email/loggedUserEmail<br />
> /reset-likes-of-product/productId/email/loggedUserEmail<br />
> /create-product<br />
### endpoints user-rest-controller:<br />
> /addUser<br />
> /all-users<br />
> /all-my-products/loggedUserEmail<br />
> /add-to-my-products/productId/email/loggedUserEmail<br />
> /remove-from-my-products/productId/email/loggedUserEmail<br />
> to view Swagger API Docs run in browser -><br />
###
> 7) http://localhost:8088/swagger-ui.html