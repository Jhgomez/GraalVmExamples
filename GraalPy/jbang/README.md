# Description
This example show case how we can generate a QR code using python library 

## Development Environment Setup

1. [Download/install jbang](https://www.jbang.dev/download/). I'm using Windows 11 so I just executed `curl -Ls https://sh.jbang.dev | bash -s - app setup` on GitBash

2. Install GraalVm 21 or above, I used 23. Set `JAVA_HOME` environment variable

## Project Setup

1. Create a `.java` file and declare the dependencies. The first two dependencies can be found in the GraalPy's "quick start" documentation, the next two represent the `org.graalvm.python:graalpy-maven-plugin:24.1.1` plugin which is found in the same documentation. We get this two dependencies from navigating through the `pom.xml` [file](https://repo1.maven.org/maven2/org/graalvm/python/graalpy-maven-plugin/24.1.1/graalpy-maven-plugin-24.1.1.pom) of this pulgin in Maven repository, remember this plugin is used to declare python packages and the last is a pip install of the python qrcode package using Graalpy's sintax

    ```java
    // PEP org.graalvm.polyglot:polyglot:24.1.1
    // PEP org.graalvm.polyglot:python:24.1.1
    // PEP org.graalvm.python:python-embedding:24.1.1
    // PEP org.graalvm.python:python-embedding-tools:24.1.1
    // pip qrcode==8.0
    ```

2. 

chmod +x helloworld.java
./helloworld.java jbang!

jbang helloworld.java