## Description
This is the implementation of the project [described in the parent directory](../README.md) using Maven to build the project, make sure
to follow the instructions in the "Development Environment Setup" section.

## Project Setup
If you need here is a [Maven cheat sheet](https://medium.com/@TimvanBaarsen/maven-cheat-sheet-45942d8c0b86)

1. [download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) Maven.


2. Start a project [using GraalPy's Maven archetype](https://www.graalvm.org/latest/reference-manual/python/#maven) or using IntelliJ, you might want to match the configurations in the pom.xml file
   generated when using the archetype.

3. As a best practice [add maven wrapper](https://maven.apache.org/wrapper/), the wrapper is just a way to help other devs
   to run maven commands in the CLI without having to install maven. So after this run maven commands using the wrapper, for example:`./mvnw install`,
however you can choose, if you have installed Maven on your computer, to run maven using the one installed on your computer with  `./mvn install`


4. Configure `pom.xml`. Add Python polyglot dependencies. As described in [GraalPy Getting Started documentation](https://www.graalvm.org/python/#getting-started), we will also
add a plugin that will allow us to import Python packages as if we were using `pip install` command but in this case
we also need group: "org.codehaus.mojo", artifact: "exec-maven-plugin" plugin to be able to execute this application from
the command line, this is something that GraalPy needs to execute its applications in the command line. we can either define
the main class in the declaration of this plugin or pass it in the command used to run the app

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
       <dependency>
          <groupId>org.graalvm.python</groupId>
          <artifactId>python-embedding</artifactId>
          <version>24.1.1</version>
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

       <pluginManagement>
          <plugin>
             <groupId>org.codehaus.mojo</groupId>
             <artifactId>exec-maven-plugin</artifactId>
             <version>1.2.1</version>
             <executions>
                <execution>
                   <goals>
                      <goal>java</goal>
                   </goals>
                </execution>
             </executions>
             <configuration>
                <mainClass>okik.tech.Main</mainClass>
             </configuration>
          </plugin>
       </pluginManagement>
    </build>
</project>
```

## Run the application
If you are just cloning/downloading this repo you can run the app using the Maven´s wrapper as it
is already available in this directory. Be aware that on windows the packages with native extensions are always compiled, in the future this might change and
the compiled code will be instead downloaded from a repo which should be faster.

```bash
  ./mvnw exec:java -Dexec.args="'what is GraalPy?'" -Dexec.mainClass="okik.tech.Main"
```

The `-Dexec.mainClass="okik.tech.Main"` is optional if you defined it in the plugin

If you've installed Maven on your computer and run commands using the version installed on your computer
which is available in the `PATH` env variable make sure it is the same version as the wrapper. This would enable
you to run it with the following command:

```bash
  mvn exec:java -Dexec.args="'what is GraalPy?'" -Dexec.mainClass="okik.tech.Main"
```
Again, `-Dexec.mainClass="okik.tech.Main"` is optional if you defined it in the plugin
