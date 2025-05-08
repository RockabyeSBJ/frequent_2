plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.rockabyesbj.security"
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

    implementation(project(":core:common"))

    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    api(libs.androidx.navigation.compose)


    implementation(libs.androidx.core.ktx) // TODO find/create common module, you keep repeating yourself
    implementation(libs.androidx.appcompat) // TODO find/create common module, you keep repeating yourself
    implementation(libs.javax.inject)
    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}