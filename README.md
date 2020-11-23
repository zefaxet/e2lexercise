# e2lexercise
Hi there

# Notes
This uses Lombok, which is a boilerplate helper.
If you open this project in an IDE without Lombok support, you may see a lot of red. Even so, the project will still compile.
If you would like to eliminate the red, Eclipse and IntelliJ both have Lombok plugins, but this is not required.

The ORM's differ from the provided UML class diagrams. This has been acknowledged in the code and was done intentionally with explanations.

# Suggestions
The written description of the modules were inconsistent with their UML representations.

# Instructions

This exercise implementation was built using Oracle OpenJDK 12.
https://jdk.java.net/java-se-ri/12

### Running the service

```shell script
$ git clone https://github.com/zefaxet/e2lexercise
$ cd e2lexercise
e2lexercise$ gradle[w] bootRun
```

### Running the tests
```shell script
e2lexercise$ gradle[w] test
```