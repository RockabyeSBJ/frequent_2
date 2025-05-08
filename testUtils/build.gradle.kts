plugins {
    id("com.android.library")
    // id("org.jetbrains.kotlin.android")  <-- not needed
    alias(libs.plugins.devtools.ksp)         // KSP plugin for Annotation processing
    alias(libs.plugins.hilt.android)         // Hilt plugin application.
    alias(libs.plugins.kotlin.android)      // Applies Kotlin support, compiler
    alias(libs.plugins.compose.compiler)    // Applies the compiler
}

android {
    namespace = "com.rockabyesbj.hilt_andkapt"
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

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}