plugins {
    java
    idea

    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.jpa") version "1.4.21"
    kotlin("kapt") version "1.4.21"
    kotlin("plugin.noarg") version "1.4.21"

    id("org.springframework.boot") version "2.3.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"

    id("io.freefair.aspectj.post-compile-weaving") version "5.2.1"
}

group = "toker"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    runtimeOnly(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter:2.3.3.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.3.3.RELEASE") {
        exclude(group="org.junit.vintage", module="junit-vintage-engine")
    }

    implementation("org.springframework.boot:spring-boot-starter-security:2.3.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-web:2.3.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.3.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-cache:2.3.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-websocket:2.3.3.RELEASE")
    implementation("org.springframework.session:spring-session-core:2.3.0.RELEASE")
    implementation("org.springframework.security:spring-security-openid:5.3.3.RELEASE")
    implementation("org.springframework.security:spring-security-jwt:1.1.0.RELEASE")
    implementation("org.springframework.security:spring-security-messaging:5.3.3.RELEASE")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")

    kapt("org.hibernate:hibernate-jpamodelgen:5.4.17.Final")
    implementation("org.hibernate:hibernate-ehcache:5.4.17.Final")
    implementation("org.postgresql:postgresql:42.2.16")
    implementation("org.apache.commons:commons-dbcp2:2.7.0")

    implementation("com.auth0:java-jwt:3.10.0")

    implementation("org.aspectj:aspectjrt:1.9.6")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.4.10")
}

kapt {
    useBuildCache = true
}

idea {
    module {
        sourceDirs.addAll(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
        generatedSourceDirs.addAll(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
    }
}