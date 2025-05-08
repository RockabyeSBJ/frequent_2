plugins {
    alias(libs.plugins.android.library)
    kotlin("android")
    alias(libs.plugins.devtools.ksp) 
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.rockabyesbj.core.network"
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

    implementation(project(":core:auth"))
    implementation(project(":core:common"))
    implementation(project(":core:security"))

    implementation(libs.androidx.core.ktx) // TODO find/create common module, you keep repeating yourself
    implementation(libs.androidx.appcompat) // TODO find/create common module, you keep repeating yourself
    implementation(libs.microsoft.identity.client){
        exclude(group = "com.microsoft.device.display", module = "display-mask")
        }
    implementation(libs.okhttp3)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.moshi)
    implementation(libs.javax.inject)
    api(libs.dagger.hilt.android)
    api(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.compiler)
    api(libs.androidx.navigation.compose)
    implementation(libs.javax.inject)

}