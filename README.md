# spring-jpa-h2
A book store application implemented with Spring Boot. It has the following dependencies:
* JDK 17
* Spring Boot 3.1.0
* Spring Data JPA 3.1.0
* H2 Database 
* Swagger 3

# Implementation details
* H2 database is started by the configuration in **application.properties** and runs in the memory
* Book repository **BookRepository** extends **JpaRepository** and includes a couple of new queries
* The Entity **Book** interact with H2 table **books**
* **BookService** drives **BookRepository** to handle the requests generated by the controller **BookController** 
* **BookController** defines a few endpoints which allow the user to create new book, query and update the existing ones 
* **SpringJpaH2Application** is the main entry point to start the application
* **SwaggerConfig** starts and configures the Swagger 3
* Use the Swagger UI to access all endpoints at http://localhost:8080/swagger-ui/index.html#/

# Enable Swagger 3
* Add the following dependency to **pom.xml**

```
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.1.0</version>
		</dependency>
```
* Create a configuration **SwaggerConfig** 
* Check API without UI at http://localhost:8080/v3/api-docs 
* Access Swagger UI at http://localhost:8080/swagger-ui/index.html#/

#Usage Instructions:
* Use the POST endpoint to create a new book. The ID is automatically generated and the available status is always **false**
* Use PUT endpoint to update the information on the a book and the ID is needed.
* A list of books can be retrieved by a string contained in the title. All books will be returned when the search string is skipped. A book can also be collected by its ID. All the available books can also be queried.
* A book can be deleted by its ID. All books can also be deleted.


# Run Spring Boot application
```
mvn spring-boot:run
```

