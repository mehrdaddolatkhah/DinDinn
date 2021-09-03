import org.jetbrains.kotlin.config.KotlinCompilerVersion

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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    //implementation(Libs.core_ktx)

    //retrofit
    api(Libs.retrofit)
    api(Libs.retrofit_gson_converter)

    //okhttp
    api(Libs.okhttp)
    api(Libs.gson)
    api(Libs.logging_interceptor)

    //rx-java
    api(Libs.rxjava)
    api(Libs.rxjava_adapter)
    api(Libs.rxandroid)

    //hilt
    implementation(Libs.hilt)
    kapt(Libs.hilt_compiler)
}
