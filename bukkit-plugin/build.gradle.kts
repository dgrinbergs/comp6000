import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("java")
  id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "com.comp6000"
version = "0.1.0-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_16
}

repositories {
  mavenCentral()
  mavenLocal()
  maven {
    url = uri("https://papermc.io/repo/repository/maven-public/")
  }
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
  implementation(project(":grpc"))
}

tasks.named<ShadowJar>("shadowJar") {
  version = ""
  classifier = ""
}

tasks {
  build {
    dependsOn(shadowJar)
  }
}

tasks.jar {
  enabled = false
}
