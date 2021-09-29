1- How to run the code :

1 - Run the command "mvn clean install" on the project root, this will generate the jar file with the compiled program.
2 - Run the command "java -jar .\target\origin-take-home-assignment-0.0.1-SNAPSHOT.jar", this will run the compiled program.
 

Main technical decisions made:

It was created a validator for the values of ownership_status and marital_status values, since only a set value is 
acceptable.


The Endpoint used to expose the service was:
http://localhost:8080/insurance/calculate - The Get method was used here, since we are requesting an information from the
server.

A Swagger Api was created for the project to document easily the endpoints, it can be acessed in the following url:
http://localhost:8080/swagger-ui.html#

All validations, and point reducing/adding where created in separate files to allow for future use.

Notes:
If Apache maven is not installed, it will be impossible to run the program, the reason being that maven was the selected
tool to manage dependencies.

The program was written in the Java programming language, using the Spring Framework.



The Api run in the 8080 port, if another program is running in the same port, the Api won't deploy.
