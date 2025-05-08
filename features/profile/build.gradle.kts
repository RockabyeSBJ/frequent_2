plugins {
    alias(libs.plugins.devtools.ksp)         // KSP plugin for Annotation processing
    alias(libs.plugins.android.library)    // Android plugin application.
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.compose.compiler)// Hilt plugin application.
    kotlin("android")      // Kotlin compiler
}

android {
    namespace = "com.rockabyesbj.features.profile"
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

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    //sourceSets {
    //    getByName("main").java.srcDirs("src/main/kotlin")
    //    getByName("test").java.srcDirs("src/test/kotlin")
    //    getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    //}
}


dependencies {

    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    api(libs.androidx.navigation.compose)

    implementation(project(":core"))
    implementation(project(":core:auth"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:cache"))
    implementation(project(":core:uicommon"))
    implementation(project(":features:auth"))

}