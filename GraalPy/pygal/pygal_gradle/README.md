# Description
This is an example that shows we can use GraalPy in Spring Boot. We're using a Python package
called Pygal, used for generating different types of charts. We are going to generate
SVG files that our services will return them in the form of a String that the browser can
render. Remember SVG describe the Path of the image they represent so the image can be
scale up or scale down without loosing resolution.

This is the [original example](https://github.com/graalvm/graal-languages-demos/tree/main/graalpy/graalpy-spring-boot-pygal-charts).
We can also check this [other example](https://github.com/graalvm/graal-languages-demos/tree/main/graalpy/graalpy-spring-boot-guide)
that explains how to implement a service in SpringBoot that uses Graalpy, this other example
also uses Jquery in the frontend UI to control the page's "Document Object Model(DOM)", to 
be able to modify the elements of the frontend and make an HTTP request to the backend.

## Development Environment Requirements

As you can see the guide indicate the requirements are:

* An IDE or text editor, I used IntelliJ IDEA 2024.3 ultimate edition here you can leverage [language injections](https://www.jetbrains.com/help/idea/using-language-injections.html#use-language-injection-comments)
* A supported JDK, preferably the latest GraalVM JDK, I used Oracle GraalVM Java-23
