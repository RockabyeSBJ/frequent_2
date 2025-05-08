plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.rockabyesbj.features.auth"
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
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {

    implementation(libs.microsoft.identity.client){
            exclude(group = "com.microsoft.device.display", module = "display-mask")
            }
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.javax.inject)

    implementation(libs.dagger.hilt.android) //<-- was formerly api
    implementation(libs.dagger.hilt.core) //<-- was formerly api
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose) //<-- was formerly api

    implementation(libs.okhttp3)
    implementation(project(":core"))
    implementation(project(":core:auth"))
    implementation(project(":core:network"))
    implementation(project(":core:cache"))
    api(project(":core:common"))
}