plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.compose)
    // For the love of Christ and all things holy, don't touch anything on this page, including navigation and compose libraries, and buildfeatures.
}

android {
    namespace = "com.rockabyesbj.features.home"
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

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get() }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check"
        )
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


}

dependencies {

    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    api(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(project(":core:uicommon"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:cache"))
    implementation(project(":core:error"))

    // Feature dependencies - use interfaces only
    implementation(project(":features:auth"))
    implementation(project(":features:profile"))
    //implementation(project(":features:loyalty"))

    // Hilt
    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)

    // Navigation
    api(libs.androidx.hilt.navigation.compose)
}