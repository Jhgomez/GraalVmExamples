## Description
This is the implementation of the project [described in the parent directory](../README.md) using Gradle to build the project, make sure
to follow the instructions in the "Development Environment Setup" section.

## Project Setup

1. [Install gradle](https://gradle.org/install/), if your downloading/cloning the repo you can skip this and next step as
the gradle wrapper is already in this directoy so no need to install, the wrapper is used to execute
gradle commands in the CLI


2. Initialize a Gradle project either using IntelliJ or the [documentation](https://docs.gradle.org/current/userguide/part1_gradle_init.html),
you should use Gradle wrapper, this would make it easier for other users running your code. You could skip this step if you
have installed Gradle on your computer but that means you would run gradle tasks in the command line just using `./gradle <task_name>`
instead of `./gradlew <task_name>`


3. Configure your project's gradle file, with these plugins. `java` plugin helps build and compile your app. `application` here 
helps us running the app from the command line but it will require us to define the main class. The `org.graalvm.python` plugin
enables us to declare python packages dependencies. Also include the Python polygloth dependencies

```bash
    plugins {
        id("java")
        id("application")
        id("org.graalvm.python") version "24.1.1"
    }
    
    application {
      mainClass = "okik.tech.Main"
    }
    
    graalPy {
        packages = setOf(
          "opeanai==1.47.1",
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
./gradlew run --args="'what is GraalVm?'"
```