# AMX-ASSESSMENT
##Pre-requirements:
- Maven version 3.3.X+
- Java Version: 8

##Building and executing:
- mvn clean package
- mvn spring-boot:run

##Features x API:
- To attend the requirements, the project is using the H2 database containing the tables FieldMapping and FieldValueMapping, where:
    - FieldMapping: Makes the from-to between xml field and json field;
    - FieldValueMapping: Makes the from-to between the values of xml field and json field;
- Converter API: /converter/xml-to-json
    - Using the POST method, the user can pass the XML data into the requisition body and receive the data converted to JSON;    
- When the application starts, some default data are populated in order to be in compliance with PDF sent.

##From-To rules
- if the field or value is not mapped in the tables, the field and value will keep the same data in json result;
- if the value from XML is set as DATE and the value of JSON is set as INTEGER or STRING, the application will return an 'integer' value calculating the current date minus the date passed into XML value 

##Acesssing H2 database
* After launch the application

- URL: http://localhost:8080/h2-console
    - Setting Name:Generic H2 (Embedded) 
    - Driver Class: org.h2.Driver
    - JDBC URL: jdbc:h2:mem:amx
    - User name: sa
    - Password: sa

##Testing through SWAGGER
* After launch the application

- URL: http://localhost:8080/swagger-ui.html
