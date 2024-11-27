# Description
This example show case how we can generate a QR code using python library 

## Development Environment Setup

1. [Download/install jbang](https://www.jbang.dev/download/). I'm using Windows 11 so I just executed `curl -Ls https://sh.jbang.dev | bash -s - app setup` on GitBash

2. Install GraalVm 21 or above, I used 23. Set `JAVA_HOME` environment variable

## Project Setup

1. Create a `.java` file and declare the dependencies. GraalPy's "quick start" documentation would 
give you this `org.graalvm.polyglot:polyglot:24.1.1` and `org.graalvm.polyglot:python:24.1.1` but these
are BOMs(Bill Of Materials), it means they are a list of artifacts/dependencies. So we have to navigate
into their `pom.xml` file and, from the first one we don't need any dependency. In the second one we will
find another BOM `org.graalvm.python:python:24.1.1`, in which we will find another BOM, [org.graalvm.python:python-community:24.1.1](https://mvnrepository.com/artifact/org.graalvm.python/python-community/24.1.1),
in this BOM we will find the first two dependencies, this is all because Jbang can only [execute jar files](https://mvnrepository.com/artifact/org.graalvm.python/python-community/24.1.1)
from a repo and not a BOM. The next two represent the `org.graalvm.python:graalpy-maven-plugin:24.1.1` plugin which is found in the same
documentation. We get this two dependencies from navigating through the `pom.xml` [file](https://repo1.maven.org/maven2/org/graalvm/python/graalpy-maven-plugin/24.1.1/graalpy-maven-plugin-24.1.1.pom)
of this pulgin in Maven repository, remember this plugin is used to declare python packages. The next one
is a package that is needed to bridge between Python and Java, it provides the API to manage and use GraalPy from Java
according to [this documentation](https://github.com/graalvm/graal-languages-demos/tree/main/graalpy/graalpy-native-extensions-guide#41-dependency-configuration) and
the last is a pip install of the python qrcode package using Graalpy's sintax

    ```java
   //DEPS org.graalvm.python:python-language:24.1.1
   //DEPS org.graalvm.python:python-resources:24.1.1
   //DEPS org.graalvm.python:python-launcher:24.1.1
   //DEPS org.graalvm.python:python-embedding-tools:24.1.1
   //DEPS org.graalvm.python:python-embedding:24.1.1
   //PIP qrcode==8.0
    ```
    
2. Create a GraalPy context, this time we use `GraalPyResources.contextBuilder()` this gives us more
'flexibility' when creating the Python virtual environment, in this case we are showcasing we 
can set up `PythonHome` environment variable which is used to set the default libraries location, it is
not required that we set this variable up manually so we are just using this to showcase how we can
customize the venv, the [python documentation](https://docs.python.org/3/using/cmdline.html#environment-variables) can
explain more about this env variable.

chmod +x helloworld.java
./helloworld.java jbang!

jbang helloworld.java