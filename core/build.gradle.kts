import com.android.kotlin.multiplatform.ide.models.serialization.androidTargetKey

plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.rockabyesbj.core"
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

    //sourceSets {
    //    getByName("main").java.srcDirs("src/main/kotlin")
    //    getByName("test").java.srcDirs("src/test/kotlin")
    //    getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    //}
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:cache"))
    implementation(project(":core:navigation"))
    implementation(project(":core:uicommon"))
    implementation(project(":core:security"))
    implementation(project(":core:error"))

    implementation(libs.dagger.hilt.android) //<-- was formerly api
    implementation(libs.dagger.hilt.core)  //<-- was formerly api
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.androidx.navigation.compose)  //<-- was formerly api
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.retrofit2)
    implementation(libs.javax.inject)
}