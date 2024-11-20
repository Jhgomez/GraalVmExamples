wsl --list --online : returns a list of valid distributions that can be installed
wsl -l -v : shows a list of installed distributions with its version
wsl -d [distribution] : run the indicated distribution
wsl --terminate [linux distro name] : terminates indicated distro
wsl --shutdown  :  terminates all running distributions and the WSL2 lightweight utility virtual machine

## Description

This example runs Python packages that uses native extension, you can see a guide [here](https://github.com/graalvm/graal-languages-demos/blob/main/graalpy/graalpy-native-extensions-guide/README.md), 
this application takes a text entry passed to the Java main method as an argument and then pass it the OPENAI API using GraalPy,
the code in this example is pretty much the code in the [official OPENAI API documentation](https://platform.openai.com/docs/quickstart/create-and-export-an-api-key).
check the [implementation details](https://platform.openai.com/docs/quickstart/create-and-export-an-api-key)

However the implementations details in short are the following, the Python code that is embedded in Java has to return a function
which then is mapped using a Java interface

## Development Environment Requirements

As you can see the guide indicate the requirements are:

* An IDE or text editor, I used IntelliJ IDEA 2024.3 ultimate edition
* A supported JDK, preferably the latest GraalVM JDK, I used Oracle GraalVM Java-21
* a C compiler toolchain (e.g., GCC, cargo)

## Development Environment Set up

### Using Gradle

1. [Install gradle](https://gradle.org/install/)


2. Initialize a gradle project either using IntelliJ or the [documentation](https://docs.gradle.org/current/userguide/part1_gradle_init.html),
you should use gradle wrapper, this would make it easier for other users running your code


3. Configure your project's gradle file, 


### Using Maven
If you need here is a [Maven cheat sheet](https://medium.com/@TimvanBaarsen/maven-cheat-sheet-45942d8c0b86)

1. [download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) Maven


2. Start a project [using GraalPy's Maven archetype](https://www.graalvm.org/latest/reference-manual/python/#maven) or using IntelliJ, you might want to match the configurations in the pom.xml file 
generated when using the archetype.

3. To be able to execute Java applications from the CLI without packing the app in a JAR file add the group: "org.codehaus.mojo" 
artifact: "exec-maven-plugin" plugin, make sure to declare the right main class package, however be aware that it is possible to run apps 
in CLI using Maven without this plugin so you could skip it if you want

### Add GraalPy Dependencies
As described in [GraalPy Getting Started documentation](https://www.graalvm.org/python/#getting-started), we will also 
add a plugin that will allow us to import Python packages as if we were using `pip install` command

#### Using Maven

```xml
<project>
    <dependencies>
        <dependency>
          <groupId>org.graalvm.polyglot</groupId>
          <artifactId>polyglot</artifactId> 
          <version>24.1.1</version>
        </dependency>
        <dependency>
          <groupId>org.graalvm.polyglot</groupId>
          <artifactId>python</artifactId> 
          <version>24.1.1</version>
          <type>pom</type>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.graalvm.python</groupId>
                <artifactId>graalpy-maven-plugin</artifactId>
                <version>24.1.1</version>
                <executions>
                    <execution>
                        <configuration>
                            <packages>
                                <!-- Select Python packages to install via pip. -->
                                <package>pyfiglet==1.0.2</package>
                            </packages>
                        </configuration>
                        <goals>
                            <goal>process-graalpy-resources</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

#### Using Gradle
In the projects gradle file:

 ```kts
plugins {
    id("java")          // java apps needs this
    id("application")   // to be able to run app in CLI
    id("org.graalvm.python") version "24.1.1"
}

graalPy {
    // collection of python packages
    packages = setOf("pyfiglet==1.0.2")
}

dependencies {
    implementation("org.graalvm.polyglot:polyglot:24.1.1")
    implementation("org.graalvm.polyglot:python:24.1.1")
}
 ```
