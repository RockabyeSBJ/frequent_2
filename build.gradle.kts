// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    kotlin("android") version "2.1.20" apply false
    kotlin("jvm") version "2.1.20" apply false
    kotlin("plugin.serialization") version "2.1.20" apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false   //<--- required in root
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.versions)
    // alias(libs.plugins.autonomousapps)
}


// //alias(libs.plugins.kotlin.android) <--- apparently not needed in root according to https://developer.android.com/develop/ui/compose/compiler and anyway this is old