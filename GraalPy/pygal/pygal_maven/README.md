# Description
This is the implementation of the project [described in the parent directory](../README.md). We will
follow the documentation to generate a XY chart. [Pygal documenation to XY chart](https://www.pygal.org/en/stable/documentation/types/xy.html)

## Project Setup

1. Initialize a SpringBoot project using the [Srping Initializer](https://start.spring.io/). Select "Maven", language "Java"
   I used SpringBoot version "3.4.0", Assign a Group and Artifact if you need, Add two dependencies, "GraalVM Native Support" and "Spring Web", the
   last dependency helps you create a webserver that uses the default embedded container which is Apache Tomcat.
   Click Generate, download the project and open it. Be aware Maven's wrapper will be available in the project's path.

2. Configure your project's POM file, with these plugins. The `org.graalvm.python` plugin
   enables us to declare python packages dependencies. Also include, besides all dependencies we had included by the generator already,
   the Python polyglot dependencies.


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
                     <package>pygal==3.0.5</package>
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

## Implementation

### Create a Python Context "Wrapper"
You can use different annotations to inject a component/bean into the application's
dependency graph. You have different ways to produce a bean, in the video you can see more ways. if you want to know more about how dependency injection
works in SpringBoot, see [this video](https://www.youtube.com/watch?v=LeoCh7VK9cg) or check the [notes](../CompomentsAKABeans) I made from it. I'll show two "approaches"

#### Create a Configuration Class to Create a Bean
1. Create a class and annotate it with `@Configuration`

2. In our case we need to make a class that takes a Polyglot context and wrap it inside something similar to an adapter or facade pattern class, in this case it can be a record,
   the wrapper is the class that our bean will produce

3. Create a method that return the bean we want in the configuration class that,
   and annotate it with `@Bean(destroyMethod = "close")`, check the code in the class and
   observe we initialize the "python" code, this is not completaly necesary if our evaluation code
   specifies the language name but in this case we need a way to close it so we have to implicitly
   call the "close" method to avoid memory leaks, so indicate it in the annotation arguments

#### "Directly" Create a Bean/Component
1. All beans can be called components, so just basicall create the Polyglot's context wrapper class, which
   is similar to a facade or adapter pattern class

2. Annotate the class with `@Component` and the method that destroys the objects that could cause memory leaks
   , if not closed correctly, with `@PreDestroy`

#### Important
Context can not process request asynchronously or in  a multithread way, so in this case we don't worry about it
but if requests needs to be processed in parallel then you should consider creatin a context pool, however [this info](https://github.com/graalvm/graal-languages-demos/tree/main/graalpy/graalpy-native-extensions-guide#72-single-context) might
indicate it is not a good practice

### Create Services
We have four variations of the same functionality to show case that we can move objects creation
and logic from Python to Java and Java to Python, For that you can use these as a guide reference
on how you can start understanding how mapping works so you can decide what approach is best or even
combining some or all of these approaches, But first you should always read the documentation and
examples of the python packages, classes and properties you need to map to java, so you can understand
what you're mapping and identify only what you need to map, . Return values and arguments are mapped according to a set of
[generic rules](https://www.graalvm.org/latest/reference-manual/python/Modern-Python-on-JVM/#java-to-python-types-automatic-conversion) as well as the [Target type mapping](https://www.graalvm.org/truffle/javadoc/org/graalvm/polyglot/Value.html#target-type-mapping-heading),
basically to sum it up you have to lear how to use the `Value` class in polyglot package. Annotate the service
class with `@Service`

### Create a Controller Class
First, annotate the class with `@RestController` or `@Controller`, this class basically maps the requests to the correct service, I think this is similar to Java Servlets, You can use
annotations like `@GetMapping("/servicename")`, `@Get(value = "/servicename")` or `@RequestMapping(method = GET, path = "/servicename")`, you could use
annotations like `@ExecuteOn(TaskExecutors.BLOCKING)`

## Run the application
```bash
./mvnw spring-boot:run
```
