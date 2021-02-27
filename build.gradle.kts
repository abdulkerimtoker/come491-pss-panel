plugins {
    java
    idea

    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.jpa") version "1.4.21"
    kotlin("kapt") version "1.4.21"
    kotlin("plugin.noarg") version "1.4.21"

    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

group = "toker"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14
java.targetCompatibility = JavaVersion.VERSION_14

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    runtimeOnly(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter:2.4.1")

    implementation("org.springframework.boot:spring-boot-starter-security:2.4.1")
    implementation("org.springframework.boot:spring-boot-starter-web:2.4.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.4.1")
    implementation("org.springframework.session:spring-session-core:2.4.1")
    implementation("org.springframework.security:spring-security-jwt:1.1.0.RELEASE")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.0")

    kapt("org.hibernate:hibernate-jpamodelgen:5.4.27.Final")
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("org.apache.commons:commons-dbcp2:2.8.0")

    implementation("com.auth0:java-jwt:3.10.0")

    implementation("org.aspectj:aspectjrt:1.9.6")
}

kapt {
    useBuildCache = true
}

noArg {
    annotation("toker.pss.annotation.PKClass")
}

idea {
    module {
        sourceDirs.addAll(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
        generatedSourceDirs.addAll(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
    }
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_14.toString()