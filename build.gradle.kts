import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "com.iguigui"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.20")
    implementation("com.alibaba:easyexcel:3.0.5")
}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}


val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}