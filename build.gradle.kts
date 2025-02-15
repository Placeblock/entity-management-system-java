plugins {
    id("maven-publish")
    id("signing")
    id("java")
}

group = "de.codelix"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

    // JSON
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.2")

    // ZeroMQ
    implementation("org.zeromq:jeromq:0.6.0")
}


java {
    withJavadocJar()
    withSourcesJar()
}


tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        options.release.set(23)
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        title = "CommandAPI API Documentation"
    }
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(tasks["jar"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            version = project.version.toString()
            pom {
                packaging = "jar"
                name.set("EntityManagementSystem")
                description.set("Library for managing entity identities")
                licenses {
                    license {
                        name.set("GNU General Public License, Version 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.html")
                    }
                }
                developers {
                    developer {
                        name.set("Felix")
                        organization.set("Codelix")
                        organizationUrl.set("https://codelix.de/")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}
