plugins {
    id("java")
    id("org.graalvm.python") version "24.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

graalPy {
    packages = setOf("openai==1.54.4")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.graalvm.polyglot:polyglot:24.1.1")
    implementation("org.graalvm.polyglot:python:24.1.1")
}

tasks.test {
    useJUnitPlatform()
}