buildscript {

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0") // Check this line for the Kotlin version
        classpath (libs.kotlin.gradle.plugin)
        classpath (libs.hilt.android.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}