plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.rockabyesbj.core.auth"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {

    implementation(project(":core:common"))
    implementation(libs.dagger.hilt.android)  //<--was formerly api
    implementation(libs.dagger.hilt.core)   //<--was formerly api
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose) //<--was formerly api
    implementation(libs.microsoft.identity.client) {
        exclude(group = "com.microsoft.device.display", module = "display-mask")
        }
}