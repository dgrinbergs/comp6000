import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
  id("java-library")
  id("com.google.protobuf") version "0.8.17"
}

group = "com.comp6000"

java {
  sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
  mavenCentral()
  mavenLocal()
}

dependencies {
  "api"("io.grpc:grpc-netty-shaded:1.42.1")
  "api"("io.grpc:grpc-protobuf:1.42.1")
  "api"("io.grpc:grpc-stub:1.42.1")
  compileOnly("org.apache.tomcat:annotations-api:6.0.53")
}

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:3.17.2:osx-x86_64"
  }
  plugins {
    id("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:1.42.1"
    }
  }
  generateProtoTasks {
    all().forEach {
      it.plugins {
        id("grpc")
      }
    }
  }
}
