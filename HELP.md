# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/#build-image)
* [Vaadin](https://vaadin.com/docs)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#actuator)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#using.devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Creating CRUD UI with Vaadin](https://spring.io/guides/gs/crud-with-vaadin/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

CREATE NEW CUSTOMER
curl -X POST -H 'Content-Type: application/json' -d '{"id":null,"firstName":"Francesco","lastName":"Signore"}' localhost:8080/crud/customer


UPDATE EXISTING CUSTOMER
curl -X POST -H 'Content-Type: application/json' -d '{"id":6,"firstName":"Francesco","lastName":"Santos Signore"}' localhost:8080/crud/customer


GET ALL CUSTOMERS
curl -X GET localhost:8080/crud/customers


GET SINGLE CUSTOMER BY PK
curl -X GET localhost:8080/crud/customers/1