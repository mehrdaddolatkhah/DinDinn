import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
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

    android.buildFeatures.dataBinding = true

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(":domain"))

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
    implementation(Libs.swipe_refresh)
    implementation(Libs.databinding_common)

    // Material
    implementation(Libs.material)

    //hilt
    implementation(Libs.hilt)
    kapt(Libs.hilt_compiler)

    //hilt-view-model
    implementation(Libs.hilt_viewmodel)
    kapt(Libs.hilt_viewmodel_compiler)

    //Glide
    implementation(Libs.glide)
    annotationProcessor(Libs.glide_compiler)

    //test
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.test_runner)
    testImplementation(TestLibs.androidx_core_testing)
    testImplementation(TestLibs.google_truth)
    testImplementation(TestLibs.mockito_core)
    testImplementation(TestLibs.mockito_inline)
    testImplementation(TestLibs.mockito_kotlin)
    testImplementation(TestLibs.coroutines_testing)
}