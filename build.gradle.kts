plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
    `java-gradle-plugin`
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

gradlePlugin {
    plugins {
        create("ssl") {
            id = "com.harleylizard.ssl"
            implementationClass = "com.harleylizard.ssl.SslPlugin"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.harleylizard"
            artifactId = "ssl"
            version = "1.0-SNAPSHOT"
            from(components["kotlin"])
        }
    }
}