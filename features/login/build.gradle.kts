plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.rockabyesbj.login"
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
        getByName("main").java.srcDirs("src/main/kotlin", "src/main/java") //<-- required for Java to construct BuildConfig in AppLogging
    }
}

dependencies {

    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    implementation(project(":core"))
    implementation(project(":core:auth"))
    implementation(project(":core:cache"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:error"))
    implementation(project(":core:uicommon"))
    implementation(project(":features:auth"))
    implementation(libs.javax.inject)

}