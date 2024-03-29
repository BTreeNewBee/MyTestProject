import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("org.springframework.boot") version "2.4.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "com.iguigui"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

javafx {
    modules("javafx.controls", "javafx.swing")
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.projectlombok:lombok:1.18.20")
    implementation("com.alibaba:easyexcel:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("cn.hutool:hutool-all:5.7.22")
    implementation("com.alibaba:fastjson:1.2.79")
    implementation("top.yumbo.music:yumbo-music-utils:1.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    //网易云client
    implementation("top.yumbo.music:yumbo-music-utils:1.2.3")
    //JNA
    implementation("net.java.dev.jna:jna:5.11.0")
    implementation("org.bytedeco:javacv-platform:1.5.8")
    //compile "io.github.fanyong920:jvppeteer:1.1.5"
    implementation("io.github.fanyong920:jvppeteer:1.1.5")
    implementation("org.freemarker:freemarker:2.3.31")
    implementation("org.jsoup:jsoup:1.15.3")


//doyaaaaaken's kotlin-csv
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.2")
//kotlin-grass
    implementation("io.github.blackmo18:kotlin-grass-core-jvm:1.0.0")
    implementation("io.github.blackmo18:kotlin-grass-parser-jvm:0.8.0")
    //  implementation 'org.mongodb:mongodb-driver-sync:4.8.2'
    implementation("org.mongodb:mongodb-driver-sync:4.2.3")
    //Guava
    implementation("com.google.guava:guava:30.1.1-jre")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //JCommander
    implementation("com.beust:jcommander:1.82")
    //    implementation 'org.eclipse.jgit:org.eclipse.jgit:5.12.0.202106070339-r' // 使用最新版本
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.12.0.202106070339-r")
    implementation("com.google.zxing:core:3.4.1")
    implementation("uk.org.okapibarcode:okapibarcode:0.4.1")

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