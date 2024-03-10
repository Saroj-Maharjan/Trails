
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")

    }
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.jetbrains.compose.gradle.plugin)
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.serialization.plugin)
        classpath(libs.anvil.gradle.plugin)
        classpath(libs.square.sqldelight.plugin)

//        id("io.ktor.plugin").version(extra["ktor.version"] as String)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    group = "org.mobilenativefoundation"
    version = "1.0.0-SNAPSHOT"
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}