plugins {
    //alias(libs.plugins.android.library)
    //kotlin("plugin.serialization")
    //kotlin("android")

    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.android)
    //using the above plugins instead of the top three because kotlin plugin seems to infer Compose, which I'm trying to keep out of this module
    //Actually probably not the problem. You can probably try calling as kotlin("plugin.serialization") and kotlin("android") to see if it works.

}

android {
    namespace = "com.rockabyesbj.core.common"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // can downgrade to jvm target = 11 if you have issues
    kotlinOptions {
        jvmTarget = "17"
    }


}
//kotlin {
//    jvmToolchain(17)
//    compilerOptions {
//        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
//        // TODO - this block isn't going to fly if it's an Android-aware module.
//    }
//}

dependencies {

    implementation(libs.org.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation((libs.retrofit2))
}