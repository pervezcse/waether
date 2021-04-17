Assumptions
===========
1. All data row of a valid input file must contain MinT and MaxT.

Technologies
============
1. Language: Java 8 (prerequisite to build)
2. Build tool: maven (prerequisite to build)
3. Framework: Spring Boot
4. Unit test: JUnit
5. IDE: IntelliJ IDEA

How to build
============
1. go to the project folder
2. run following command:
    mvn clean package

How to run
==========
java -jar target/weather-0.0.1-SNAPSHOT.jar

Result
======
9 54.0

Notes
=====
1. Design Pattern used: Template Method, Builder, Immutable Object, Dependency Injection
2. The input file is stored in resources dir. It is part of the jar file



