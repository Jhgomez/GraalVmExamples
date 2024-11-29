val graalPyVersion = "24.1.1"

plugins {
    id("java")
    id("application")
    id("org.graalvm.python") version "24.1.1"
}

group = "okik.tech"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "okik.tech.Main"
}

graalPy {

    packages = setOf(
        "annotated-types==0.7.0",
        "anyio==4.6.0",
        "certifi==2024.8.30",
        "distro==1.9.0",
        "h11==0.14.0",
        "hpy==0.9.0",
        "httpcore==1.0.5",
        "httpx==0.27.2",
        "idna==3.10",
        // uses native extensions, openai uses this
        "jiter==0.5.0",
        "openai==1.47.1",
        "pydantic==2.4.2",
        // uses native extensions, openai uses this
        "pydantic_core==2.10.1",
        "sniffio==1.3.1",
        "tqdm==4.66.5",
        "typing_extensions==4.12.2")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.graalvm.polyglot:polyglot:$graalPyVersion")
    implementation("org.graalvm.polyglot:python:$graalPyVersion")
    implementation("org.graalvm.python:python-embedding:$graalPyVersion")
}

tasks.test {
    useJUnitPlatform()
}