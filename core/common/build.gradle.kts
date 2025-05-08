plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        // TODO - is the above line needed?
    }
}

//sourceSets {
//    getByName("main").java.srcDirs("src/main/kotlin")
//    getByName("test").java.srcDirs("src/test/kotlin")
//    getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
//}

dependencies {

    implementation(libs.org.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation((libs.retrofit2))
}