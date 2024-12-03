// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    alias(libs.plugins.compose.compiler) apply false
}
