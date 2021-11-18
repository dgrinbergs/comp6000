plugins {
  id("java")
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
}
