# Description
This is the implementation of the project [described in the parent directory](../README.md). We will
follow the documentation to generate a XY chart. [Pygal documenation to XY chart](https://www.pygal.org/en/stable/documentation/types/xy.html)

## Project Setup

1. Initialize a SpringBoot project using the [Srping Initializer](https://start.spring.io/). Select "Gradle-kotlin", language "Java"
I used SpringBoot version "3.4.0", Assign a Group and Artifact if you need, Add two dependencies, "GraalVM Native Support" and "Spring Web", the
last dependency helps you create a webserver that uses the default embedded container which is Apache Tomcat. 
Click Generate, download the project and open it. Be aware gradle wrapper will be availabe in the project's path.

2. Configure your project's gradle file, with these plugins. The `org.graalvm.python` plugin
   enables us to declare python packages dependencies. Also include, besides all dependencies we had included by the generator already,
the Python polygloth dependencies.


```bash
    plugins {
        id("org.graalvm.python") version "24.1.1"
    }
    
    application {
      mainClass = "okik.tech.Main"
    }
    
    graalPy {
        packages = setOf(
            "pygal==3.0.5",
        )
    }
    
    dependencies {
        implementation("org.graalvm.polyglot:polyglot:24.1.1")
        implementation("org.graalvm.polyglot:python:24.1.1")
        implementation("org.graalvm.python:python-embedding:24.1.1")
    }
```

## Run the application
```bash
./gradlew bootRun
```
