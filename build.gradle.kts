// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    // Repositories to search for dependencies
    repositories {
        google()
        mavenCentral()
    }
    // Dependencies for build script, such as the Kotlin plugin
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath ("com.android.tools.build:gradle:8.2.0")

    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
    id ("com.android.library") version "8.2.0" apply false

    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false

    id("com.google.dagger.hilt.android") version "2.42" apply false
    id ("androidx.navigation.safeargs") version "2.5.3" apply false

}