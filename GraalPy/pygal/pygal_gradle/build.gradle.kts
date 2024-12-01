val graalPyVersion = "24.1.1"

plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.graalvm.buildtools.native") version "0.10.3"
	id("org.graalvm.python") version "24.1.1"
}

group = "okik.tech"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

graalPy {
	packages = setOf(
		"pygal==3.0.5"
	)
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.graalvm.polyglot:polyglot:$graalPyVersion")
	implementation("org.graalvm.polyglot:python:$graalPyVersion")
	implementation("org.graalvm.python:python-embedding:$graalPyVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
