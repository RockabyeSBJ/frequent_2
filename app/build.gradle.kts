plugins {
    alias(libs.plugins.android.application)  // Android Compiler
    alias(libs.plugins.compose.compiler)    // Compose Compiler
    alias(libs.plugins.devtools.ksp)         // KSP plugin for Annotation processing
    alias(libs.plugins.hilt.android)         // Hilt plugin application.
    kotlin("android")

}

android {
    namespace = "com.rockabyesbj.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rockabyesbj.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //sourceSets {
    //    getByName("main").java.srcDirs("src/main/kotlin")
    //    getByName("test").java.srcDirs("src/test/kotlin")
    //    getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    //}


    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":core:auth"))
    implementation(project(":core:cache"))
    implementation(project(":core:common"))
    implementation(project(":core:error"))
    implementation(project(":core:uicommon"))
    implementation(project(":core:security"))
    implementation(project(":core:navigation"))
    implementation(project(":di"))  // <-- new way to avoid feature:profile and feature:auth as dependencies in core
    implementation(project(":features:auth"))
    implementation(project(":features:home"))
    implementation(project(":features:splash"))
    implementation(project(":features:login"))
    implementation(project(":features:profile"))

    //required in this module, as setContent{} lives in MainActivity, here
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    //implementation(libs.microsoft.identity.client) {
    //    exclude(group = "com.microsoft.device.display", module = "display-mask")
    //}
    ksp(libs.dagger.hilt.compiler)
}