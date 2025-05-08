plugins {

    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)

}

android {
    namespace = "com.rockabyesbj.core.cache"
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

    ksp {
        arg("room.schemaLocation", "$rootDir/schemas")
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:auth"))
    implementation(libs.androidx.core.ktx) // TODO find/create common module, you keep repeating yourself
    implementation(libs.androidx.appcompat) // TODO find/create common module, you keep repeating yourself
    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.javax.inject)
    ksp(libs.androidx.room.compiler)
}