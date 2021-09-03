import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdk = AndroidSdk.compileSdkVersion

    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    android.buildFeatures.dataBinding = true

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    // Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation(Libs.core_ktx)
    implementation(Libs.activity_ktx)
    implementation(Libs.fragment_ktx)

    // Android x
    implementation(Libs.appcompat)
    implementation(Libs.constraint)
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    // Material
    implementation(Libs.material)

    //hilt
    implementation(Libs.hilt)
    kapt(Libs.hilt_compiler)

    //multidex
    implementation(Libs.multidex)
}