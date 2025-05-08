plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)  // <-- must be applied for every module that uses Compose
    kotlin("android")  // <--- libs.plugins.kotlin.android is dead, after a long frustrating exploration. Fucking confusing-ass shit.
    alias(libs.plugins.hilt.android) //requires KSP and Dagger
    alias(libs.plugins.devtools.ksp) // required by KSP
}

android {
    namespace = "com.rockabyesbj.uicommon"
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

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin", "src/main/java") //<-- required for Java to construct BuildConfig in AppLogging
    }
}

dependencies {

    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    api(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.foundation)
    api(libs.androidx.foundation.layout)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.material3)
    //api(libs.androidx.navigation.compose) shouldn't be needed.
    api(libs.androidx.runtime)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)

}