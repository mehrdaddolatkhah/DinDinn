plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdk = AndroidSdk.compileSdkVersion
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }
}

dependencies {

    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

        // Modules
        implementation(project(":domain"))

        //hilt
        implementation(Libs.hilt)
        kapt(Libs.hilt_compiler)
    }
}