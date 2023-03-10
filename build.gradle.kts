plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"

    `java-library`
    `maven-publish`
}

group = "com.github.jiy0ung"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")

    api("org.apache.commons:commons-math3:3.6.1")

    implementation("com.google.guava:guava:31.1-jre")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        ))
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
