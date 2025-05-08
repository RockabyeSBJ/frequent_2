plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)

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

    composeOptions {
        kotlinCompilerExtensionVersion = "2.1.20"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    buildFeatures {
        buildConfig = true // <- FORCE it on
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
    //implementation(project(":core:security"))
    implementation(project(":core:navigation"))
    implementation(project(":di"))  // <-- new way to avoid feature:profile and feature:auth as dependencies in core
    implementation(project(":features:auth"))
    //implementation(project(":features:home"))
    implementation(project(":features:splash"))
    implementation(project(":features:login"))
    //implementation(project(":features:profile"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.microsoft.identity.client) {
        exclude(group = "com.microsoft.device.display", module = "display-mask")
    }
}