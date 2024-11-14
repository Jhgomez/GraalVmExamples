val graalPyVersion = "24.1.1"

plugins {
    id("java")
    id("application")
    id("org.graalvm.python") version "24.1.1"
//    id("org.graalvm.buildtools.native") version "0.10.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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
        // the more compatible version according to
        // https://www.graalvm.org/python/compatibility/
        // is 0.27.4 however I still get the same error no matter of the version

        //    Patching package pydantic_core using C:\Users\Juan Enrique\AppData\Local\org.graalvm.polyglot\python\python-home\7f99e649dc51b21db2b83ce56bc9742eefe66415\lib-graalpython\patches\pydantic-core\pydantic-core-2.10.1.patch
        //    WARNING: GraalPy needs the 'patch' utility to apply compatibility patches. Please install it using your system's package manager.
        //    Installing build dependencies: started
        //    Installing build dependencies: still running...
        //    Installing build dependencies: finished with status 'done'
        //    Getting requirements to build wheel: started
        //    Getting requirements to build wheel: finished with status 'done'
        //    Preparing metadata (pyproject.toml): started
        //    Preparing metadata (pyproject.toml): finished with status 'error'
        //    error: subprocess-exited-with-error
        //
        //    × Preparing metadata (pyproject.toml) did not run successfully.
        //    │ exit code: 1
        //    ╰─> [6 lines of output]
        //    Checking for Rust toolchain....
        //
        //        Cargo, the Rust package manager, is not installed or is not on PATH.
        //    This package requires Rust and Cargo to compile extensions. Install it through
        //    the system's package manager or via https://rustup.rs/
        //
        //    [end of output]
        //
        //    note: This error originates from a subprocess, and is likely not a problem with pip.
        //    error: metadata-generation-failed

        // TODO try new versions of "org.graalvm.python" when available
        "openai==0.27.4",
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